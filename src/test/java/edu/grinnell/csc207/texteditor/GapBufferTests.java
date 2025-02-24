package edu.grinnell.csc207.texteditor;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import edu.grinnell.csc207.texteditor.GapBuffer;

public class GapBufferTests {
  


    @Test
    public void Test() {
        GapBuffer strBuff = new GapBuffer(5);
        assertEquals(0, strBuff.getCursorPosition());
        //Insertion check
        strBuff.insert('a');
        assertEquals(5, strBuff.getSize());
        //Deletion check
        strBuff.insert('b');
        strBuff.delete();
        assertEquals(5, strBuff.getSize());
        //Get cursor position
        assertEquals(1, strBuff.getCursorPosition());
        strBuff.insert('b');
        strBuff.insert('c');
        strBuff.insert('d');
        strBuff.insert('e');
        assertEquals(5, strBuff.getCursorPosition());
        //Move right
      //  strBuff.moveRight();
      //  assertEquals(5,strBuff.getCursorPosition());
    /*    //Move left
        strBuff.moveLeft();
        assertEquals(4,strBuff.getCursorPosition());
        //getChar
       assertEquals('a',strBuff.getChar(0));
      //  String str = strBuff.toString();
     //   assertEquals("abcde", str);
        
        //Corner cases:
        strBuff.delete();
        strBuff.delete();
        strBuff.delete();
        strBuff.delete();
        strBuff.delete();
        assertEquals(1, strBuff.getCursorPosition());
        
        strBuff.moveLeft(); //should just return
        assertEquals(1, strBuff.getCursorPosition());
       
      //  strBuff.moveRight();
     //   strBuff.moveRight();
    //    strBuff.moveRight();
     //   strBuff.moveRight();
    //    strBuff.moveRight();
    //    strBuff.moveRight(); //should just return
        
        //Doubling size of gap check;
      //  strBuff.insert('f');
      //  assertEquals(10, strBuff.getSize());
      */
    }
    
    @Property
    public GapBuffer NoLargerGapBuffer (@ForAll @IntRange(min=1,max=100) int val) {
       GapBuffer strBuff = new GapBuffer(val);
       for(int i = 0; i < val; i++) {
           strBuff.insert('a');
       }
       assertEquals(strBuff.getSize(), val);
       return(strBuff);
    }

}

