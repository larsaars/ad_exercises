#include <iostream>

using std::cout, std::endl;

int main(int argc, char **argv) {
    int k = std::stoi(argv[1]);

    // a number is prime, if it is only divisible by one and itself
    // using the "Sieve of Eratosthenes" algorithm

    // fill the bool array with numbers from 2 to k
    bool prime[k];
    for (int i = 0; i < k; i++) prime[i] = true;

    // i is the position in the boolean array, i + 1 is the number it refers to
    for (int i = 1; i < k; i++) {
        if (prime[i]) {
            // again, j refers to a position in the array
            // remove (set false) all the multipliers of i, since they cannot be
            // prime numbers
            for (int j = (i + 1) * 2 - 1; j < k; j += (i + 1)) prime[j] = false;
        }
    }

    // print out
    for (int i = 0; i < k; i++) {
        if (prime[i])
            cout << i + 1 << endl;
    }
}
