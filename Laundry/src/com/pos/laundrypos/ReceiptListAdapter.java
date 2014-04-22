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

	static class ViewHolder {

		protected TextView qty;
		protected TextView text;
		protected TextView price;
	}

	private final Activity context;

	private final List<ModelLaundryItem> list;

	public ReceiptListAdapter(Activity context, List<ModelLaundryItem> list) {
		super(context, R.layout.receiptlistrow, list);
		this.context = context;
		this.list = list;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		if (convertView == null) {
			LayoutInflater inflator = context.getLayoutInflater();
			view = inflator.inflate(R.layout.receiptlistrow, null);
			view.setMinimumHeight(50);
			final ViewHolder viewHolder = new ViewHolder();
			viewHolder.qty = (TextView) view.findViewById(R.id.itemQty);
			viewHolder.text = (TextView) view.findViewById(R.id.itemName);
			viewHolder.price = (TextView) view.findViewById(R.id.itemPrice);

			String itemName = list.get(position).itemName
					+ list.get(position).getAllExtraString();

			String itemPrice = "$ "
					+ String.valueOf(list.get(position).getTotalPrice());
			String itemQty = String.valueOf(list.get(position).getTotalQty());
			if (itemName != "") {
				viewHolder.text.setText(itemName);
				viewHolder.price.setText(itemPrice);
				viewHolder.qty.setText(itemQty);
				viewHolder.text.setMinimumHeight(30);
			}

			view.setTag(viewHolder);

		} else {
			view = convertView;

		}

		return view;
	}
}