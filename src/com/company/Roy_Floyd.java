package com.company;

public class Roy_Floyd {

    private int n;
    private int[][] t;
    int iterations = 0;

    public Roy_Floyd(int n, int[][] t) {
        this.n = n;
        this.t = t;
    }

    public void execute() {
        for (int l = 1; l < n + 1; l ++)
            for (int i = 1; i < n + 1; i ++)
                for (int j = 1; j < n + 1; j ++)
                    if (t[i][j] > t[i][l] + t[l][j]) {
                        iterations ++;
                        t[i][j] = t[i][l] + t[l][j];
                    }
    }

    public void getPaths() {
        execute();

        System.out.println("\nROY-FLOYD");

        for (int i = 1; i < n + 1; i ++) {
            for (int j = 1; j < n + 1; j ++)
                if (t[i][j] == 1_000_000)
                    System.out.print(String.format("%5d", -1));
                else System.out.print(String.format("%5d", t[i][j]));
            System.out.println();
        }
    }
}
