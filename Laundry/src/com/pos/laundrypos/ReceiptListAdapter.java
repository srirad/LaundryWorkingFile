/** Laundry POS
 * @Author Srinivasan
 */
package com.pos.laundrypos;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pos.laundrypos.ExtraListAdapter.ViewHolder;

/**
 * @author srinivasan
 * 
 */
public class ReceiptListAdapter extends ArrayAdapter<ModelLaundryItem> {

	private final Activity context;

	private final List<ModelLaundryItem> list;

	public ReceiptListAdapter(Activity context, List<ModelLaundryItem> list) {
		super(context, R.layout.receiptlistrow, list);
		this.context = context;
		this.list = list;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;

		if (view == null) {
			LayoutInflater inflator = context.getLayoutInflater();
			view = inflator.inflate(R.layout.receiptlistrow, null);
		}
		ModelLaundryItem aItem = list.get(position);

		if (aItem != null && view != null) {
			TextView qty = (TextView) view.findViewById(R.id.itemQty);
			TextView name = (TextView) view.findViewById(R.id.itemName);
			TextView price = (TextView) view.findViewById(R.id.itemPrice);
			
			if (name != null) {
				name.setText(aItem.itemName);
			}
			if (price != null) {
				 String itemPrice = "$ " + String.valueOf(list.get(position).getTotalQty());
				qty.setText(itemPrice);
			}
			if (price != null) {
				 String itemPrice = "$ " + String.valueOf(list.get(position).getTotalPrice());
				 qty.setText(itemPrice);
			}

			/*
			 * viewHolder = (ViewHolder) convertView.getTag(); String itemName =
			 * list.get(position).itemName +
			 * list.get(position).getAllExtraString();
			 * 
			 * String itemPrice = "$ " +
			 * String.valueOf(list.get(position).getTotalPrice()); String
			 * itemQty = String.valueOf(list.get(position).getTotalQty());
			 * 
			 * viewHolder.text.setText(itemName);
			 * viewHolder.price.setText(itemPrice);
			 * viewHolder.qty.setText(itemQty);
			 * 
			 * viewHolder.text.setHeight(40);
			 * viewHolder.text.setMinimumHeight(130);
			 * viewHolder.price.setText(itemPrice);
			 */
		}

		return view;
	}

}