package com.sap.csc.service.product;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.PageRequest;

import com.sap.csc.domain.model.c4c.product.C4CProduct;
import com.sap.csc.domain.model.dto.response.c4c.C4CCollectionEntry;

public interface ProductService {
	
	public Set<C4CProduct> findAll(String openID);

	public C4CCollectionEntry<C4CProduct> findAll(String openID, PageRequest pageRequest);

	public Optional<C4CProduct> findByID(String openID, String id);

	public Set<C4CProduct> finaByCategory(String openID, String category);

	public C4CCollectionEntry<C4CProduct> finaByCategory(String openID, PageRequest pageRequest, String category);
	
}
