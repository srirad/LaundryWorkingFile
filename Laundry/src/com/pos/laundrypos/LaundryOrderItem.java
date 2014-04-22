package com.pos.laundrypos;

import android.graphics.Bitmap;

/**
 * 
 * @author manish.s
 *
 */

public class LaundryOrderItem {
	
	int category;
	String catString;
	
	int itemTypeNum;
	String itemTypeString;
	
	int itemSize;
	String sizeString;
	
	int itemMaterial;
	String materialString;
	
	int itemAddon;
	String addonString;
	
	int itemOthers;
	String othersString;
	
	String brandName;
	String remarks;
	
	int orderType;
	int quantity;
	
	int color;
	float price;
	
	int orderNum;

	public LaundryOrderItem(int order) {
		super();
		orderNum = order + 1;
	}
	
	public void setCategory(int num, String label){
	
		category = num;
		catString = label;
	}
	
	public void setItemType(int num, String label){
		
		itemTypeNum = num;
		itemTypeString = label;
	}
	
	public void setSize(int num, String label){
		
		category = num;
		sizeString = label;
	}
	
	public void setMaterial(int num, String label){
		
		category = num;
		materialString = label;
	}
	
	public void setAddon(int num, String label){
		
		category = num;
		addonString = label;
	}
	
	public void setOthers(int num, String label){
		
		category = num;
		othersString = label;
	}
	
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getOrderType() {
		return orderType;
	}

	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	public String getProductSummary(){
		return "Hello";
	}

	@Override
	public String toString() {
		return orderNum + "  " + catString + " - " + itemTypeString + " - " + sizeString ;
	}
	
	
	
}
