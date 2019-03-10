package com.sap.csc.service.activity;

import java.util.Set;

import org.springframework.data.domain.PageRequest;

import com.sap.csc.domain.model.c4c.activity.C4CActivity;
import com.sap.csc.domain.model.dto.response.c4c.C4CCollectionEntry;

public interface ActivityService {

	Set<C4CActivity> findByOwner(String openID);

	C4CCollectionEntry<C4CActivity> findByOwner(String openID, PageRequest pageRequest);

	Set<C4CActivity> findByCustomerAndOwner(String openID, String customerID);

	C4CCollectionEntry<C4CActivity> findByCustomerAndOwner(String openID, String customerID, PageRequest pageRequest);

}
