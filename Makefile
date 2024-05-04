# Define variables for directories and files
SRC_DIR = src/CLI
BIN_DIR = bin
PROGRAM = WordLadderSolver
DICTIONARY = dictionary.txt

# Default target to compile and run the program
all: compile run

# Compile the Java files and move .class files to bin directory
compile:
    @echo Compiling and running $(PROGRAM)...
    @rm -rf $(BIN_DIR)
    @mkdir $(BIN_DIR)
    @javac -d $(BIN_DIR) $(SRC_DIR)/$(PROGRAM).java

# Run the program if compilation is successful
run:
    @java -cp $(BIN_DIR) src.CLI.$(PROGRAM) $(DICTIONARY)

# Clean up the bin directory
clean:
    @echo Cleaning up...
    @rm -rf $(BIN_DIR)

# Phony targets to avoid conflicts with file names
.PHONY: all compile run clean
