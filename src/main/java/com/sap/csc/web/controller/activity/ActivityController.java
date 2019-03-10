package com.sap.csc.web.controller.activity;

import java.util.Optional;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sap.csc.domain.model.c4c.activity.C4CActivity;
import com.sap.csc.domain.model.dto.response.activity.ActivityCollectionResponse;
import com.sap.csc.domain.model.dto.response.c4c.C4CCollectionEntry;
import com.sap.csc.service.activity.ActivityService;
import com.sap.csc.web.controller.GeneralController;

@RestController
public class ActivityController extends GeneralController {

	private final ActivityService activityService;

	public ActivityController(ActivityService activityService) {
		this.activityService = activityService;
	}

	@GetMapping("/activities")
	public ActivityCollectionResponse findByOwner(@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer size) {
		Optional<String> openID = super.getCurrentUserOpenID();

		if (openID.isPresent()) {
			if (page == null) {
				Set<C4CActivity> activies = activityService.findByOwner(openID.get());

				if (CollectionUtils.isNotEmpty(activies)) {
					return new ActivityCollectionResponse(activies);
				}
			} else {
				final PageRequest pageRequest = new PageRequest(page, size);
				C4CCollectionEntry<C4CActivity> entry = activityService.findByOwner(openID.get(), pageRequest);

				if (entry != null) {
					return new ActivityCollectionResponse(entry, pageRequest);
				}
			}
		}

		return null;
	}

	@GetMapping("/customers/{customerID}/activities")
	public ActivityCollectionResponse findByCustomerAndOwner(@PathVariable String customerID,
			@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) {
		Optional<String> openID = super.getCurrentUserOpenID();

		if (openID.isPresent()) {
			if (page == null) {
				Set<C4CActivity> activies = activityService.findByCustomerAndOwner(openID.get(), customerID);

				if (CollectionUtils.isNotEmpty(activies)) {
					return new ActivityCollectionResponse(activies);
				}
			} else {
				final PageRequest pageRequest = new PageRequest(page, size);
				C4CCollectionEntry<C4CActivity> entry = activityService.findByCustomerAndOwner(openID.get(), customerID,
						pageRequest);

				if (entry != null) {
					return new ActivityCollectionResponse(entry, pageRequest);
				}
			}
		}

		return null;
	}

}
