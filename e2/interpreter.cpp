#include <fstream>
#include <iostream>
#include <string>
#include <vector>
#include "utils.h"

using std::cerr, std::endl, std::cout, std::cin, std::vector, std::string;


bool execute(int &pointer, int ax[], const vector<string> &cCmds);

int main(int argc, char **argv) {
    // check param lens
    if (argc < 3) {
        cerr << "no ax size (storage) and input file given" << endl;
        return -1;
    }

    // command list and index ('pointer'),
    // which command is executed right now
    int pointer = 0;
    vector<string> cmds;

    // storage units
    int ax[std::stoi(argv[1])];
    for (int &i: ax) i = 0;

    // read in file
    std::fstream file(argv[2], std::ios::in);
    if (file.good()) {
        char bin[200];
        while (file.getline(bin, 200))
            cmds.emplace_back(bin);
    } else
        file.clear();


    // execute while pointer is still under the commands size
    while (pointer < cmds.size()) {
        // PREPROCESSING
        // trim string
        cmds[pointer] = utils::trim(cmds[pointer]);

        // increase pointer as default action
        pointer++;

        // skip comments (with ';')
        if (cmds[pointer][0] == ';')
            continue;

        // split commands with space
        vector<string> cCmds;
        utils::tokenize(cmds[pointer - 1], ' ', cCmds);

        // continue if line is empty
        if (cCmds.empty())
            continue;

        // implement the commands
        // if the execute method returns a true boolean, exit the program (the loop)
        if (execute(pointer, ax, cCmds))
            break;
    }

    return 0;
}

// execute cmds given
bool execute(int &pointer, int ax[], const vector<string> &cCmds) {
    string cmd = cCmds[0];
    int addr = -1, extra = -1;
    if (cCmds.size() > 1)
        addr = std::stoi(cCmds[1]);
    if (cCmds.size() > 2)
        extra = std::stoi(cCmds[2]);
    if (cmd == "jez") {
        if (ax[addr] == 0) pointer = extra;
    } else if (cmd == "jnz") {
        if (ax[addr] != 0) pointer = extra;
    } else if (cmd == "jgz") {
        if (ax[addr] > 0) pointer = extra;
    } else if (cmd == "jlz") {
        if (ax[addr] < 0) pointer = extra;
    } else if (cmd == "set") {
        ax[addr] = extra;
    } else if (cmd == "exit") {
        return true;
    } else if (cmd == "print") {
        cout << ax[addr] << endl;
    } else if (cmd == "in") {
        cin >> ax[addr];
    } else if (cmd == "add") {
        ax[addr] += ax[extra];
    } else if (cmd == "sub") {
        ax[addr] -= ax[extra];
    } else if (cmd == "mul") {
        ax[addr] *= ax[extra];
    } else if (cmd == "div") {
        ax[addr] /= ax[extra];
    }
    return false;
}
