package com.sap.csc.service.product;

import java.util.Set;

import com.sap.csc.domain.model.c4c.product.C4CProductCategory;

public interface ProductCategoryService {

	public Set<C4CProductCategory> findAll(String openID);

	public Set<C4CProductCategory> findTop(String openID);

	public Set<C4CProductCategory> findChildrenByParentID(String openID, String id);
	
}
