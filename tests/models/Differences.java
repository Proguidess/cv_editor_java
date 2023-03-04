package models;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Test;

public class Differences {

	@Test
	public void test() throws ParseException {
		CV cv1 = TestFactor.factorCV();
		CV cv2 = TestFactor.factorCV();
		
		cv1.getSections().get(1).addItem(new BulletList("b1"));
		cv2.getSections().get(1).addItem(new BulletList("b2"));
		
		CV d = cv1.differencesWith(cv2);
		
		if(!d.getSections().get(1).containsBulletList(new BulletList("b1"))){
			fail("b1 not added");
		}
		
		if(!d.getSections().get(1).containsBulletList(new BulletList("b2"))){
			fail("b2 not added");
		}
		
		
	}

}
