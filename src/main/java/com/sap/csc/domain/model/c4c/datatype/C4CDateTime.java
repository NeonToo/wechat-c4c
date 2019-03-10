package com.sap.csc.domain.model.c4c.datatype;

import java.io.Serializable;

/**
 * @author I326950
 */
public class C4CDateTime implements Serializable {

	private static final long serialVersionUID = 4388517731946385782L;

	private String timeZoneCode;
	private Boolean daylightSavingTimeIndicator;
	private String content;

	public String getTimeZoneCode() {
		return timeZoneCode;
	}

	public void setTimeZoneCode(String timeZoneCode) {
		this.timeZoneCode = timeZoneCode;
	}

	public Boolean getDaylightSavingTimeIndicator() {
		return daylightSavingTimeIndicator;
	}

	public void setDaylightSavingTimeIndicator(Boolean daylightSavingTimeIndicator) {
		this.daylightSavingTimeIndicator = daylightSavingTimeIndicator;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
