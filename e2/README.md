# interpreter.cpp
Simulating a register machine. Execute your (not really) 'assembler' programs after compiling with


    ./interpreter NUMBER_OF_REGISTERS PATH_TO_YOUR_PROGRAM

## Writing a program for the interpreter (commands)

> `ADDRESS` is always the index of an int in the simulated registers array
> executed is always by the scheme
> `COMMAND[string] [int] [int]`

command | action
--- | ---
jez ADDRESS LINE_NR | jump to LINE_NR if value at ADDRESS is equal zero
jnz ADDRESS LINE_NR | jump to LINE_NR if value at ADDRESS is not equal zero
jgz ADDRESS LINE_NR | jump to LINE_NR if value at ADDRESS is greater zero
jlz ADDRESS LINE_NR | jump to LINE_NR if value at ADDRESS is lower zero
set ADDRESS VALUE | set value at ADDRESS to VALUE

in ADDRESS | read in the value from stdin to ADDRESS
print ADDRESS | print value at ADDRESS
exit | exit the program instantly

