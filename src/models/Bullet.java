package models;

import java.util.ArrayList;

public class Bullet {
	
	private String text;
	private ArrayList<Bullet> subBullets;

	public Bullet(String text) {
		super();
		this.text = text;
		subBullets = new ArrayList<>();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public ArrayList<Bullet> getSubBullets() {
		return subBullets;
	}

	public void setSubBullets(ArrayList<Bullet> subBullets) {
		this.subBullets = subBullets;
	}
	
	public void addSubBullet(Bullet b){
		subBullets.add(b);
	}

	public void removeBullet(int i){
		subBullets.remove(i);
	}
	
	@Override
	public String toString() {
		return text;
	}


}
