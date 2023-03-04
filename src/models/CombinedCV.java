package models;

import java.util.ArrayList;

public class CombinedCV extends CV{

	public CombinedCV(String name) {
		super(name);
		sections = new ArrayList<>();
		sections.add(new Section("GENERAL INFORMATION"));
		sections.add(new Section("PROFFESSIONAL PROFILE"));
		sections.add(new Section("SKILLS AND EXPERIENCE"));
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
