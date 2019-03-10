package com.sap.csc.web.controller.sales;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sap.csc.domain.exception.CommonApplicationException;
import com.sap.csc.domain.model.c4c.sales.C4CSalesOrder;
import com.sap.csc.domain.model.c4c.sales.C4CSalesOrderItem;
import com.sap.csc.domain.model.c4c.sales.C4CSalesOrderParty;
import com.sap.csc.domain.model.dto.response.c4c.C4CCollectionEntry;
import com.sap.csc.domain.model.dto.response.sales.SalesOrderCollectionResponse;
import com.sap.csc.domain.model.dto.response.sales.SalesOrderItemResponse;
import com.sap.csc.domain.model.dto.response.sales.SalesOrderPartyResponse;
import com.sap.csc.domain.model.dto.response.sales.SalesOrderResponse;
import com.sap.csc.service.sales.SalesOrderService;
import com.sap.csc.web.controller.GeneralController;

@RestController
public class SalesOrderController extends GeneralController {

	private final static String DECODE_NAME = "UTF-8";

	private final SalesOrderService salesOrderService;

	@Autowired
	public SalesOrderController(SalesOrderService salesOrderService) {
		this.salesOrderService = salesOrderService;
	}

	@GetMapping("/orders")
	public SalesOrderCollectionResponse findByOwner(@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer size) {
		Optional<String> openID = super.getCurrentUserOpenID();

		if (openID.isPresent()) {
			if (page == null) {
				Set<C4CSalesOrder> orders = salesOrderService.findByOwner(openID.get());

				if (CollectionUtils.isNotEmpty(orders)) {
					return new SalesOrderCollectionResponse(orders);
				}
			} else {
				final PageRequest pageRequest = new PageRequest(page, size);
				C4CCollectionEntry<C4CSalesOrder> entry = salesOrderService.findByOwner(openID.get(), pageRequest);

				if (entry != null) {
					return new SalesOrderCollectionResponse(entry, pageRequest);
				}
			}
		}

		return null;
	}

	@GetMapping("/orders/{id}")
	public SalesOrderResponse findByID(@PathVariable String id) {
		Optional<String> openID = super.getCurrentUserOpenID();

		if (openID.isPresent()) {
			Optional<C4CSalesOrder> order = salesOrderService.findByID(openID.get(), id);

			if (order.isPresent()) {
				return new SalesOrderResponse(order.get());
			}
		}

		return null;
	}

	@GetMapping("/orders/order")
	public SalesOrderResponse findByUuidOrURI(@RequestParam(required = false) String uuid,
			@RequestParam(required = false) String uri) {
		Optional<String> openID = super.getCurrentUserOpenID();

		if (openID.isPresent()) {
			Optional<C4CSalesOrder> order = null;

			if (StringUtils.isNotBlank(uuid)) {
				order = salesOrderService.findByUUID(openID.get(), uuid);
			}

			if (StringUtils.isBlank(uuid) && StringUtils.isNotBlank(uri)) {
				try {
					order = salesOrderService.findByURI(openID.get(), URLDecoder.decode(uri, DECODE_NAME));
				} catch (UnsupportedEncodingException e) {
					throw new CommonApplicationException("UNSUPPORTED_ENCODING", "The encoding is unsupported");
				}
			}

			if (order.isPresent()) {
				return new SalesOrderResponse(order.get());
			}
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/orders/{id}/items")
	public Collection<SalesOrderItemResponse> findItems(@PathVariable String id,
			@RequestParam(required = false) String uuid, @RequestParam(required = false) String uri) {
		Optional<String> openID = super.getCurrentUserOpenID();

		if (openID.isPresent()) {
			Set<C4CSalesOrderItem> items = null;

			if (StringUtils.isNotBlank(uuid)) {
				items = salesOrderService.findItems(openID.get(), uuid);
			}

			if (StringUtils.isBlank(uuid) && StringUtils.isNotBlank(uri)) {
				try {
					items = salesOrderService.findItemsByURI(openID.get(), URLDecoder.decode(uri, DECODE_NAME));
				} catch (UnsupportedEncodingException e) {
					throw new CommonApplicationException("UNSUPPORTED_ENCODING", "The encoding is unsupported");
				}
			}

			if (CollectionUtils.isNotEmpty(items)) {
				return items.stream().map(item -> new SalesOrderItemResponse(item)).collect(Collectors.toList());
			}
		}

		return CollectionUtils.EMPTY_COLLECTION;
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/orders/{id}/parties")
	public Collection<SalesOrderPartyResponse> findPartiesByURI(@PathVariable String id, @RequestParam String uri) {
		Optional<String> openID = super.getCurrentUserOpenID();

		if (openID.isPresent()) {
			try {
				Set<C4CSalesOrderParty> parties = salesOrderService.findPartiesByURI(openID.get(),
						URLDecoder.decode(uri, DECODE_NAME));

				if (!CollectionUtils.isEmpty(parties)) {
					return parties.stream().map(party -> new SalesOrderPartyResponse(party))
							.collect(Collectors.toList());
				}
			} catch (UnsupportedEncodingException e) {
				throw new CommonApplicationException("UNSUPPORTED_ENCODING", "The encoding is unsupported");
			}
		}

		return CollectionUtils.EMPTY_COLLECTION;
	}

	@GetMapping("/customers/{customerID}/orders")
	public SalesOrderCollectionResponse findByCustomerAndOwner(@PathVariable String customerID,
			@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) {
		Optional<String> openID = super.getCurrentUserOpenID();

		if (openID.isPresent()) {
			if (page == null) {
				Set<C4CSalesOrder> orders = salesOrderService.findByCustomerAndOwner(openID.get(), customerID);

				if (CollectionUtils.isNotEmpty(orders)) {
					return new SalesOrderCollectionResponse(orders);
				}
			} else {
				final PageRequest pageRequest = new PageRequest(page, size);
				C4CCollectionEntry<C4CSalesOrder> entry = salesOrderService.findByCustomerAndOwner(openID.get(),
						customerID, pageRequest);

				if (entry != null) {
					return new SalesOrderCollectionResponse(entry, pageRequest);
				}
			}
		}

		return null;
	}
}
