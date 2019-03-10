package com.sap.csc.domain.persistence.repository.system;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sap.csc.domain.model.jpa.c4c.C4CSystem;

public interface SystemRepository extends JpaRepository<C4CSystem, Long> {

	public C4CSystem findByURL(String url);
	
}
