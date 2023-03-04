package models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TestFactor {
	
	public static CV factorCV() throws ParseException{
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		CV cv = new ChronologicalCV("mytest");
		cv.getSections().get(0).getItems().add(new BulletList("Phone: 123456"));
		cv.getSections().get(0).getItems().add(new BulletList("Address: add 1"));
		cv.getSections().get(1).getItems().add(new BulletList("Java engineer"));
		Item java = new BulletList("Java");
		Item python = new BulletList("Python");
		cv.getSections().get(2).getItems().add(java);
		cv.getSections().get(2).getItems().add(python);
		
		BulletList compa = new BulletList("Google",format.parse("2015-01-01"));
		
		Bullet j = new Bullet("java");
		Bullet test = new Bullet("test");
		Bullet gui = new Bullet("Gui");
		Bullet be = new Bullet("backend");
		Bullet des = new Bullet("design");
		Bullet c = new Bullet("C++");
		
		compa.addBullet(j);
		compa.addBullet(c);
		
		j.addSubBullet(test);
		j.addSubBullet(des);
		
		test.addSubBullet(gui);
		test.addSubBullet(be);
		
		cv.getSections().get(3).getItems().add(compa);
		cv.getSections().get(3).getItems().add(new BulletList("Facebook, Ioannina",format.parse("2016-01-01")));
		
		cv.getSections().get(4).getItems().add(new BulletList("Computer science University of Ioannina",format.parse("2015-01-01")));

		cv.getSections().get(5).getItems().add(new BulletList("Programming in C",format.parse("2015-01-01")));
		cv.getSections().get(5).getItems().add(new BulletList("Programming in Haskell",format.parse("2016-01-01")));
		
		cv.getSections().get(6).getItems().add(new BulletList("Information part A"));
		cv.getSections().get(6).getItems().add(new BulletList("Information part B"));
		
		cv.getSections().get(7).getItems().add(new BulletList("Football"));
		cv.getSections().get(7).getItems().add(new BulletList("Basketball"));
		
		return cv;
	}
	
	public static CV addToCv(CV cv) throws ParseException{
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		cv.getSections().get(0).getItems().add(new BulletList("Phone: 123456"));
		cv.getSections().get(0).getItems().add(new BulletList("Address: add 1"));
		cv.getSections().get(1).getItems().add(new BulletList("Java engineer"));
		Item java = new BulletList("Java");
		Item python = new BulletList("Python");
		cv.getSections().get(2).getItems().add(java);
		cv.getSections().get(2).getItems().add(python);
		
		BulletList compa = new BulletList("Google",format.parse("2015-01-01"));
		
		Bullet j = new Bullet("java");
		Bullet test = new Bullet("test");
		Bullet gui = new Bullet("Gui");
		Bullet be = new Bullet("backend");
		Bullet des = new Bullet("design");
		Bullet c = new Bullet("C++");
		
		compa.addBullet(j);
		compa.addBullet(c);
		
		j.addSubBullet(test);
		j.addSubBullet(des);
		
		test.addSubBullet(gui);
		test.addSubBullet(be);
		
		cv.getSections().get(3).getItems().add(compa);
		cv.getSections().get(3).getItems().add(new BulletList("Facebook, Ioannina",format.parse("2016-01-01")));
		
		cv.getSections().get(4).getItems().add(new BulletList("Computer science University of Ioannina",format.parse("2015-01-01")));

		cv.getSections().get(5).getItems().add(new BulletList("Programming in C",format.parse("2015-01-01")));
		cv.getSections().get(5).getItems().add(new BulletList("Programming in Haskell",format.parse("2016-01-01")));
		
		cv.getSections().get(6).getItems().add(new BulletList("Information part A"));
		cv.getSections().get(6).getItems().add(new BulletList("Information part B"));
		
		cv.getSections().get(7).getItems().add(new BulletList("Football"));
		cv.getSections().get(7).getItems().add(new BulletList("Basketball"));
		
		return cv;
	}

}
