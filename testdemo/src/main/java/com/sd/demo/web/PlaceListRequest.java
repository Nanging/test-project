package com.sd.demo.web;

public class PlaceListRequest {
	public String name;
	public String type;
	public int page;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	@Override
	public String toString() {
		return "PlaceListRequest [name=" + name + ", type=" + type + ", page=" + page + "]";
	}

	
}
