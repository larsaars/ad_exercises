cmake_minimum_required(VERSION 3.20)
project(e2)

set(CMAKE_CXX_STANDARD 14)

add_executable(e2
        interpreter.cpp utils.cpp utils.h)
