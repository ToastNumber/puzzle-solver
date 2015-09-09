#Puzzle Solver
This program can be used to generate a solution to sliding puzzles, such as the 15 puzzle. It can be controlled from the command line. The solutions generated are not optimal.

##Usage
1. Compile the source files into a `bin/puzzlesolver` folder. 
2. Navigate into `bin` folder.
3. Run the program using `java puzzlesolver/Puzzle WIDTH HEIGHT "SCRAMBLE"`.
	- `WIDTH` indicates the width of the puzzle
	- `HEIGHT` indicates the height of the puzzle.
	- `SCRAMBLE` indicates the tiles of the puzzle. 0 denotes a space. See the example below.

##Example
To generate a solution to the following puzzle:
```
8 3 5
0 4 1
2 6 7
```
Use the command ```java puzzlesolver/Puzzle 3 3 "8 3 5 0 4 1 2 6 7"```.
The solution generated will be ```LLDRULURDLURDRULDRDLUURDLULDRURDLLURDRULL```.

##Improvement
The current solution uses a priority queue to direct the breadth-first search. In order to improve the algorithm's speed and/or move efficiency, a different heuristic can be used. A simple heuristic is being used currently, which can be improved to better the algorithm's performance.