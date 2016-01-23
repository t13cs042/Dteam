package Test;

import static org.junit.Assert.*;

import org.junit.Test;

import NewData.Newtemp;

public class NewtempTest {

	@Test
	public void testcheckerror() {
		Newtemp temp = new Newtemp();
		
		assertEquals(temp.checkerror(""), 1);
		assertEquals(temp.checkerror("aaa"), 2);
		
		assertEquals(temp.checkerror("-30"), 0);
		assertEquals(temp.checkerror("-31"), 4);
		assertEquals(temp.checkerror("50"), 0);
		assertEquals(temp.checkerror("51"), 4);
		
	}

}
