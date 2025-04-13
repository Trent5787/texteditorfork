package edu.grinnell.csc207.texteditor;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.swing.SwingTerminalFrame;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The driver for the TextEditor Application.
 */
public class TextEditor {

    /**
     * Commits the changes made in the gap to the screen
     * @param buf from where to draw the contents to the screen
     * @param screen where to draw the buffer to
     * @throws IOException
     */
    public static void drawBuffer(GapBuffer buf, Screen screen) throws IOException {
    screen.clear(); 
    
    
    int inc = 0;
    int row = 0;
    for (int i = 0; i < buf.getSize(); i++) { 
        if (i >= buf.getStart() && i <= buf.getEnd()) { //implemented this to attempt to prevent traversals into the gap
            System.out.println(buf.getStart());
            System.out.println(buf.getEnd());
            TerminalPosition pos = new TerminalPosition(inc,row);
            screen.setCursorPosition(pos);
            continue; 
        } 
        char ch = buf.getChar(i); // Get the character at index i
       
        TextCharacter textChar = TextCharacter.fromCharacter(ch)[0];  
        
        screen.setCharacter(inc, row, textChar); // iterate through every row and column
         if (ch == '\n') {
            inc = 0;
            row++;
        } else {
            inc++;
        }
        System.out.println("row:" + row);
        System.out.println("inc:" + inc);
        System.out.println("char:" + textChar);
    }
    screen.refresh(); 
}
    
    /**
     * The main entry point for the TextEditor application.
     * @param args command-line arguments.
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: java TextEditor <filename>");
            return;
        }

        // TODO: fill me in with a text editor TUI!
        String pathString = args[0];
        System.out.format("Loading %s...\n", pathString);
        
        SwingTerminalFrame term = new DefaultTerminalFactory().createSwingTerminal();
        term.setVisible(true);
        Screen screen = new TerminalScreen(term);
        screen.startScreen();
        Path pathPath = Paths.get(pathString);
       
        //Checking for validity
        if (Files.exists(pathPath) == false) {
            System.out.println("Not a valid file or directory. Exiting.");
            return;
        }
        if (Files.isRegularFile(pathPath) == false) {
            System.out.println("Must be a file. Exiting.");
            return;
        }
        
        //Contents from file read and displayed
        String str = Files.readString(pathPath);
        System.out.println("str is (should be original contents of file):" + str);
        char[] charArray = str.toCharArray();
        GapBuffer gap = new GapBuffer(); //WARNING: changed from size of gap to size of gap (1) + size of everything else

        for(int i = 0; i < charArray.length; i++) {
            gap.insert(charArray[i]);
        }

        System.out.println("Gap is (should still be original contents of file):" + gap.toString()); 
        //read characters from gap buffer and write to the screen
        
        //reading inputs, checking what they are, and performing actions based on that
        while(true) {
            drawBuffer(gap,screen);
            KeyStroke stroke = screen.readInput();
          
          if ((stroke.getKeyType()).equals(KeyType.Character)) {
              char a = stroke.getCharacter();
              gap.insert(a);
          } else if ((stroke.getKeyType()).equals(KeyType.ArrowLeft)) { 
              gap.moveLeft();
          } else if ((stroke.getKeyType()).equals(KeyType.ArrowRight)) { 
              gap.moveRight();
          } else if ((stroke.getKeyType()).equals(KeyType.Backspace)) { 
              gap.delete();
          } else if ((stroke.getKeyType()).equals(KeyType.Escape)) { 
              break;
          } else {
              System.out.println("Unrecognized input");
          }
        }
        
        String contents = gap.toString();
        Files.writeString(pathPath,contents); //commit changes from gapbuffer to the file
        
        screen.stopScreen();
    }
}

