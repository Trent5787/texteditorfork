package edu.grinnell.csc207.texteditor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import net.jqwik.api.*;
import net.jqwik.api.constraints.*;

import java.nio.file.Path;
import java.nio.file.Paths;
    
import edu.grinnell.csc207.texteditor.SimpleStringBuffer;


public class SimpleStringBufferTests {

    @Test
    public void Test() {
        SimpleStringBuffer strBuff = new SimpleStringBuffer();
        //Insertion check
        strBuff.insert('a');
        assertEquals(1, strBuff.getSize());
        //Deletion check
        strBuff.insert('b');
        strBuff.delete();
        assertEquals(1, strBuff.getSize());
        //Get cursor position
        assertEquals(1, strBuff.getCursorPosition());
        strBuff.insert('c');
        //Move left
        strBuff.moveLeft();
        assertEquals(1,strBuff.getCursorPosition());
        //Move right
        strBuff.moveRight();
        assertEquals(2,strBuff.getCursorPosition());
        //getChar
       assertEquals('c',strBuff.getChar(1));
        String str = strBuff.toString();
        assertEquals("ac", str);
        
        //Corner cases:
        strBuff.delete();
        strBuff.delete();
        assertEquals(0, strBuff.getCursorPosition());
        
        strBuff.moveLeft(); //should just return
       
        strBuff.moveRight();
        strBuff.moveRight();
        strBuff.moveRight(); //should just return
        
    }
    
    @Property
    public SimpleStringBuffer greaterThanZero (@ForAll @IntRange(min=1,max=100) int val) {
       SimpleStringBuffer strBuff = new SimpleStringBuffer();
       strBuff.insert('b');
       strBuff.insert('b');
       strBuff.insert('b');
       strBuff.insert('b');
       strBuff.insert('b');
       strBuff.insert('b');
       for (int i = 0; i < val; i++) {
          strBuff.delete(); 
       }
       return strBuff;
    }

}
