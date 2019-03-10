package com.sap.csc.service.sales;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.PageRequest;

import com.sap.csc.domain.model.c4c.sales.C4CSalesOrder;
import com.sap.csc.domain.model.c4c.sales.C4CSalesOrderItem;
import com.sap.csc.domain.model.c4c.sales.C4CSalesOrderParty;
import com.sap.csc.domain.model.dto.response.c4c.C4CCollectionEntry;

public interface SalesOrderService {

	public Optional<C4CSalesOrder> findByUUID(String openID, String uuid);

	public Optional<C4CSalesOrder> findByID(String openID, String id);

	public Set<C4CSalesOrder> findByOwner(String openID);

	public C4CCollectionEntry<C4CSalesOrder> findByOwner(String openID, PageRequest pageRequest);

	public Set<C4CSalesOrderItem> findItems(String openID, String uuid);

	public Set<C4CSalesOrderItem> findItemsByURI(String openID, String url);

	public Set<C4CSalesOrderParty> findPartiesByURI(String openID, String url);

	public Optional<C4CSalesOrder> findByURI(String openID, String uri);

	public Set<C4CSalesOrder> findByCustomerAndOwner(String openID, String customerID);

	public C4CCollectionEntry<C4CSalesOrder> findByCustomerAndOwner(String openID, String customerID,
			PageRequest pageRequest);

}
