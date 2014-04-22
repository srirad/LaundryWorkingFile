package com.pos.laundrypos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.pos.laundrypos.ModelExtra.eExtraType;
import com.pos.laundrypos.ModelLaundryItem.eCategoryType;

import android.app.Activity;
import android.app.FragmentManager;
import android.graphics.Bitmap;

import android.os.Bundle;

import android.support.v4.widget.DrawerLayout;

import android.util.Log;

import android.view.MotionEvent;

import android.view.View;
import android.view.View.OnTouchListener;

import android.widget.AdapterView;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

/**
 * @author srinivasan
 * 
 */
public class MainActivity_POS extends Activity {

	ListView addOnListView;

	ArrayList<Bitmap> colorArray = new ArrayList<Bitmap>();
	public GridView colorGridView;
	CommonAPI commonAPI = new CommonAPI();

	ArrayList<Item> currentArray = new ArrayList<Item>();
	static ModelLaundryItem currentItem;
	static ModelLaundryItem prevItem;
	static ModelReceipt receipt = new ModelReceipt();

	ArrayList<String> currentMaterials = new ArrayList<String>();
	int currentOrderNum = -1;
	ColorGridViewAdapter customColorGridAdapter;

	final int HOUSE_TAB = 3;
	ArrayList<ModelLaundryItem> houseArray = new ArrayList<ModelLaundryItem>();

	final int LADIES_TAB = 0;

	static ArrayList<ModelLaundryItem> ladiesArray = new ArrayList<ModelLaundryItem>();

	final int MEN_TAB = 1;
	ArrayList<ModelLaundryItem> menArray = new ArrayList<ModelLaundryItem>();

	int mTabSelect = 0;

	final int OTHERS_TAB = 2;
	ArrayList<ModelLaundryItem> othersArray = new ArrayList<ModelLaundryItem>();
	ListView otherListView;
	List<RowItem> rowItems;

	int selectedTab = LADIES_TAB;

	int totalLadItem = 0;

	static String currentItemStr = "";
	static EditText itemTrackText;
	boolean itemSelected = false;

	DrawerLayout mDrawerLayout;
	ListView mDrawerList;

	MainFragment fragment;
	private MyTouchListener mOnTouchListener;

