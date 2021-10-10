"""
DISCLAIMER
basic implementation of a matrix:
watch out: this code is not optimised
if you want fast matrix multiplication etc. use numpy or a compiled language as c++ instead
"""
from random import randint, uniform


class Matrix:
    # m = rows
    # n = columns
    def __init__(self, m: int, n: int):
        self.m, self.n = m, n
        self.matrix = [[0] * self.n for _ in range(self.m)]

    def print(self):
        print(f"{self.matrix} ({self.m} x {self.n})")

    def input(self):
        self.matrix = [[int(x) for x in input().split()] for _ in range(self.m)]

    def random_fill(self, min_value=-10, max_value=10, round_value=2):
        if isinstance(max_value, int) and isinstance(min_value, int):
            self.matrix = [[randint(min_value, max_value) for x in range(self.n)] \
                           for x in range(self.m)]
        else:
            self.matrix = [[round(uniform(min_value, max_value), round_value) for x in range(self.n)] \
                           for x in range(self.m)]
        return self

    def add(self, m2):
        if not m2.m == self.m or not m2.n == self.n:
            print(f"Different dimensions: A:{self.m}x{self.n}, B:{m2.m}x{m2.n}")
            return

        n_mat = Matrix(self.n, self.m)

        for row in range(self.m):
            for col in range(self.n):
                n_mat.matrix[row][col] += m2.matrix[row][col]

        return n_mat

    def multiply(self, m2):
        if not self.n == m2.m:
            print(f"Wrong dimensions for matrix multiplication: A:{self.m}x{self.n}, B:{m2.m}x{m2.n}")
            return

        n_mat = Matrix(self.n, m2.m)

        product = [[0] * m2.n for _ in range(self.m)]
        for row in range(self.m):
            for col in range(m2.n):
                for inner in range(self.n):
                    product[row][col] += self.matrix[row][inner] * m2.matrix[inner][col]
        n_mat.matrix = product

        return n_mat


if __name__ == '__main__':
    print('input a 3x3 matrix: ')
    m = Matrix(3, 3)
    m.input()
    print('this matrix:')
    m.print()

    m2 = Matrix(3, 3)
    m2.random_fill()
    print('\nrandom matrix:')
    m2.print()

    print('\naddition')
    m.add(m2).print()

    print('\nmultiplication')
    m.multiply(m2).print()
