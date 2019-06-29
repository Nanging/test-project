package com.sd.demo.web;

import java.util.Set;

public class PlaceItem {
	public long id;
	public int affordNumber;
	public String description;
	public String location;
	public String name;
	public int price;
	public int roomNumber;
	public int size;
	public String owner;
	public String phonenumber;
	public String type;
	public Set<String> images;
	public long getId() {
		return id;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getAffordNumber() {
		return affordNumber;
	}
	public void setAffordNumber(int affordNumber) {
		this.affordNumber = affordNumber;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Set<String> getImages() {
		return images;
	}
	public void setImages(Set<String> images) {
		this.images = images;
	}
	@Override
	public String toString() {
		return "PlaceItem [id=" + id + ", affordNumber=" + affordNumber + ", description=" + description + ", location="
				+ location + ", name=" + name + ", price=" + price + ", roomNumber=" + roomNumber + ", size=" + size
				+ ", owner=" + owner + ", type=" + type + ", images=" + images + "]";
	}
	
}
