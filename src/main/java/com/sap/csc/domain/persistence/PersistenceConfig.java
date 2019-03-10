package com.sap.csc.domain.persistence;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.sap.csc.domain.model.jpa.JpaModelDesignation;


/**
 * @author I326950
 */
@Configuration
@EntityScan(basePackageClasses = JpaModelDesignation.class)
@EnableJpaAuditing
public class PersistenceConfig {

}
