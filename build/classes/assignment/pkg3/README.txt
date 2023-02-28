./Assignment 3/src/assignment/pkg3/README.txt

// DISPLAYING THE DATA 

The init() method in javafx initialises the field game which creates a new minefield. The
minefield is then populated with mines using the populate() method.

The start() method in the MinesweeperGUI creates an empty 2d array of buttons, equivalent in
size to the dimensions of the minefield. It also creates a grid that stores the array of 
buttons. The buttons are added one by one using a nested for loop, with each button
having a mouse event if clicked on. Each time a button is left-clicked on, the step() method
in the Minefield class is run which updates the revealed, minedNeighbours and mined 
fields. A refresh() method is run which updates the display of the minefield depending
on the tile that was clicked on.

The refresh() method uses the getMineTile() method in the Minefield class to get the 
specific tile that corresponds to the button clicked on. If the button clicked on is a mine,
the isMined() method is checked in the MineTile class and if true, then the program places
a '*' as the label for the button. Similarly for a neighbour tile, the getMinedNeighbours()
method is accessed to display the number on the button as a label.

If a button is right clicked on, it is flagged, the flag() method is run and places a "?" 
on the button as a label. If a button is right clicked on and already has a "?", then
the unflag() method is run, where the flag is removed.

If a mine is clicked on, all the mines are made visible and the buttons become unclickable.


// EDITING THE DATA

The user can change the data by left and right clicking on the tiles. For example, if
a user right clicks on a tile, they can flag it. When a tile is flagged the program toggles
the boolean marked field to true using the toggleMarked() method. Similary when right 
clicked on again, the marked field is set to false to unflag it. Winning the game is
dependant on the user changing the correct tiles to marked = true.

