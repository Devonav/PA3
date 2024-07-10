README
COP 3503C - Assignment 3
Author: Devon Villalona
Overview
This program is designed to analyze the connectivity of a network of nodes, with an emphasis on understanding how the connectivity changes when specific edges are removed and subsequently re-added. The program uses the Disjoint Set Union (DSU) data structure to efficiently manage and query the connectivity of the network.

Key Concepts
Disjoint Set Union (DSU): Also known as Union-Find, this data structure helps keep track of a set of elements partitioned into disjoint (non-overlapping) subsets. It supports two main operations: finding the root of a set (find) and merging two sets (union).
Connectivity Calculation: The program calculates the connectivity of the network by summing the squares of the sizes of each connected component.
Input
The program expects input in the following format:

An integer n representing the number of nodes.
An integer m representing the number of edges.
An integer d representing the number of edges to be destroyed.
The next m lines each contain two integers u and v, representing an edge between nodes u and v.
The next d lines each contain an integer representing the index (1-based) of the edge to be destroyed.
Output
The program outputs the connectivity of the network after the initial destruction sequence and then after each re-addition of the destroyed edges, in the correct order.
