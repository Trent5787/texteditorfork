# CSC 207: Text Editor

**Author**: Trenton Cochell

## Resources Used

+ Acknowledgements:
 * - Java 23 coding language
 * - Java 23 coding documentation from Oracle help center.
 * - Apache Netbeans IDE
 * - Course website by Professor Osera for instructions:
 *   https://osera.cs.grinnell.edu/ttap/data-structures-labs/text-editor.html
 * - David, my tutor, who guided me through the process of fixing many of the problems with the drawBuffer and toString method, and other general help with the project. 


## Changelog
Fixed and debugged drawBuffer and methods in gapBuffer, some parts of main, and the toString method to get the gapbuffer to work correctly. As far as I can tell, everything should work now. 

## Analyzing the simple string Buffer
* Relevant inputs: explicitly: a character (ch) implicitly: the current cursor position, and the length
of the string buffer.
* Critical operation: shifting each element of the string buffer after the character to the right,
to create room for the ch character. 
* Mathematical model: Assuming that the cursor position is called "p" and the length of the string buffer
is "n", we have the following:
    insert() = n - p
* Big O characterization: insert is O(n)