package com.sap.csc.domain.model.dto.response.sales;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;

import com.sap.csc.domain.model.c4c.sales.C4CSalesOrder;
import com.sap.csc.domain.model.dto.response.GeneralCollectionResponse;
import com.sap.csc.domain.model.dto.response.c4c.C4CCollectionEntry;

/**
 * @author I326950
 */
public class SalesOrderCollectionResponse extends GeneralCollectionResponse<SalesOrderResponse> {

	private static final long serialVersionUID = -8898161267318298394L;
	
	public SalesOrderCollectionResponse(@SuppressWarnings("rawtypes") Collection emptyCollection) {
		super(new ArrayList<>());
	}
	
	public SalesOrderCollectionResponse(Set<C4CSalesOrder> orders) {
		super(orders.stream().map(order -> new SalesOrderResponse(order)).collect(Collectors.toList()));
	}

	public SalesOrderCollectionResponse(List<C4CSalesOrder> orders) {
		super(orders.stream().map(order -> new SalesOrderResponse(order)).collect(Collectors.toList()));
	}

	public SalesOrderCollectionResponse(C4CCollectionEntry<C4CSalesOrder> entry, PageRequest pageRequest) {
		super(entry.getResults().stream().map(order -> new SalesOrderResponse(order)).collect(Collectors.toList()),
				pageRequest, entry.getCount());
	}

}
