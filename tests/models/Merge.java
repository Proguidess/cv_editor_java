package models;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Test;

public class Merge {

	@Test
	public void test() throws ParseException {
		CV cv1 = TestFactor.factorCV();
		CV cv2 = TestFactor.factorCV();
		
		cv1.getSections().get(1).addItem(new BulletList("b1"));
		cv2.getSections().get(1).addItem(new BulletList("b2"));
		
		CV inter = cv1.intersectWith(cv2);
		CV diff = cv1.differencesWith(cv2);
		
		
		CV merged = CV.merge(inter, diff);
		
		if(!merged.getSections().get(1).containsBulletList(new BulletList("b1"))){
			fail("b1 problem");
		}
		
		if(!merged.getSections().get(1).containsBulletList(new BulletList("b2"))){
			fail("b2 problem");
		}
		
		if(!merged.getSections().get(3).containsBulletList(new BulletList("Google"))){
			fail("Should contain Google");
		}
	}

}
