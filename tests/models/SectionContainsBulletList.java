package models;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

public class SectionContainsBulletList {

	@Test
	public void test() throws ParseException {
		
		CV cv = TestFactor.factorCV();
		
		boolean b = cv.getSections().get(3).containsBulletList(new BulletList("Google"));
		boolean b2 = cv.getSections().get(3).containsBulletList(new BulletList("asdasdsadasd"));
		
		if(!b){
			fail("contains problem for true");
		}
		
		if(b2){
			fail("contains problem for false");
		}
		
	}

}
