package com.sap.csc.domain.model.dto.response.activity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;

import com.sap.csc.domain.model.c4c.activity.C4CActivity;
import com.sap.csc.domain.model.dto.response.GeneralCollectionResponse;
import com.sap.csc.domain.model.dto.response.c4c.C4CCollectionEntry;

public class ActivityCollectionResponse extends GeneralCollectionResponse<ActivityResponse> {

	private static final long serialVersionUID = -4713504596887457226L;

	public ActivityCollectionResponse(@SuppressWarnings("rawtypes") Collection emptyCollection) {
		super(new ArrayList<>());
	}

	public ActivityCollectionResponse(Set<C4CActivity> activities) {
		super(activities.stream().map(activity -> new ActivityResponse(activity)).collect(Collectors.toList()));
	}

	public ActivityCollectionResponse(List<C4CActivity> activities) {
		super(activities.stream().map(activity -> new ActivityResponse(activity)).collect(Collectors.toList()));
	}

	public ActivityCollectionResponse(C4CCollectionEntry<C4CActivity> entry, PageRequest pageRequest) {
		super(entry.getResults().stream().map(activity -> new ActivityResponse(activity)).collect(Collectors.toList()),
				pageRequest, entry.getCount());
	}
}
