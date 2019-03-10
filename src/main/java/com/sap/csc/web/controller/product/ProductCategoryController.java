package com.sap.csc.web.controller.product;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sap.csc.domain.model.c4c.product.C4CProductCategory;
import com.sap.csc.domain.model.dto.response.product.ProductCategoryResponse;
import com.sap.csc.service.product.ProductCategoryService;
import com.sap.csc.web.controller.GeneralController;

@RestController
@RequestMapping("/categories")
public class ProductCategoryController extends GeneralController {

	private final ProductCategoryService productCategoryService;
	
	@Autowired
	public ProductCategoryController(ProductCategoryService productCategoryService) {
		this.productCategoryService = productCategoryService;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping
	public Collection<ProductCategoryResponse> findAll(@RequestParam(required = false) String level) {
		Optional<String> openID = super.getCurrentUserOpenID();
		
		if (openID.isPresent()) {
			Set<C4CProductCategory> categories = null;
			
			if(StringUtils.equals(level, "top")) {
				categories = productCategoryService.findTop(openID.get());
			} else {
				categories = productCategoryService.findAll(openID.get());
			}
			
			if (CollectionUtils.isNotEmpty(categories)) {
				return categories.stream().map(category -> new ProductCategoryResponse(category)).collect(Collectors.toList());
			}
		}
		
		return CollectionUtils.EMPTY_COLLECTION;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/{id}")
	public Collection<ProductCategoryResponse> findChildrenByParentID(@PathVariable String id) {
		Optional<String> openID = super.getCurrentUserOpenID();
		
		if (openID.isPresent()) {
			Set<C4CProductCategory> children = productCategoryService.findChildrenByParentID(openID.get(), id);
			
			if (CollectionUtils.isNotEmpty(children)) {
				return children.stream().map(category -> new ProductCategoryResponse(category)).collect(Collectors.toList());
			}
		}
		
		return CollectionUtils.EMPTY_COLLECTION;
	}
	
}
