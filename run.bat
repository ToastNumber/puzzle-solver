@ECHO off

:main
	ECHO ---Puzzle-Solver---
	ECHO.
	IF NOT EXIST bin/puzzlesolver/Puzzle.class GOTO fail
	
	SET width=""
	SET height=""
	SET scramble=""
	
	SET /p width="Enter width: "
	SET /p height="Enter height: "
	SET /p scramble="Enter scramble: "

	java -cp bin puzzlesolver.Puzzle %width% %height% %scramble%

	SET /p dummy="Press ENTER to continue ..."
	CLS
	GOTO main
	
:fail
	ECHO Error: Could not find or load main class puzzlesolver.Puzzle
	ECHO Hint: Try compiling the source files using `compile.bat`
	ECHO.
	PAUSE
	EXIT