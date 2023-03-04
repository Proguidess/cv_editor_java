package gui;

import static org.junit.Assert.*;

import org.junit.Test;

import models.Section;

public class ItemsTest {

	@Test
	public void testAddItem() {
		GuiItems g = new GuiItems();
		Section section = new Section("test");
		g.setSection(section);
		
		g.addItem("item1", "2015-01-02");
		assertEquals("Should be added", section.getItems().size(),1);
		
		g.addItem("item1", "2015-01-03");
		assertEquals("Should be added", section.getItems().size(),2);
		
		g.addItem("item1", "2015-01-01");
		assertEquals("Should not be added", section.getItems().size(),2);
		
	}

}
