package models;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SectionTest {

	@Test
	public void testAddItem() {
		Section s = new Section("test section");
		int before = s.getItems().size();
		s.addItem(new BulletList("b1"));
		int after = s.getItems().size();
		
		assertEquals("Not added correctly", before+1, after);
		
	}

	@Test
	public void testRemoveItem() {
		Section s = new Section("test section");
		
		s.addItem(new BulletList("b1"));
		s.addItem(new BulletList("b2"));
		
		
		s.removeItem(0);
		
		int after = s.getItems().size();
		assertEquals("Result", after, 1);
	}

}
