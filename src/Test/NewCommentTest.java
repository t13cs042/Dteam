package Test;


import static org.junit.Assert.*;

import org.junit.Test;

import NewData.NewComment;

public class NewCommentTest {

	@Test
	public void testcheckerror() {
		NewComment com = new NewComment();
		assertEquals(com.checkerror("1", "2"), 0);//エラーなし
		assertEquals(com.checkerror("", "a"), 1);//件名が入力されていない
		assertEquals(com.checkerror("a", ""), 2);//本文が入力されていない
		
		String str = "";
		for(int i = 0; i < 50; i++){
			str+= "a";
		}
		
		assertEquals(com.checkerror(str, "a"), 0);//件名の50文字制限のチェック(50文字の場合)
		
		str += "a";
		
		assertEquals(com.checkerror(str, "a"), 4);//件名の50文字制限のチェック(51文字の場合)
		
		String str2 = "";
		for(int i = 0; i < 500; i++){
			str2 += "a";
		}
		
		assertEquals(com.checkerror("a", str2), 0);//本文の500文字制限のチェック(500文字の場合)
		
		str2 += "a";
		
		assertEquals(com.checkerror("a", str2), 8);//本文の500文字制限のチェック(501文字の場合)
			
	}

}
