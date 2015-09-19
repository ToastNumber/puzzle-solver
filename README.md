#Puzzle Solver
This program can be used to generate a solution to sliding puzzles, such as the 15 puzzle. It can be controlled from the command line. The solutions generated are not optimal.

##Usage
Run the program using `java puzzlesolver/Puzzle WIDTH HEIGHT "SCRAMBLE"`.
	- `WIDTH` indicates the width of the puzzle
	- `HEIGHT` indicates the height of the puzzle.
	- `SCRAMBLE` indicates the tiles of the puzzle. 0 denotes a space. See the example below.

The output will consist of a combination of the four letters `ULDR`. 
- `U` denotes an upwards move, where a tile is moved upwards.
- `R` denotes a rightwards move.
- `D` denotes a downwards move.
- `L` denotes a leftwards move.

###Automated
- `compile.bat` automatically compiles the source files into a bin folder.
- `run.bat` automatically runs the program, and asks for the width, height, and scramble.

##Example
To generate a solution to the following puzzle:
```
8 3 5
0 4 1
2 6 7
```
Run `run.bat` and enter
```
---Puzzle-Solver---

Enter width: 3
Enter height: 3
Enter scramble: "8 3 5 0 4 1 2 6 7"
```
The output will be 
```
8 3 5
0 4 1
2 6 7
Solution: LLDRULURDLURDRULDRDLUURDLULDRURDLLURDRULL
```.

##Improvement
The current solution uses a priority queue to direct the breadth-first search. In order to improve the algorithm's speed and/or move efficiency, a different heuristic can be used. A simple heuristic is being used currently, which can be improved to better the algorithm's performance.