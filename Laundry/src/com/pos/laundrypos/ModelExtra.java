package com.pos.laundrypos;

/**
 * @author srinivasan
 * 
 */
public class ModelExtra {
	boolean selected;

	public enum eExtraType {
		size, material, addOn, other
	};

	String name = "";

	float price = 0.0f;
	eExtraType extraType = eExtraType.size;

	public ModelExtra(String aName, float aPrice, eExtraType aType) {

		super();
		this.price = aPrice;
		this.name = aName;
		selected = false;

	}

	public void setPrice(float aPrice) {
		price = aPrice;
	}

	public void setName(String aName) {
		name = aName;
	}

	public void setExtraType(eExtraType aExType) {
		extraType = aExType;
	}

	public String getName() {
		return name;
	}

	public float getPrice() {
		return price;
	}

	public eExtraType getType() {
		return extraType;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

}
