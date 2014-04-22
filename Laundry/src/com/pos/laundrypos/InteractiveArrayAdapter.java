package com.pos.laundrypos;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

/**
 * @author srinivasan
 *
 */
public class InteractiveArrayAdapter extends ArrayAdapter<ModelExtra> {


	static class ViewHolder {
		protected CheckBox checkbox;
		protected TextView text;
		protected TextView price;
	}
	private final Activity context;

	private final List<ModelExtra> list;

	public InteractiveArrayAdapter(Activity context, List<ModelExtra> list) {
		super(context, R.layout.extralistrow, list);
		this.context = context;
		this.list = list;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		if (convertView == null) {
			LayoutInflater inflator = context.getLayoutInflater();
			view = inflator.inflate(R.layout.extralistrow, null);
			final ViewHolder viewHolder = new ViewHolder();
			viewHolder.text = (TextView) view.findViewById(R.id.label);
			viewHolder.price = (TextView) view.findViewById(R.id.label2);
			viewHolder.checkbox = (CheckBox) view.findViewById(R.id.check);
			viewHolder.checkbox
					.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

						@Override
						public void onCheckedChanged(CompoundButton buttonView,
								boolean isChecked) {
							ModelExtra element = (ModelExtra) viewHolder.checkbox
									.getTag();
							element.setSelected(buttonView.isChecked());
										}
					});
			view.setTag(viewHolder);
			viewHolder.checkbox.setTag(list.get(position));
		} else {
			view = convertView;
			((ViewHolder) view.getTag()).checkbox.setTag(list.get(position));
		}
		ViewHolder holder = (ViewHolder) view.getTag();
		String rowText = list.get(position).getName();
		
		holder.text.setText(rowText);
		holder.price.setText("$"+ ""+list.get(position).getPrice());
		holder.checkbox.setChecked(list.get(position).isSelected());
		
		return view;
	}
}