	ArrayAdapter<String> receiptAdapter;
	ListView receiptListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.ordermainpage);
		setContentView(R.layout.layoutmain);

		fragment = new MainFragment();

		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.content_frame, fragment).commit();

		populateMen();
		populateLadies();
		populateOthers();
		populateHouse();
		populateColors();
		mOnTouchListener = new MyTouchListener();
		fragment.colorGridAdapter = new ColorGridViewAdapter(this,
				R.layout.color_grid, colorArray);

		View view = findViewById(R.id.objects_drawer);
		view.setOnTouchListener(mOnTouchListener);

		receiptListView = (ListView) findViewById(R.id.receiptItemListId);
		ArrayList<ModelExtra> itemList = new ArrayList<ModelExtra>();
		currentItem = new ModelLaundryItem(-1, "");
		itemList = getExtraModel();
		ArrayAdapter<ModelLaundryItem> receiptAdapter = new ReceiptListAdapter(
				this, receipt.getAllItem());
		receiptListView.setAdapter(receiptAdapter);

	}

	public void displayHouseItems() {

		LinearLayout yourLayout = (LinearLayout) findViewById(R.id.itemsLinearLayout);
		yourLayout.removeAllViews();
		for (int i = 0; i < houseArray.size(); i++) {

			ModelLaundryItem aItem = houseArray.get(i);

			View headerView = View.inflate(this, R.layout.selectableitem, null);

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

	public ArrayList<ModelLaundryItem> getLadiesArray() {
		return ladiesArray;
	}

	public void displayLadiesItem() {

		LinearLayout yourLayout = (LinearLayout) findViewById(R.id.itemsLinearLayout);
		yourLayout.removeAllViews();
		for (int i = 0; i < ladiesArray.size(); i++) {

			ModelLaundryItem aItem = ladiesArray.get(i);

			View headerView = View.inflate(this, R.layout.selectableitem, null);

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

	public void displayMenItems() {

		LinearLayout yourLayout = (LinearLayout) findViewById(R.id.itemsLinearLayout);
		yourLayout.removeAllViews();
		for (int i = 0; i < menArray.size(); i++) {

			ModelLaundryItem aItem = menArray.get(i);

			View headerView = View.inflate(this, R.layout.selectableitem, null);

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

	public void displayOthersItem() {

		LinearLayout yourLayout = (LinearLayout) findViewById(R.id.itemsLinearLayout);
		yourLayout.removeAllViews();
		for (int i = 0; i < othersArray.size(); i++) {

			ModelLaundryItem aItem = othersArray.get(i);

			View headerView = View.inflate(this, R.layout.selectableitem, null);

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

	private ArrayList<ModelExtra> getExtraModel() {
		ArrayList<ModelExtra> list = new ArrayList<ModelExtra>();

		list.add(new ModelExtra("XXX", 2.0f, eExtraType.size));
		list.add(new ModelExtra("Medium", 2.0f, eExtraType.size));
		list.add(new ModelExtra("Small", 2.0f, eExtraType.size));

		return list;
	}

	public void selectLadies(View view) {

		this.itemSelected = false;
		System.out.println("Clicked Ladies");
		this.selectedTab = LADIES_TAB;

		commonAPI.setActivateVal(view, R.id.ladiesTab, true);
		commonAPI.setActivateVal(view, R.id.menTab, false);
		commonAPI.setActivateVal(view, R.id.houseTab, false);
		commonAPI.setActivateVal(view, R.id.othersTab, false);

		RelativeLayout lay = (RelativeLayout) findViewById(R.id.relativeLayoutWBg);
		lay.setBackgroundResource(R.drawable.l_tabbg);
		this.displayLadiesItem();

	}

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// /* Inflate the menu; this adds items to the action bar if it is present.
	// */
	// getMenuInflater().inflate(R.menu.main, menu);
	// return true;
	// }
	//

	private void populateColors() {

		Bitmap image = Bitmap.createBitmap(30, 30, Bitmap.Config.ARGB_8888);
		image.eraseColor(android.graphics.Color.CYAN);
		colorArray.add(image);

		image = Bitmap.createBitmap(30, 30, Bitmap.Config.ARGB_8888);
		image.eraseColor(android.graphics.Color.BLUE);
		colorArray.add(image);

		image = Bitmap.createBitmap(30, 30, Bitmap.Config.ARGB_8888);
		image.eraseColor(android.graphics.Color.GREEN);
		colorArray.add(image);

		image = Bitmap.createBitmap(30, 30, Bitmap.Config.ARGB_8888);
		image.eraseColor(android.graphics.Color.BLACK);
		colorArray.add(image);

		image = Bitmap.createBitmap(30, 30, Bitmap.Config.ARGB_8888);
		image.eraseColor(android.graphics.Color.WHITE);
		colorArray.add(image);

		image = Bitmap.createBitmap(30, 30, Bitmap.Config.ARGB_8888);
		image.eraseColor(android.graphics.Color.DKGRAY);
		colorArray.add(image);

		image = Bitmap.createBitmap(30, 30, Bitmap.Config.ARGB_8888);
		image.eraseColor(android.graphics.Color.MAGENTA);
		colorArray.add(image);

		image = Bitmap.createBitmap(30, 30, Bitmap.Config.ARGB_8888);
		image.eraseColor(android.graphics.Color.YELLOW);
		colorArray.add(image);

		image = Bitmap.createBitmap(30, 30, Bitmap.Config.ARGB_8888);
		image.eraseColor(android.graphics.Color.RED);
		colorArray.add(image);

		image = Bitmap.createBitmap(30, 30, Bitmap.Config.ARGB_8888);
		image.eraseColor(android.graphics.Color.YELLOW);
		colorArray.add(image);

		image = Bitmap.createBitmap(30, 30, Bitmap.Config.ARGB_8888);
		image.eraseColor(android.graphics.Color.DKGRAY);
		colorArray.add(image);

		image = Bitmap.createBitmap(30, 30, Bitmap.Config.ARGB_8888);
		image.eraseColor(android.graphics.Color.BLACK);
		colorArray.add(image);

		image = Bitmap.createBitmap(30, 30, Bitmap.Config.ARGB_8888);
		image.eraseColor(android.graphics.Color.DKGRAY);
		colorArray.add(image);

		image = Bitmap.createBitmap(30, 30, Bitmap.Config.ARGB_8888);
		image.eraseColor(android.graphics.Color.MAGENTA);
		colorArray.add(image);

		image = Bitmap.createBitmap(30, 30, Bitmap.Config.ARGB_8888);
		image.eraseColor(android.graphics.Color.YELLOW);
		colorArray.add(image);

		image = Bitmap.createBitmap(30, 30, Bitmap.Config.ARGB_8888);
		image.eraseColor(android.graphics.Color.RED);
		colorArray.add(image);

	}

	private void populateHouse() {

		ModelLaundryItem bedsheet = new ModelLaundryItem(R.drawable.h_bedsheet,
				"bedsheet", eCategoryType.house, 1.0f, 2.0f, 3.0f, 4.0f);
		this.generateRandExtra(bedsheet);
		houseArray.add(bedsheet);

		ModelLaundryItem blackoutcurtain = new ModelLaundryItem(
				R.drawable.h_blackoutcurtain, "bedsheet", eCategoryType.house,
				1.0f, 2.0f, 3.0f, 4.0f);
		this.generateRandExtra(blackoutcurtain);
		houseArray.add(blackoutcurtain);

		ModelLaundryItem carpet = new ModelLaundryItem(R.drawable.h_carpet,
				"carpet", eCategoryType.house, 1.0f, 2.0f, 3.0f, 4.0f);
		this.generateRandExtra(carpet);
		houseArray.add(carpet);

		ModelLaundryItem comforter = new ModelLaundryItem(R.drawable.h_comfort,
				"comforter", eCategoryType.house, 1.0f, 2.0f, 3.0f, 4.0f);
		this.generateRandExtra(comforter);
		houseArray.add(comforter);

		ModelLaundryItem dayCurtain = new ModelLaundryItem(
				R.drawable.h_daycurtain, "comforter", eCategoryType.house,
				1.0f, 2.0f, 3.0f, 4.0f);
		this.generateRandExtra(dayCurtain);
		houseArray.add(dayCurtain);

		ModelLaundryItem cushion = new ModelLaundryItem(R.drawable.h_cushion,
				"comforter", eCategoryType.house, 1.0f, 2.0f, 3.0f, 4.0f);
		this.generateRandExtra(cushion);
		houseArray.add(cushion);

		ModelLaundryItem mat = new ModelLaundryItem(R.drawable.h_mat,
				"comforter", eCategoryType.house, 1.0f, 2.0f, 3.0f, 4.0f);
		this.generateRandExtra(mat);
		houseArray.add(mat);

		ModelLaundryItem nightcurtain = new ModelLaundryItem(
				R.drawable.h_nightcurtain, "nightcurtain", eCategoryType.house,
				1.0f, 2.0f, 3.0f, 4.0f);
		this.generateRandExtra(nightcurtain);
		houseArray.add(nightcurtain);

		ModelLaundryItem pianocover = new ModelLaundryItem(
				R.drawable.h_pianocover, "nightcurtain", eCategoryType.house,
				1.0f, 2.0f, 3.0f, 4.0f);
		this.generateRandExtra(pianocover);
		houseArray.add(pianocover);

		ModelLaundryItem pillow = new ModelLaundryItem(R.drawable.h_pillow,
				"pillow", eCategoryType.house, 1.0f, 2.0f, 3.0f, 4.0f);
		this.generateRandExtra(pillow);
		houseArray.add(pillow);

		ModelLaundryItem Quilt = new ModelLaundryItem(R.drawable.h_quilt,
				"Quilt", eCategoryType.house, 1.0f, 2.0f, 3.0f, 4.0f);
		this.generateRandExtra(Quilt);
		houseArray.add(Quilt);

		ModelLaundryItem sleepingbag = new ModelLaundryItem(
				R.drawable.h_sleepingbag, "sleepingbag", eCategoryType.house,
				1.0f, 2.0f, 3.0f, 4.0f);
		this.generateRandExtra(sleepingbag);
		houseArray.add(sleepingbag);

		ModelLaundryItem sofacover = new ModelLaundryItem(
				R.drawable.h_sofacover, "sofacover", eCategoryType.house, 1.0f,
				2.0f, 3.0f, 4.0f);
		this.generateRandExtra(sofacover);
		houseArray.add(sofacover);

		ModelLaundryItem tablecloth = new ModelLaundryItem(
				R.drawable.h_tablecloth, "tablecloth", eCategoryType.house,
				1.0f, 2.0f, 3.0f, 4.0f);
		this.generateRandExtra(tablecloth);
		houseArray.add(tablecloth);

		ModelLaundryItem towel = new ModelLaundryItem(R.drawable.h_towel,
				"towel", eCategoryType.house, 1.0f, 2.0f, 3.0f, 4.0f);
		this.generateRandExtra(towel);
		houseArray.add(towel);

		ModelLaundryItem toy = new ModelLaundryItem(R.drawable.h_toy, "toy",
				eCategoryType.house, 1.0f, 2.0f, 3.0f, 4.0f);
		this.generateRandExtra(toy);
		houseArray.add(toy);

		ModelLaundryItem washandfold = new ModelLaundryItem(
				R.drawable.h_washandfold, "washandfold", eCategoryType.house,
				1.0f, 2.0f, 3.0f, 4.0f);
		this.generateRandExtra(toy);
		houseArray.add(washandfold);

	}

	private void populateLadies() {

		ModelLaundryItem blouse = new ModelLaundryItem(R.drawable.l_blouse,
				"Blouse", eCategoryType.lady, 1.0f, 2.0f, 3.0f, 4.0f);
		this.generateRandExtra(blouse);
		ladiesArray.add(blouse);

		ModelLaundryItem dress = new ModelLaundryItem(R.drawable.l_dress,
				"dress", eCategoryType.lady, 1.0f, 2.0f, 3.0f, 4.0f);
		this.generateRandExtra(dress);
		ladiesArray.add(dress);

		ModelLaundryItem jacket = new ModelLaundryItem(R.drawable.l_jacket,
				"jacket", eCategoryType.lady, 1.0f, 2.0f, 3.0f, 4.0f);
		this.generateRandExtra(jacket);
		ladiesArray.add(jacket);

		ModelLaundryItem gown = new ModelLaundryItem(R.drawable.l_gown, "gown",
				eCategoryType.lady, 1.0f, 2.0f, 3.0f, 4.0f);
		this.generateRandExtra(gown);
		ladiesArray.add(gown);

		ModelLaundryItem saree = new ModelLaundryItem(R.drawable.l_saree,
				"saree", eCategoryType.lady, 1.0f, 2.0f, 3.0f, 4.0f);
		this.generateRandExtra(saree);
		ladiesArray.add(saree);

		ModelLaundryItem skirt = new ModelLaundryItem(R.drawable.l_skirt,
				"skirt", eCategoryType.lady, 1.0f, 2.0f, 3.0f, 4.0f);
		this.generateRandExtra(skirt);
		ladiesArray.add(skirt);

		ModelLaundryItem teadress = new ModelLaundryItem(R.drawable.l_teadress,
				"teadress", eCategoryType.lady, 1.0f, 2.0f, 3.0f, 4.0f);
		this.generateRandExtra(teadress);
		ladiesArray.add(teadress);

		ModelLaundryItem traditional = new ModelLaundryItem(
				R.drawable.l_traditional, "traditional", eCategoryType.lady,
				1.0f, 2.0f, 3.0f, 4.0f);
		this.generateRandExtra(traditional);
		ladiesArray.add(traditional);

		ModelLaundryItem weddinggown = new ModelLaundryItem(
				R.drawable.l_wedding, "Weddinggown", eCategoryType.lady, 1.0f,
				2.0f, 3.0f, 4.0f);
		this.generateRandExtra(weddinggown);
		ladiesArray.add(weddinggown);

	}

	//
	private void populateMen() {

		ModelLaundryItem gJacket = new ModelLaundryItem(R.drawable.g_jacket,
				"jacket", eCategoryType.man, 1.0f, 2.0f, 3.0f, 4.0f);

		ModelLaundryItem gShirt = new ModelLaundryItem(R.drawable.g_shirt,
				"shirt", eCategoryType.man, 1.0f, 2.0f, 3.0f, 4.0f);

		ModelLaundryItem gTie = new ModelLaundryItem(R.drawable.g_tie,
				"jacket", eCategoryType.man, 1.0f, 2.0f, 3.0f, 4.0f);
		ModelLaundryItem gTrad = new ModelLaundryItem(R.drawable.g_traditional,
				"traditional", eCategoryType.man, 1.0f, 2.0f, 3.0f, 4.0f);
		ModelLaundryItem gTrouser = new ModelLaundryItem(R.drawable.g_trousers,
				"trousers", eCategoryType.man, 1.0f, 2.0f, 3.0f, 4.0f);
		ModelLaundryItem gVest = new ModelLaundryItem(R.drawable.g_vest,
				"vest", eCategoryType.man, 1.0f, 2.0f, 3.0f, 4.0f);

		this.generateRandExtra(gJacket);
		this.generateRandExtra(gShirt);
		this.generateRandExtra(gTie);
		this.generateRandExtra(gTrouser);
		this.generateRandExtra(gTrad);
		this.generateRandExtra(gVest);

		menArray.add(gJacket);
		menArray.add(gShirt);
		menArray.add(gTie);
		menArray.add(gTrad);
		menArray.add(gVest);
	}

	//
	private void populateOthers() {

		ModelLaundryItem Coat = new ModelLaundryItem(R.drawable.o_coat, "Coat",
				eCategoryType.other, 1.0f, 2.0f, 3.0f, 4.0f);
		this.generateRandExtra(Coat);
		othersArray.add(Coat);

		ModelLaundryItem furcoat = new ModelLaundryItem(R.drawable.o_fur,
				"furcoat", eCategoryType.other, 1.0f, 2.0f, 3.0f, 4.0f);
		this.generateRandExtra(furcoat);
		othersArray.add(furcoat);

		ModelLaundryItem gloves = new ModelLaundryItem(R.drawable.o_gloves,
				"Coat", eCategoryType.other, 1.0f, 2.0f, 3.0f, 4.0f);
		this.generateRandExtra(gloves);
		othersArray.add(gloves);

		ModelLaundryItem graduationgown = new ModelLaundryItem(
				R.drawable.o_gradgown, "graduationgown", eCategoryType.other,
				1.0f, 2.0f, 3.0f, 4.0f);
		this.generateRandExtra(graduationgown);
		othersArray.add(graduationgown);

		ModelLaundryItem leatherjacket = new ModelLaundryItem(
				R.drawable.o_leatherjacket, "leatherjacket",
				eCategoryType.other, 1.0f, 2.0f, 3.0f, 4.0f);
		this.generateRandExtra(leatherjacket);
		othersArray.add(leatherjacket);

		ModelLaundryItem leatherskirt = new ModelLaundryItem(
				R.drawable.o_leatherskirt, "leatherskirt", eCategoryType.other,
				1.0f, 2.0f, 3.0f, 4.0f);
		this.generateRandExtra(leatherskirt);
		othersArray.add(leatherskirt);

		ModelLaundryItem overall = new ModelLaundryItem(R.drawable.o_overall,
				"overall", eCategoryType.other, 1.0f, 2.0f, 3.0f, 4.0f);
		this.generateRandExtra(overall);
		othersArray.add(overall);

		ModelLaundryItem scarf = new ModelLaundryItem(R.drawable.o_scarf,
				"scarf", eCategoryType.other, 1.0f, 2.0f, 3.0f, 4.0f);
		this.generateRandExtra(scarf);
		othersArray.add(scarf);

		ModelLaundryItem shawl = new ModelLaundryItem(R.drawable.o_shawl,
				"scarf", eCategoryType.other, 1.0f, 2.0f, 3.0f, 4.0f);
		this.generateRandExtra(shawl);
		othersArray.add(shawl);

		ModelLaundryItem sweater = new ModelLaundryItem(R.drawable.o_sweater,
				"scarf", eCategoryType.other, 1.0f, 2.0f, 3.0f, 4.0f);
		this.generateRandExtra(sweater);
		othersArray.add(sweater);

		ModelLaundryItem windbreaker = new ModelLaundryItem(
				R.drawable.o_windbreaker, "windbreaker", eCategoryType.other,
				1.0f, 2.0f, 3.0f, 4.0f);
		this.generateRandExtra(windbreaker);
		othersArray.add(windbreaker);

	}

	//
	public void selectHouse(View view) {
		itemSelected = false;
		commonAPI.setActivateVal(view, R.id.menTab, false);
		commonAPI.setActivateVal(view, R.id.ladiesTab, false);
		commonAPI.setActivateVal(view, R.id.othersTab, false);
		commonAPI.setActivateVal(view, R.id.houseTab, true);
		selectedTab = HOUSE_TAB;
		RelativeLayout lay = (RelativeLayout) findViewById(R.id.relativeLayoutWBg);
		lay.setBackgroundResource(R.drawable.h_tabbg);
		this.displayHouseItems();
	}

	public void selectItem(View view) {
		itemSelected = true;
		prevItem = currentItem;
		if (prevItem != null) {
		
		
		prevItem.setAllNotSel();
		
		}
		if (selectedTab == LADIES_TAB) {
			for (int i = 0; i < ladiesArray.size(); i++) {
				ModelLaundryItem aItem = ladiesArray.get(i);
				int id = -1;
				Log.d("itemID", Integer.valueOf(id).toString());
				id = aItem.itemLayoutId;
				System.out.println(id);
				Log.d("MY_TAG = ", Integer.valueOf(id).toString());
				commonAPI.setActivateVal(view, aItem.itemLayoutId, false);

				Button abtn = (Button) findViewById(id);
				abtn.setTextColor(getResources().getColor(R.color.white));
				if (view.getId() == aItem.itemLayoutId) {
					currentItem = aItem;

					this.setAdapterForExtraLists(aItem);

				}

			}
		}

		else

		if (selectedTab == MEN_TAB) {
			for (int i = 0; i < menArray.size(); i++) {
				ModelLaundryItem aItem = menArray.get(i);
				int id = -1;
				Log.d("itemID", Integer.valueOf(id).toString());
				id = aItem.itemLayoutId;
				System.out.println(id);
				Log.d("MY_TAG = ", Integer.valueOf(id).toString());
				commonAPI.setActivateVal(view, aItem.itemLayoutId, false);

				Button abtn = (Button) findViewById(id);
				abtn.setTextColor(getResources().getColor(R.color.white));

				if (view.getId() == aItem.itemLayoutId) {
					currentItem = aItem;

					this.setAdapterForExtraLists(aItem);

				}

			}

		} else if (selectedTab == OTHERS_TAB) {
			for (int i = 0; i < othersArray.size(); i++) {
				ModelLaundryItem aItem = othersArray.get(i);
				int id = -1;
				Log.d("itemID", Integer.valueOf(id).toString());
				id = aItem.itemLayoutId;
				System.out.println(id);
				Log.d("MY_TAG = ", Integer.valueOf(id).toString());
				commonAPI.setActivateVal(view, aItem.itemLayoutId, false);

				Button abtn = (Button) findViewById(id);
				abtn.setTextColor(getResources().getColor(R.color.white));

				if (view.getId() == aItem.itemLayoutId) {
					currentItem = aItem;

					this.setAdapterForExtraLists(aItem);
				}

			}
		}

		else if (selectedTab == HOUSE_TAB) {
			for (int i = 0; i < houseArray.size(); i++) {
				ModelLaundryItem aItem = houseArray.get(i);
				int id = -1;
				Log.d("itemID", Integer.valueOf(id).toString());
				id = aItem.itemLayoutId;
				System.out.println(id);
				Log.d("MY_TAG = ", Integer.valueOf(id).toString());
				commonAPI.setActivateVal(view, aItem.itemLayoutId, false);

				Button abtn = (Button) findViewById(id);
				abtn.setTextColor(getResources().getColor(R.color.white));
				if (view.getId() == aItem.itemLayoutId) {
					currentItem = aItem;

					this.setAdapterForExtraLists(aItem);

				}
			}
		}

		commonAPI.setActivateVal(view, view.getId(), true);
		Button aBtn = (Button) view;
		aBtn.setTextColor(getResources().getColor(R.color.systemColor));

	}

	public void selectMen(View view) {
	
		itemSelected = false;
		System.out.println("Clicked GentleMen");
		selectedTab = MEN_TAB;

		commonAPI.setActivateVal(view, R.id.menTab, true);
		commonAPI.setActivateVal(view, R.id.ladiesTab, false);
		commonAPI.setActivateVal(view, R.id.othersTab, false);
		commonAPI.setActivateVal(view, R.id.houseTab, false);
		RelativeLayout lay = (RelativeLayout) findViewById(R.id.relativeLayoutWBg);
		lay.setBackgroundResource(R.drawable.g_tabbg);
		this.displayMenItems();
	}

	public void selectOthers(View view) {
		itemSelected = false;

		selectedTab = OTHERS_TAB;

		RelativeLayout lay = (RelativeLayout) findViewById(R.id.relativeLayoutWBg);
		lay.setBackgroundResource(R.drawable.o_tabbg);

		commonAPI.setActivateVal(view, R.id.menTab, false);
		commonAPI.setActivateVal(view, R.id.ladiesTab, false);
		commonAPI.setActivateVal(view, R.id.othersTab, true);
		commonAPI.setActivateVal(view, R.id.houseTab, false);

		this.displayOthersItem();
	}

	public void selectService(View view) {

		if (itemSelected == true) {
			commonAPI.setActivateVal(view, R.id.dryCleanType, false);
			commonAPI.setActivateVal(view, R.id.wetCleanType, false);
			commonAPI.setActivateVal(view, R.id.handCleanType, false);
			commonAPI.setActivateVal(view, R.id.pressCleanType, false);

			commonAPI.setActivateVal(view, view.getId(), true);
		}

		else {

			itemTrackText.setText("Select an Item First");

		}
	}

	public void generateRandExtra(ModelLaundryItem aItem) {

		Random randomGenerator = new Random();

		int sizeInt = randomGenerator.nextInt(3);
		randomGenerator = new Random();
		int materialInt = randomGenerator.nextInt(3);
		randomGenerator = new Random();
		int otherInt = randomGenerator.nextInt(3);
		randomGenerator = new Random();
		int addOnInt = randomGenerator.nextInt(3);

		float[] priceArr = { 1.0f, 2.0f, 3.20f, 4.50f, 0.50f };

		String[] sizeStrings = { "XX", "XL", "MEDIUM", "SMALL", "XXS", "XXL",
				"UPTOTHIGH" };
		String[] materialStrings = { "LEATHER", "COTTON", "LINEN", "SILK",
				"WOOLEN" };
		String[] addOnStrings = { "LINING", "TRIMMING", "BUTTONS", "PLEATS",
				"BEADS" };
		String[] otherStrings = { "FULL SET", "NO PRESSING", "FULL PRESSING",
				"WITHOUT SASH", "WITH SASH" };

		if (sizeInt > 0) {

			int sizeNum = randomGenerator.nextInt(sizeStrings.length);

			for (int i = 0; i < sizeNum; i++) {
				int namePos = randomGenerator.nextInt(sizeStrings.length);
				int pricePos = randomGenerator.nextInt(priceArr.length);

				String name = sizeStrings[namePos];
				float price = priceArr[pricePos];
				aItem.addSize(name, price);
			}
		}
		if (materialInt > 0) {

			int materialNum = randomGenerator.nextInt(materialStrings.length);

			for (int i = 0; i < materialNum; i++) {
				int namePos = randomGenerator.nextInt(materialStrings.length);
				int pricePos = randomGenerator.nextInt(priceArr.length);

				String name = materialStrings[namePos];
				float price = priceArr[pricePos];
				aItem.addMaterial(name, price);
			}
		}

		if (otherInt > 0) {

			int otherNum = randomGenerator.nextInt(otherStrings.length);

			for (int i = 0; i < otherNum; i++) {
				int namePos = randomGenerator.nextInt(otherStrings.length);
				int pricePos = randomGenerator.nextInt(priceArr.length);

				String name = otherStrings[namePos];
				float price = priceArr[pricePos];
				aItem.addOther(name, price);
			}
		}

		if (addOnInt > 0) {

			int addOnNum = randomGenerator.nextInt(addOnStrings.length);

			for (int i = 0; i < addOnNum; i++) {
				int namePos = randomGenerator.nextInt(addOnStrings.length);
				int pricePos = randomGenerator.nextInt(priceArr.length);

				String name = addOnStrings[namePos];
				float price = priceArr[pricePos];
				aItem.addAddOn(name, price);
			}
		}

	}

	//
	public void setAdapterForExtraLists(ModelLaundryItem aItem) {

		fragment.modifySizeArray(aItem.getSizeArr(), aItem.getMaterialArr(),
				aItem.getAddOnArr(), aItem.getOtherArr());

	}

	// FOR THE COLOR GRID VIEW
	private class ItemTypeClickListener implements GridView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
		}
	}

	public void selectTender(View view) {

	}

	public void deleteCurrentItem(View view) {
		currentItem = new ModelLaundryItem(-1, "");
		

	}

	public void addItemToReceipt(View view) {

		ModelLaundryItem aTempItem = currentItem;
		if(currentItem.itemName != "") {
		
		receipt.addItemToReceipt(aTempItem);
		ArrayAdapter<ModelLaundryItem> receiptAdapter = new ReceiptListAdapter(
				this, receipt.getAllItem());
		receiptListView.setAdapter(receiptAdapter);
		
		}
		currentItem = new ModelLaundryItem(-1, "");
	}



	class MyTouchListener implements OnTouchListener {
		@Override
		public boolean onTouch(View v, MotionEvent event) {

			int action = event.getAction();
			// int position = (Integer) v.getTag();
			View receiptView = findViewById(R.id.objects_drawer);
			if (v == receiptView) {

				switch (action) {
				case MotionEvent.ACTION_DOWN:

					Log.d("action", "ACTION_DOWN - ");
					break;
				case MotionEvent.ACTION_MOVE:
					Log.d("action", "ACTION_MOVE - ");

					break;
				case MotionEvent.ACTION_UP:
					Log.d("action", "ACTION_UP - ");

					break;
				}

			}
			return true;
		}
	}
	
	public void  setAllExtraNotSel() {
		
		
		
	}

}
