import time
from typing import Callable


def time_func(func: Callable, *args, **kwargs):
    start = time.time()
    res = func(*args, **kwargs)
    end = time.time()

    print(f'execution time of \'{func.__name__}\': {(end - start) * 1000}ms')

    return res
