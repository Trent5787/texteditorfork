package edu.grinnell.csc207.texteditor;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import edu.grinnell.csc207.texteditor.GapBuffer;

public class GapBufferTests {
  
    /**
     * Performs all tests, normal and corner cases, on the gapbuffer.
     */
    @Test
    public void Test() {
        GapBuffer strBuff = new GapBuffer();
        
        //Initial check
        assertEquals(0, strBuff.getCursorPosition());
        assertEquals(0, strBuff.getStart());
        assertEquals(31, strBuff.getEnd());
        assertEquals(32, strBuff.getSize());
        //Insert Checks
        strBuff.insert('a');
        assertEquals(1, strBuff.getCursorPosition());
        assertEquals(1, strBuff.getStart());
        assertEquals(31, strBuff.getEnd());
        assertEquals(32, strBuff.getSize());
        //Deletion Checks
        strBuff.delete();
        assertEquals(0, strBuff.getCursorPosition());
        assertEquals(0, strBuff.getStart());
        assertEquals(31, strBuff.getEnd());
        assertEquals(32, strBuff.getSize());
        //Moving left
        strBuff.insert('a');
        strBuff.moveLeft();
        assertEquals(0, strBuff.getCursorPosition());
        assertEquals(0, strBuff.getStart());
        assertEquals(30, strBuff.getEnd());
        assertEquals(32, strBuff.getSize());
        //Moving right
        strBuff.moveRight();
        assertEquals(1, strBuff.getCursorPosition());
        assertEquals(1, strBuff.getStart());
        assertEquals(31, strBuff.getEnd());
        assertEquals(32, strBuff.getSize());
        //getChar
        assertEquals('a', strBuff.getChar(0));

        //doubling the gapbuffer
        GapBuffer strBuff2 = new GapBuffer();
        assertEquals(32, strBuff2.getSize());
        strBuff2.insert('a');
        strBuff2.insert('a');
        strBuff2.insert('a');
        strBuff2.insert('a');
        strBuff2.insert('a');
        strBuff2.insert('a');
        strBuff2.insert('a');
        strBuff2.insert('a');
        strBuff2.insert('a');
        strBuff2.insert('a');
        strBuff2.insert('a');
        strBuff2.insert('a');
        strBuff2.insert('a');
        strBuff2.insert('a');
        strBuff2.insert('a');
        strBuff2.insert('a');
        strBuff2.insert('a');
        strBuff2.insert('a');
        strBuff2.insert('a');
        strBuff2.insert('a');
        strBuff2.insert('a');
        strBuff2.insert('a');
        strBuff2.insert('a');
        strBuff2.insert('a');
        strBuff2.insert('a');
        strBuff2.insert('a');
        strBuff2.insert('a');
        strBuff2.insert('a');
        strBuff2.insert('a');
        strBuff2.insert('a');
        strBuff2.insert('a');
        strBuff2.insert('a');
        assertEquals(64, strBuff2.getSize());
        
        GapBuffer strBuff3 = new GapBuffer();
        //Corner cases
        //Moving left and right when there is nowhere to move left and right to.
        assertEquals(0, strBuff3.getCursorPosition());
        strBuff3.moveLeft(); //should just return
        strBuff3.moveRight(); //should just return
        //Deleting when there is nothing to delete
        strBuff3.delete(); //should just return
        assertEquals(0, strBuff3.getCursorPosition());
    }
    
    /**
     * Assures that the start position and the cursor position are always the same
     * @param val some int between 1 and 100
     * @return the gapbuffer
     */
    @Property
    public GapBuffer NoLargerGapBuffer (@ForAll @IntRange(min=1,max=100) int val) {
       GapBuffer strBuff = new GapBuffer();
       for(int i = 0; i < val; i++) {
           strBuff.insert('a');
       }
       assertEquals(strBuff.getStart(),strBuff.getCursorPosition());
       return(strBuff);
    }


}

