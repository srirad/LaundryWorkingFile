package com.pos.laundrypos;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * @author srinivasan
 *
 */
public class ColorGridViewAdapter extends ArrayAdapter<Bitmap> {
	static class RecordHolder {
		ImageView imageItem;
		TextView txtTitle;

	}
	Context context;
	ArrayList<Bitmap> data = new ArrayList<Bitmap>();

	int layoutResourceId;

	public ColorGridViewAdapter(Context context, int layoutResourceId,
			ArrayList<Bitmap> data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		RecordHolder holder = null;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);

			holder = new RecordHolder();
			holder.imageItem = (ImageView) row.findViewById(R.id.color_image);
			row.setTag(holder);
		} else {
			holder = (RecordHolder) row.getTag();
		}

		Bitmap item = data.get(position);
		holder.imageItem.setImageBitmap(item);
		return row;

	}
}