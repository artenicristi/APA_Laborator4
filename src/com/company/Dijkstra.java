package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Dijkstra {

    private int n;
    private int[][] matrix;
    private int[] parent;
    private boolean[] visited;
    private int[] weights;
    int iterations = 0;

    public Dijkstra(int n, int[][] matrix) {
        this.n = n;
        this.matrix = matrix;
        this.parent = new int[n + 5];
        this.visited = new boolean[n + 5];
        this.weights = new int[n + 5];
    }

    public void execute(int current){

        visited[current] = true;

        for (int i = 1; i < n + 1; i ++) {
            iterations ++;
            weights[i] = matrix[current][i];
            if (i != current && matrix[current][i] < 1_000_000)
                parent[i] = current;
        }

        for (int i = 1; i < n; i ++) {
            int min = 1_000_000;

            for (int j = 1; j < n + 1; j ++)
                if (!visited[j] && weights[j] < min) {
                    iterations ++;
                    min = weights[j];
                    current = j;
                }

            visited[current] = true;

            for (int j = 1; j < n + 1; j ++)
                if (weights[current] + matrix[current][j] < weights[j]) {
                    iterations ++;
                    weights[j] = weights[current] + matrix[current][j];
                    parent[j] = current;
                }
        }
    }

    private void input(){

        if (n > 15)
            return;

        try(FileWriter writer = new FileWriter("input.txt")) {

            writer.write("digraph G {\n");

            for (int i = 1; i < n + 1; i ++)
                for (int j = 1; j < n + 1; j ++)
                    if (matrix[i][j] > 0 && matrix[i][j] < 1_000_000)
                        writer.write(String.format("\t%d -> %d[label=\"%d\", weight=\"%d\"];\n", i, j, matrix[i][j], matrix[i][j]));

            writer.write("}");
            Runtime.getRuntime().exec("dot -T png input.txt -o input.png");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getPaths(int start) {

        execute(start);

        System.out.print("\nDIJKSTRA\nWeights: ");
        for (int j = 1; j < n + 1; j ++)
            if (weights[j] == 1_000_000)
                System.out.print(-1 + " ");
            else System.out.print(weights[j] + " ");

        System.out.println();

        input();

        for (int i = 1; i < n + 1; i ++)
            if (weights[i] != 0) {

                List<Integer> path = new ArrayList<>();

                int a = i;

                while (a != 0) {
                    path.add(a);
                    a = parent[a];
                }

                if (path.size() == 1)
                    System.out.println("Nodul " + path.get(0) + " este izolat");
                else {
                    System.out.print("Drum de la " + start + " la " + i + ":: ");

                    try(FileWriter writer = new FileWriter("Drum_" + start + "__" + i + ".txt")){
                        writer.write("digraph G {\n");

                        int x = path.get(path.size() - 1);

                        for (int j = path.size() - 2; j >= 0; j --) {
                            System.out.print(x + "---");

                            writer.write(String.format("\t%d -> ", x));

                            int y = path.get(j);
                            System.out.print(String.format("(%d)-->", matrix[x][y]));

                            writer.write(String.format("%d[label=\"%d\", weight=\"%d\"];\n", y, matrix[x][y], matrix[x][y]));

                            x = y;
                        }
                        System.out.println(path.get(0));

                        writer.write("}");

                        if (n <= 15)
                            Runtime.getRuntime().exec("dot -T png " + ("Drum_" + start + "__" + i + ".txt") + " -o " +
                                "" + ("Drum_" + start + "__" + i + ".png"));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
    }
}
