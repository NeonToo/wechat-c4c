package com.sap.csc.domain.model.dto.response.product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;

import com.sap.csc.domain.model.c4c.product.C4CProduct;
import com.sap.csc.domain.model.dto.response.GeneralCollectionResponse;
import com.sap.csc.domain.model.dto.response.c4c.C4CCollectionEntry;

public class ProductCollectionResponse extends GeneralCollectionResponse<ProductResponse> {

	private static final long serialVersionUID = 2296829293865295343L;

	public ProductCollectionResponse(Set<C4CProduct> products) {
		super(products.stream().map(product -> new ProductResponse(product)).collect(Collectors.toList()));
	}

	public ProductCollectionResponse(List<C4CProduct> products) {
		super(products.stream().map(product -> new ProductResponse(product)).collect(Collectors.toList()));
	}

	public ProductCollectionResponse(C4CCollectionEntry<C4CProduct> entry, PageRequest pageRequest) {
		super(entry.getResults().stream().map(product -> new ProductResponse(product)).collect(Collectors.toList()),
				pageRequest, entry.getCount());
	}

	public ProductCollectionResponse(@SuppressWarnings("rawtypes") Collection emptyCollection) {
		super(new ArrayList<>());
	}

}
