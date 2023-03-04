package models;

import java.util.Date;

public abstract class Item {
	
	private Date date;
	
	public Item() {
		date = null;
	}
	
	public Item(Date d) {
		date = d;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	

}
