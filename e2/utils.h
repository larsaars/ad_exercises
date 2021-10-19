//
// Created by me on 19.10.21.
//

#ifndef E2_UTILS_H
#define E2_UTILS_H

#include <string>
#include <vector>


namespace utils {
    // trim string (remove all redundant whitespaces from right and left of string)
    std::string trim(const std::string &);

    // tokenize string to vector (split by whitespaces)
    void tokenize(std::string const &, char, std::vector<std::string> &);
};


#endif //E2_UTILS_H
