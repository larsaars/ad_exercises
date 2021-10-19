#include <fstream>
#include <iostream>
#include <string>
#include <vector>

using std::cerr, std::endl, std::cout, std::cin, std::vector, std::string;


void tokenize(string const &, char, vector<string> &);

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
        // split commands with space
        vector<string> cCmds;
        tokenize(cmds[pointer], ' ', cCmds);

        // increase pointer as default
        pointer++;

        // implement the commands
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

void tokenize(string const &str, char delim,
              vector<string> &out) {
    size_t start;
    size_t end = 0;

    while ((start = str.find_first_not_of(delim, end)) != string::npos) {
        end = str.find(delim, start);
        out.push_back(str.substr(start, end - start));
    }
}