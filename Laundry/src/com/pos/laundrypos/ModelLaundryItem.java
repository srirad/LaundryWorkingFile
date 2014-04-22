package com.pos.laundrypos;

import java.util.ArrayList;
import java.util.Locale;

import com.pos.laundrypos.ModelExtra.eExtraType;

import android.annotation.SuppressLint;

/**
 * @author srinivasan
 * 
 */
@SuppressLint("DefaultLocale")
public class ModelLaundryItem {

	/*
	 * Resource id and layout id should be different because the same resource
	 * can be used in multiple places
	 */

	int itemLayoutId;
	String itemName;
	int itemResourceId;
	float itemTotalPrice;

	float dryPrice;
	float wetPrice;
	float handPrice;
	float pressPrice;

	ArrayList<ModelExtra> sizeArray = new ArrayList<ModelExtra>();
	ArrayList<ModelExtra> materialArray = new ArrayList<ModelExtra>();
	ArrayList<ModelExtra> addOnArray = new ArrayList<ModelExtra>();
	ArrayList<ModelExtra> otherArray = new ArrayList<ModelExtra>();

	public enum eCategoryType {
		lady, man, house, other
	};

	public enum eCleaningType {
		wet, dry, hand, press
	};

	eCleaningType cleaningType;
	eCategoryType categoryType;

	int qty;

	public ModelLaundryItem(int aResourceId, String aName) {
		super();
		/* Populating with default values */

		this.itemLayoutId = -1;
		this.itemTotalPrice = 0.0f;

		/* for the image and the name of the item */
		this.itemResourceId = aResourceId;

		if (aName.length() > 10)
			aName = aName.substring(0, 10 - 3) + "...";
		this.itemName = aName.toUpperCase(Locale.getDefault());
		cleaningType = eCleaningType.dry;
		categoryType = eCategoryType.lady;

	}

	public ModelLaundryItem(int aResourceId, String aItemName,
			eCategoryType aCategoryType,

			float aDryOnlyPrice, float aWetOnlyPrice, float aPressOnlyPrice,
			float aHandOnlyPrice) {
		super();

		this.itemResourceId = aResourceId;

		if (aItemName.length() > 10)
			aItemName = aItemName.substring(0, 10 - 3) + "...";
		this.itemName = aItemName.toUpperCase(Locale.getDefault());

		this.dryPrice = aDryOnlyPrice;
		this.wetPrice = aWetOnlyPrice;
		this.handPrice = aHandOnlyPrice;
		this.pressPrice = aPressOnlyPrice;
		this.qty = 1;

		/* runtime */
		this.cleaningType = eCleaningType.dry;
		this.categoryType = eCategoryType.lady;
		this.itemLayoutId = -1; // will be calculated during runtime
	}

	void setLayoutId(int aVal) {
		this.itemLayoutId = aVal;
	}

	void setResourceId(int aVal) {
		this.itemResourceId = aVal;
	}

	void setTotalPrice(float aVal) {
		this.itemTotalPrice = aVal;
	}

	void addToSizeArray(ModelExtra aModelExtra) {
		sizeArray.add(aModelExtra);

	}

	void addToMaterialArray(ModelExtra aModelExtra) {
		materialArray.add(aModelExtra);

	}

	void addToAddOnArray(ModelExtra aModelExtra) {
		addOnArray.add(aModelExtra);

	}

	void addToOtherArray(ModelExtra aModelExtra) {
		otherArray.add(aModelExtra);

	}

	public ArrayList<ModelExtra> getSizeArr() {
		return sizeArray;

	}

	public ArrayList<ModelExtra> getMaterialArr() {
		return materialArray;

	}

	public ArrayList<ModelExtra> getAddOnArr() {
		return addOnArray;

	}

	public ArrayList<ModelExtra> getOtherArr() {
		return otherArray;

	}

	public void addSize(String aName, float aPrice) {
		ModelExtra sizeMdl = new ModelExtra(aName, aPrice, eExtraType.size);
		sizeArray.add(sizeMdl);
	}

	public void addMaterial(String aName, float aPrice) {
		ModelExtra materialMdl = new ModelExtra(aName, aPrice,
				eExtraType.material);
		materialArray.add(materialMdl);
	}

	public void addAddOn(String aName, float aPrice) {
		ModelExtra addOnMdl = new ModelExtra(aName, aPrice, eExtraType.addOn);
		addOnArray.add(addOnMdl);
	}

	public void addOther(String aName, float aPrice) {
		ModelExtra otherMdl = new ModelExtra(aName, aPrice, eExtraType.other);
		otherArray.add(otherMdl);
	}

	public float getTotalPrice() {
		itemTotalPrice = 0;
		switch (cleaningType) {
		case dry:
			itemTotalPrice = itemTotalPrice + dryPrice;

			break;
		case wet:
			itemTotalPrice = itemTotalPrice + wetPrice;

			break;
		case hand:
			itemTotalPrice = itemTotalPrice + handPrice;

			break;
		case press:
			itemTotalPrice = itemTotalPrice + pressPrice;

			break;
		default:
			break;
		}

	this.calXtraPrice(sizeArray);
	this.calXtraPrice(materialArray);
	this.calXtraPrice(addOnArray);
	this.calXtraPrice(otherArray);
	

		return (qty *itemTotalPrice);
	}

	/*public String getExtraName() {
		
		
	}*/
	
	public void calXtraPrice(ArrayList<ModelExtra> aXtraArr) {

		for (int i = 0; i < aXtraArr.size(); i++) {

			ModelExtra aXtra = aXtraArr.get(i);

			if (aXtra.isSelected()) {

				itemTotalPrice = itemTotalPrice + aXtra.price;
			}
		}
	}
}
