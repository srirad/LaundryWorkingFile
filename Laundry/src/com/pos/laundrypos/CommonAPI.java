/**
 * @author srinivasan
 *
 */

package com.pos.laundrypos;

import android.view.View;

public class CommonAPI {

	public CommonAPI() {
		super();

	}

	public void setActivateVal(View aView, int aId, boolean aVal) {
		View rootView = aView.getRootView();
		View view = (View) rootView.findViewById(aId);
		view.setActivated(aVal);

	}

}