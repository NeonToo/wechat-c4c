package com.sap.csc.domain.model.c4c.datatype;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author I326950
 */
public class C4CQuantity implements Serializable {

	private static final long serialVersionUID = -5726506213668798894L;

	private BigDecimal content;

	private String unitCode;

	public BigDecimal getContent() {
		return content;
	}

	public void setContent(BigDecimal content) {
		this.content = content;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

}
