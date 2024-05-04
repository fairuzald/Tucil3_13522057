@echo off
rem Compile Java files and move .class files to bin directory
echo Compiling and running WordLadderSolver...

rem Set the source and bin directories
set SRC_DIR=src\CLI
set BIN_DIR=bin

rem Delete contents of bin directory
rmdir /s /q %BIN_DIR%
mkdir %BIN_DIR%

rem Compile WordLadderSolver.java and move .class file to bin directory
javac -d %BIN_DIR% %SRC_DIR%\WordLadderSolver.java

rem If compilation is successful, run the program
if %errorlevel% == 0 (
    java -cp %BIN_DIR% src.CLI.WordLadderSolver dictionary.txt
) else (
    echo Compilation failed.
)
