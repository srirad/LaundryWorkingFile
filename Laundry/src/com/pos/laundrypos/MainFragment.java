package com.pos.laundrypos;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class MainFragment extends Fragment {

	int mType = 1;
	View rootView;

	MainActivity_POS acti = new MainActivity_POS();
	CommonAPI commonAPI = new CommonAPI();
	ArrayAdapter<String> sizeAdapter;
	ArrayAdapter<String> materialAdapter;
	ArrayAdapter<String> othersAdapter;
	ArrayAdapter<String> addonAdapter;

	ListView materialListView;
	ListView sizeListView;
	ListView otherListView;
	ListView addOnListView;

	GridView cgv;

	public GridView gridView;

	ColorGridViewAdapter customColorGridAdapter;

	ColorGridViewAdapter colorGridAdapter;

	static ArrayList<ModelExtra> sizeArray;

	public MainFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.ordermainpage, container, false);

		materialListView = (ListView) rootView.findViewById(R.id.materialList);
		sizeListView = (ListView) rootView.findViewById(R.id.sizeList);
		otherListView = (ListView) rootView.findViewById(R.id.othersList);
		addOnListView = (ListView) rootView.findViewById(R.id.addOnList);

		cgv = (GridView) rootView.findViewById(R.id.colorGrid);

		cgv.setAdapter(colorGridAdapter);

		chooseLadies();
		return rootView;
	}

	/**
	 * 
	 */

	public void modifySizeArray(ArrayList<ModelExtra> sizeArr,
			ArrayList<ModelExtra> materialArr, ArrayList<ModelExtra> addOnArr,
			ArrayList<ModelExtra> otherArr) {

		ArrayList<ModelExtra> sizeArrFil = new ArrayList<ModelExtra>();
		
		for (int i = 0; i < sizeArr.size(); i++) {

			if (sizeArr.get(i) != null) {
				sizeArrFil.add(sizeArr.get(i));
			}
		}

		ArrayAdapter<ModelExtra> sizeAdapter = new ExtraListAdapter(
				getActivity(), sizeArrFil);
		sizeListView.setAdapter(sizeAdapter);
		ArrayAdapter<ModelExtra> materialAdapter = new ExtraListAdapter(
				getActivity(), materialArr);
		materialListView.setAdapter(materialAdapter);
		ArrayAdapter<ModelExtra> addOnAdapter = new ExtraListAdapter(
				getActivity(), addOnArr);
		addOnListView.setAdapter(addOnAdapter);
		ArrayAdapter<ModelExtra> otherAdapter = new ExtraListAdapter(
				getActivity(), otherArr);
		otherListView.setAdapter(otherAdapter);
	}

	private void chooseLadies() {

		commonAPI.setActivateVal(rootView, R.id.ladiesTab, true);
		commonAPI.setActivateVal(rootView, R.id.menTab, false);
		commonAPI.setActivateVal(rootView, R.id.houseTab, false);
		commonAPI.setActivateVal(rootView, R.id.othersTab, false);

		RelativeLayout lay = (RelativeLayout) rootView
				.findViewById(R.id.relativeLayoutWBg);
		lay.setBackgroundResource(R.drawable.l_tabbg);
		ArrayList<ModelLaundryItem> ladiesArray = ((MainActivity_POS) getActivity())
				.getLadiesArray();
		LinearLayout yourLayout = (LinearLayout) rootView
				.findViewById(R.id.itemsLinearLayout);
		yourLayout.removeAllViews();
		for (int i = 0; i < ladiesArray.size(); i++) {

			ModelLaundryItem aItem = ladiesArray.get(i);

			View headerView = View.inflate(getActivity(),
					R.layout.selectableitem, null);

			Button itemBtn = (Button) headerView.findViewById(R.id.ButtonTest);
			itemBtn.setBackgroundResource(aItem.itemResourceId);

			int aId = View.generateViewId();
			itemBtn.setId(aId);

			itemBtn.setText(aItem.itemName);
			itemBtn.setTextColor(getResources().getColor(R.color.white));
			aItem.setLayoutId(aId);

			System.out.println(aId);
			yourLayout.addView(headerView);

		}
	}

}