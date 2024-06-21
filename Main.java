/*
Summer 24
COP 3503C Assignment 3
This program is written by: Devon Villalona*/


import java.util.*;

public class Main {
    static int[] parent, rank, size;
    static int n, m, d;
    static List<int[]> connections = new ArrayList<>();
    static List<Integer> destructions = new ArrayList<>();
    static long initialConnectivity;

    // Main method to read input and call the necessary methods to solve the problem and print the results in the correct order  
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        d = sc.nextInt();

        parent = new int[n + 1];
        rank = new int[n + 1];
        size = new int[n + 1];

        // Initialize DSU
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        // Read connections
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            connections.add(new int[]{u, v});
        }

        // Read destruction sequence
        for (int i = 0; i < d; i++) {
            destructions.add(sc.nextInt());
        }

        // Calculate initial connectivity without the edges to be destroyed
        Set<Integer> destroyedEdgesSet = new HashSet<>(destructions);
        for (int i = 0; i < m; i++) {
            if (!destroyedEdgesSet.contains(i + 1)) {
                union(connections.get(i)[0], connections.get(i)[1]);
            }
        }
        initialConnectivity = calculateConnectivity();

        // Prepare result list
        List<Long> results = new ArrayList<>();
        results.add(initialConnectivity);

        // Process destructions in reverse order to construct the network from scratch
        for (int i = d - 1; i >= 0; i--) {
            int connIdx = destructions.get(i) - 1;  // Convert to 0-based index
            int u = connections.get(connIdx)[0];
            int v = connections.get(connIdx)[1];
            addEdge(u, v);
            long connectivity = calculateConnectivity();
            results.add(connectivity);
        }

        // Print results in the correct order
        for (int i = results.size() - 1; i >= 0; i--) {
            System.out.println(results.get(i));
        }
    }

    static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
                size[rootX] += size[rootY];
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
                size[rootY] += size[rootX];
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
                size[rootX] += size[rootY];
            }
        }
    }

    static void addEdge(int u, int v) {
        union(u, v);
    }
    // Calculate the connectivity of the network by summing the square of the size of each connected component 
    static long calculateConnectivity() {
        long connectivity = 0;
        for (int i = 1; i <= n; i++) {
            if (parent[i] == i) {
                connectivity += (long) size[i] * size[i];
            }
        }
        return connectivity;
    }
}
