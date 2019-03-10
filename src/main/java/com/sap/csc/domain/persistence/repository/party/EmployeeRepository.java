package com.sap.csc.domain.persistence.repository.party;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sap.csc.domain.model.jpa.party.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	public Employee findOne(Long id);
	
	public Employee findDetailedOne(Long id);
	
	public List<Employee> findDetailedAllByOpenId(String openId);
	
	public List<Employee> findAllByOpenId(String openId);
	
	public int countOwnSystemsByOpenId(String openId);

}
