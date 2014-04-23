/** Laundry POS
 * @Author Srinivasan
 */
package com.pos.laundrypos;

import java.util.ArrayList;

/**
 * @author srinivasan
 *
 */
public class ModelReceipt {
	
	ArrayList<ModelLaundryItem> launItemArr = new ArrayList<ModelLaundryItem>();
	float totalReceiptPrice;
	
	
	
	public ModelReceipt () {
		super();
	}
	
	public void deleteItemAt(int aVal) {
		
		launItemArr.remove(aVal);
	}
	
	public void addItemToRec(ModelLaundryItem aItem) {
		launItemArr.add(aItem);
	} 
	
	public float getTotalPrice() {
		totalReceiptPrice = 0.0f;
		
		for(int i = 0; i < launItemArr.size();i++) {
			
			ModelLaundryItem aItem = launItemArr.get(i);
			
			totalReceiptPrice =totalReceiptPrice +aItem.getTotalPrice();
		}
		
		return totalReceiptPrice;
	}
	
	public ArrayList<ModelLaundryItem> getAllItem() {
		
		return launItemArr;
	}
		
}
