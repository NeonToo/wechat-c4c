package com.sap.csc.domain.model.enumeration.c4c;

import com.sap.csc.domain.model.enumeration.ScalableEnum;

public enum SalesOrderStatus implements ScalableEnum {

	UNKNOWN(0, "未知"), NOT_STARTED(1, "未开始"), IN_PROCESS(2, "处理中"), FINISHED(3, "已完成"), NOT_RELEVANT(4, "不相关"), INTTERRUPTED(5, "中断");

	private final int value;

	private final String description;

	SalesOrderStatus(int value, String description) {
		this.value = value;
		this.description = description;
	}
	
	public static SalesOrderStatus getValueOf(int value) {
		for(SalesOrderStatus status: values()) {
			if(status.value == value) {
				return status;
			}
		}
		return null;
	}

	@Override
	public int getValue() {
		return value;
	}

	@Override
	public String getDescription() {
		return description;
	}
}
