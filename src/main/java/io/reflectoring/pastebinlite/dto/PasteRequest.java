package io.reflectoring.pastebinlite.dto;

public class PasteRequest {

	  	private String content;
	    private Integer ttlSeconds;
	    private Integer maxViews;

	    public String getContent() {
	        return content;
	    }

	    public Integer getTtlSeconds() {
	        return ttlSeconds;
	    }

	    public Integer getMaxViews() {
	        return maxViews;
	    }

	    public void setContent(String content) {
	        this.content = content;
	    }

	    public void setTtlSeconds(Integer ttlSeconds) {
	        this.ttlSeconds = ttlSeconds;
	    }

	    public void setMaxViews(Integer maxViews) {
	        this.maxViews = maxViews;
	    }
}
