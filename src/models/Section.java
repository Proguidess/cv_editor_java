package models;

import java.util.ArrayList;

public class Section {
	
	private String title;
	private ArrayList<Item> items;
	
	public Section(String title) {
		super();
		this.title = title;
		items = new ArrayList<>();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void addItem(Item item) {
		this.items.add(item);
	}
	
	public void removeItem(int i){
		items.remove(i);
	}
	
	

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return title;
	}

	public boolean containsBulletList(BulletList b){
		if(items.contains(b)){
			return true;
		}else{
			return false;
		}
	}

	
	
	
	

}
