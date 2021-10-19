//
// Created by me on 19.10.21.
//

#include "utils.h"

namespace utils {
    bool isWhitespace(const char c) {
        return c == ' ' || c == '\n' || c == '\t' || c == '\r' || c == 11;
    }

    std::string trim(const std::string &s) {
        unsigned long left = 0, right = s.length() - 1;

        while (left <= right && isWhitespace(s[left]))
            ++left;

        while (right > left && isWhitespace(s[right]))
            --right;

        return s.substr(left, 1 + right - left);
    }

    void tokenize(std::string const &str, char delim,
                  std::vector<std::string> &out) {
        size_t start;
        size_t end = 0;

        while ((start = str.find_first_not_of(delim, end)) != std::string::npos) {
            end = str.find(delim, start);
            out.push_back(str.substr(start, end - start));
        }
    }
}
