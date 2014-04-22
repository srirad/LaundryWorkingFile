package com.pos.laundrypos;

import android.graphics.Bitmap;

/**
 * @author srinivasan
 *
 */
public class Item {
	String[] addonList;
	Bitmap image;
	
	int itemLayoutId;
	int itemResourceId;
	String[] materialList;
	String[] othersList;
	String[] sizeList;
	String title;
	
	public Item() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public Item(Bitmap image, String title, String[] sizes, String[] materials,
			String[] addons, String[] others) {
		super();
		this.image = image;
		this.title = title;
		this.sizeList = sizes;
		this.materialList = materials;
		this.addonList = addons;
		this.othersList = others;
		this.itemResourceId = R.drawable.g_shirt;
		this.itemLayoutId = 1; 
	}

	public String[] getAddonList() {
		return addonList;
	}
	
	public Bitmap getImage() {
		return image;
	}
	
	public String[] getMaterialList() {
		return materialList;
	}
	public String[] getOthersList() {
		return othersList;
	}
	public String[] getSizeList() {
		return sizeList;
	}
	public String getTitle() {
		return title;
	}
	
	public void setAddonList(String[] addonList) {
		this.addonList = addonList;
	}
	public void setImage(Bitmap image) {
		this.image = image;
	}
	public void setItemLayoutId (int aLayId) {
		
		this.itemLayoutId = aLayId;
	}
	public void setItemResourceId (int aResId) {
		this.itemResourceId = aResId;
	}
	public void setMaterialList(String[] materialList) {
		this.materialList = materialList;
	}
	public void setOthersList(String[] othersList) {
		this.othersList = othersList;
	}
	public void setSizeList(String[] sizeList) {
		this.sizeList = sizeList;
	}
	public void setTitle(String title) {
		this.title = title;
	}

}
