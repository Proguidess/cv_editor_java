package models;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Test;

public class IOFunctional {

	@Test
	public void test() throws Exception {
		
		CV cv = new FunctionalCV("FunctionalIO");
		TestFactor.addToCv(cv);
		
		
		cv.exportLatex();
		cv.exportTxt();
		
		CV latex = CV.importLatex("FunctionalIO.tex");
		CV txt = CV.importText("FunctionalIO.txt");
		
		if(!latex.getSections().get(3).containsBulletList(new BulletList("Google"))){
			fail("latex IO");
		}
		
		if(!txt.getSections().get(3).containsBulletList(new BulletList("Google"))){
			fail("txt IO");
		}
	}
}
