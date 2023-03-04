package models;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Test;

public class IOCombined {

	@Test
	public void test() throws Exception {
		CV cv = new CombinedCV("CombinedIO");
		TestFactor.addToCv(cv);
		
		
		cv.exportLatex();
		cv.exportTxt();
		
		CV latex = CV.importLatex("CombinedIO.tex");
		CV txt = CV.importText("CombinedIO.txt");
		
		if(!latex.getSections().get(3).containsBulletList(new BulletList("Google"))){
			fail("latex IO");
		}
		
		if(!txt.getSections().get(3).containsBulletList(new BulletList("Google"))){
			fail("txt IO");
		}
	}

}
