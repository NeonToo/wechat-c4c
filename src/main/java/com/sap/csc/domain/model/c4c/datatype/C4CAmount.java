package com.sap.csc.domain.model.c4c.datatype;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author I326950
 */
public class C4CAmount implements Serializable {

	private static final long serialVersionUID = -2115664283073106765L;

	private BigDecimal content;

	private String currencyCode;

	public BigDecimal getContent() {
		return content;
	}

	public void setContent(BigDecimal content) {
		this.content = content;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

}
