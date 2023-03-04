package models;

import java.io.PrintWriter;
import java.util.ArrayList;

public class ChronologicalCV extends CV{

	public ChronologicalCV(String name) {
		super(name);
		sections = new ArrayList<>();
		sections.add(new Section("GENERAL INFORMATION"));
		sections.add(new Section("PROFFESSIONAL PROFILE"));
		sections.add(new Section("CORE STRENGTHS"));
		sections.add(new Section("PROFESSIONAL EXPERIENCE"));
		sections.add(new Section("EDUCATION AND TRAINING"));
		sections.add(new Section("FURTHER COURSES"));
		sections.add(new Section("ADDITIONAL INFORMATION"));
		sections.add(new Section("INTERESTS"));
	}

	@Override
	public void printTofile() {
		// TODO Auto-generated method stub
		
	}
	
	

}
