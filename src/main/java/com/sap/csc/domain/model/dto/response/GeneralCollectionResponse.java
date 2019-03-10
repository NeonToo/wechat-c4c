package com.sap.csc.domain.model.dto.response;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

/**
 * @author I326950
 */
public class GeneralCollectionResponse<T> extends PageImpl<T> implements Serializable {

	private static final long serialVersionUID = -7471608240587326248L;

	private LinkEntry __link;
	
	public GeneralCollectionResponse(List<T> data) {
		super(data);
	}
	
	public GeneralCollectionResponse(List<T> data, PageRequest pageRequest, long total) {
		super(data, pageRequest, total);
	}

	public LinkEntry getLink() {
		return __link;
	}

	public void setLink(LinkEntry __link) {
		this.__link = __link;
	}

	public static class LinkEntry implements Serializable {
 
		private static final long serialVersionUID = 9026147598461556904L;

		private String rel;

		private String href;

		public String getRel() {
			return rel;
		}

		public void setRel(String rel) {
			this.rel = rel;
		}

		public String getHref() {
			return href;
		}

		public void setHref(String href) {
			this.href = href;
		}
	}

}
