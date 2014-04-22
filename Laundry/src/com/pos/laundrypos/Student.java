package com.pos.laundrypos;

/**
 * @author srinivasan
 *
 */
public class Student {
	private int id;
	private String name;

	public long getId() {
		return id;
	}

	public String getName() {
		return this.name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
