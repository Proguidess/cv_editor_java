package models;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Test;

public class IOChronological {

	@Test
	public void test() throws Exception {
		CV cv = new ChronologicalCV("ChronologicalIO");
		TestFactor.addToCv(cv);
		
		
		cv.exportLatex();
		cv.exportTxt();
		
		CV latex = CV.importLatex("ChronologicalIO.tex");
		CV txt = CV.importText("ChronologicalIO.txt");
		
		if(!latex.getSections().get(3).containsBulletList(new BulletList("Google"))){
			fail("latex IO");
		}
		
		if(!txt.getSections().get(3).containsBulletList(new BulletList("Google"))){
			fail("txt IO");
		}
	}

}
