package models;

import java.util.Date;

public class Paragraph extends Item{
	
	private String text;
	
	public Paragraph(String text) {
		super();
		this.text = text;
	}
	
	public Paragraph(String text, Date d) {
		super(d);
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Paragraph [text=" + text + " date="+ getDate()+"]";
	}

	
	
}
