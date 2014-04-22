package com.pos.laundrypos;

/**
 * @author srinivasan
 *
 */
public class RowItem {
	private String desc;
	private int imageId;
	private String title;

	public RowItem(int imageId, String title, String desc) {
		this.imageId = imageId;
		this.title = title;
		this.desc = desc;
		
		/*checking for source tree*/
	}

	public String getDesc() {
		return desc;
	}

	public int getImageId() {
		return imageId;
	}

	public String getTitle() {
		return title;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return title + "\n" + desc;
	}
}