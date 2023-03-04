package models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BulletList extends Item{
	
	private String title;
	private ArrayList<Bullet> bullets;
	
	
	public BulletList(String title) {
		super();
		this.title = title;
		bullets = new ArrayList<>();
	}
	
	public BulletList(String title, Date d) {
		super(d);
		this.title = title;
		bullets = new ArrayList<>();
	}
	
	public void addBullet(Bullet b){
		bullets.add(b);
	}
	
	public void removeBullet(int i){
		bullets.remove(i);
	}

	public ArrayList<Bullet> getBullets() {
		return bullets;
	}
	
	@Override
	public String toString() {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try{
			String d = format.format(getDate());
			return title+", "+d;
		}catch (Exception e) {
			return title;
		}
		
	}

	public String getTitle() {
		return title;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BulletList other = (BulletList) obj;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.trim().equals(other.title.trim()))
			return false;
		return true;
	}
	
	
	
	
}
