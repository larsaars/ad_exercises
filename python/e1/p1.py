import argparse

from utils import time_func


def gcd_iterative(a: int, b: int) -> int:
    while True:
        r = a % b
        a = b
        b = r

        if r == 0:  # not r
            break

    return a


def gcd_recursive(a: int, b: int):
    return gcd_recursive(b, a % b) if b else a


def scm(a: int, b: int) -> int:
    # gcd(a, b) * scm(a, b) = |a * b|
    return abs(a * b) // gcd_iterative(a, b)


def parse_args():
    parser = argparse.ArgumentParser(description='find greatest common divisor')
    parser.add_argument('-a', '--a', type=int, required=True, help='number 1')
    parser.add_argument('-b', '--b', type=int, required=True, help='number 2')
    return parser.parse_args()


if __name__ == '__main__':
    args = parse_args()

    print(f'gcd_iterative({args.a}, {args.b}) = {time_func(gcd_iterative, args.a, args.b)}')
    print(f'gcd_recursive({args.a}, {args.b}) = {time_func(gcd_recursive, args.a, args.b)}')
    print(f'scm({args.a}, {args.b}) = {time_func(scm, args.a, args.b)}')

    # because of the call stack the recursive function is normally slower, but better readable

    print(f"{'a':^3}|{'b':^3}|{'gcd':^6}|{'kcm':^6}|{'a*b':^6}")
    print(f"{'':-^28}")
    for i in range(30, 40 + 1):
        for k in range(i, 40 + 1):
            print(f"{i:>3}|{k:>3}|{gcd_iterative(i, k):>6}|{scm(i, k):>6}|{(i * k):>6}")

    # gcd(a, b) * scm(a, b) = |a * b|
