package com.pos.laundrypos;

/**
 * 
 * @author manish.s
 * 
 */

public class LaundryItem {

	/*
	 * Resource id and layout id should be different because the same resource
	 * can be used in multiple places
	 */

	int itemLayoutId; // to keep track of the item in the view.
	int itemResourceId; // to assign bg values to the dynamic item.
	float itemIndiePrice; // independent of the add-ons.
	float itemTotalPrice; // inclusive of everything
	String itemName;

	public LaundryItem(int aResourceId, String aName) {
		super();
		/* Populating with default values */
		this.itemIndiePrice = 0.0f;
		this.itemLayoutId = -1;
		this.itemTotalPrice = 0.0f;
		
		/* for the image and the name of the item */
		this.itemResourceId = aResourceId;
		
		if (aName.length() >10)
		aName = aName.substring(0, 10-3) + "...";
		this.itemName = aName.toUpperCase();

	}

	void setLayoutId(int aVal) {
		this.itemLayoutId = aVal;
	}

	void setResourceId(int aVal) {
		this.itemResourceId = aVal;
	}

	void setIndiePrice(float aVal) {
		this.itemIndiePrice = aVal;
	}

	void setTotalPrice(float aVal) {
		this.itemTotalPrice = aVal;
	}

}
