import argparse
from typing import Callable


def gcd_iterative(a: int, b: int) -> int:
    while True:
        r = a % b
        a = b
        b = r

        if r == 0:  # not r
            break

    return a


def gcd_recursive(a: int, b: int) -> Callable:
    return gcd_recursive(b, a % b) if b else a


def parse_args():
    parser = argparse.ArgumentParser(description='find greatest common divisor')
    parser.add_argument('-a', '--a', type=int, required=True, help='number 1')
    parser.add_argument('-b', '--b', type=int, required=True, help='number 2')
    return parser.parse_args()


if __name__ == '__main__':
    args = parse_args()

    print(f'gcd_iterative({args.a}, {args.b}) = {gcd_iterative(args.a, args.b)}')
    print(f'gcd_recursive({args.a}, {args.b}) = {gcd_recursive(args.a, args.b)}')
