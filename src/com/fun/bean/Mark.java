package com.fun.bean;
import java.util.List;


public class Mark {
	
	private String title;
	private String url;
	private List<String> sonUrls;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<String> getSonUrls() {
		return sonUrls;
	}
	public void setSonUrls(List<String> sonUrls) {
		this.sonUrls = sonUrls;
	}
	
	@Override
	public String toString() {
		return "[标题是"+title+",链接是"+url+"]";
	}
	
}
