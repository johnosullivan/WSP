package service;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Link")
public class Link {
	private String ref;
	private String uri;
	private String method;
	private String mediatype;
	private String payload;
	
	public Link() {}
	
	public Link(String ref, String uri, String method, String mediatype) {
		this.ref = ref;
		this.uri = uri;
		this.method = method;
		this.mediatype = mediatype;
	}
	
	public String getPayload() {
		return payload;
	}
	public void setPayload(String payload) {
		this.payload = payload;
	}
	
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	
	public String getMediatype() {
		return mediatype;
	}
	public void setMediatype(String mediatype) {
		this.mediatype = mediatype;
	}
	
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String url) {
		this.uri = url;
	}
	
}