package com.sap.csc.web.controller.product;

import java.util.Optional;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sap.csc.domain.model.c4c.product.C4CProduct;
import com.sap.csc.domain.model.dto.response.c4c.C4CCollectionEntry;
import com.sap.csc.domain.model.dto.response.product.ProductCollectionResponse;
import com.sap.csc.domain.model.dto.response.product.ProductResponse;
import com.sap.csc.service.product.ProductService;
import com.sap.csc.web.controller.GeneralController;

@RestController
@RequestMapping("/products")
public class ProductController extends GeneralController {

	private final ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public ProductCollectionResponse findAll(@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer size, @RequestParam(required = false) String category) {
		Optional<String> openID = super.getCurrentUserOpenID();

		if (openID.isPresent()) {
			if (page == null) {
				Set<C4CProduct> products = null;

				if (StringUtils.isNotBlank(category)) {
					products = productService.finaByCategory(openID.get(), category);
				} else {
					products = productService.findAll(openID.get());
				}

				if (CollectionUtils.isNotEmpty(products)) {
					return new ProductCollectionResponse(products);
				}
			} else {
				final PageRequest pageRequest = new PageRequest(page, size);
				C4CCollectionEntry<C4CProduct> entry = null;

				if (StringUtils.isNotBlank(category)) {
					entry = productService.finaByCategory(openID.get(), pageRequest, category);
				} else {
					entry = productService.findAll(openID.get(), pageRequest);
				}

				if (entry != null) {
					return new ProductCollectionResponse(entry, pageRequest);
				}
			}
		}

		return new ProductCollectionResponse(CollectionUtils.EMPTY_COLLECTION);
	}

	@GetMapping("/{id}")
	public ProductResponse findByID(@PathVariable String id) {
		Optional<String> openID = super.getCurrentUserOpenID();

		if (openID.isPresent()) {
			Optional<C4CProduct> product = productService.findByID(openID.get(), id);

			if (product.isPresent()) {
				return new ProductResponse(product.get());
			}
		}

		return null;
	}

}
