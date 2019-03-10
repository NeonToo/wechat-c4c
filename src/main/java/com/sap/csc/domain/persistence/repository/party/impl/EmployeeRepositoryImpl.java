package com.sap.csc.domain.persistence.repository.party.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.JoinType;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sap.csc.domain.model.jpa.party.Employee;
import com.sap.csc.domain.persistence.repository.party.EmployeeRepository;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class EmployeeRepositoryImpl extends SimpleJpaRepository<Employee, Long> implements EmployeeRepository {

	public EmployeeRepositoryImpl(EntityManager entityManager) {
		super(Employee.class, entityManager);
	}

	@Override
	@Transactional(readOnly = true)
	public Employee findOne(Long id) {
		return super.findOne((root, query, cb) -> {
			root.fetch("c4cUser", JoinType.LEFT);

			return cb.equal(root.get("id"), id);
		});
	}

	@Override
	@Transactional(readOnly = true)
	public Employee findDetailedOne(Long id) {
		return super.findOne((root, query, cb) -> {
			root.fetch("c4cUser", JoinType.LEFT);
			root.fetch("wechatUser", JoinType.LEFT);

			return cb.equal(root.get("id"), id);
		});
	}


	@Override
	@Transactional(readOnly = true)
	public List<Employee> findAllByOpenId(String openId) {
		return super.findAll((root, query, cb) -> {
			root.fetch("c4cUser", JoinType.LEFT).fetch("system", JoinType.LEFT);

			return cb.equal(root.join("wechatUser").get("openId"), openId);
		});
	}

	@Override
	@Transactional(readOnly = true)
	public List<Employee> findDetailedAllByOpenId(String openId) {
		return super.findAll((root, query, cb) -> {
			root.fetch("c4cUser", JoinType.LEFT).fetch("system", JoinType.LEFT);
			root.fetch("wechatUser", JoinType.LEFT);

			return cb.equal(root.get("openId"), openId);
		});
	}

	@Override
	@Transactional(readOnly = true)
	public int countOwnSystemsByOpenId(String openId) {
		return this.findAllByOpenId(openId).size();
	}
}
