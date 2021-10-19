# interpreter.cpp
Simulating a register machine. Execute your (not really) 'assembler' programs after compiling with


    ./interpreter NUMBER_OF_REGISTERS PATH_TO_YOUR_PROGRAM

## Writing a program for the interpreter (commands)

> comment: `ADDRESS` is always the index of an int in the simulated registers array
> comment: executed is always by the scheme `COMMAND[string] [int] [int]`

command | action
--- | ---
jez ADDRESS LINE_NR | jump to LINE_NR if value at ADDRESS is zero
