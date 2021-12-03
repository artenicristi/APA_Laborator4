package com.company;

import java.io.File;
import java.io.IOException;

public class Main {

    static Process runtime;

    static {
        try {
            runtime = Runtime.getRuntime().exec(new String[] {"cmd", "/C", "Start"});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        deleteFolder(new File("D:\\Univer\\FCIM_SEM_3\\Analiza si proiectarea algoritmilor (APA)\\Laborator_4_V4"));

        int n = 9;
        int start = 1;

//        int[][] matrix = new RandAdjMatrix(n).getMatrix(Cazuri.RAR);
        int a = 1_000_000;

        int[][] matrix = new int[][] {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 2, 5, 2, a, a, a, a, a},
                {0, 2, 0, 3, a, 1, a, a, a, a},
                {0, 5, 3, 0, 3, 1, 1, a, 1, a},
                {0, 2, a, 3, 0, a, a, 2, a, a},
                {0, a, 1, 1, a, 0, a, a, a, 7},
                {0, a, a, 1, a, a, 0, 2, 3, a},
                {0, a, a, a, 2, a, 2, 0, a, a},
                {0, a, a, 1, a, a, 3, a, 0, 1},
                {0, a, a, a, a, 7, a, a, 1, 0}
        };

        Dijkstra dijkstra = new Dijkstra(n, matrix);
        long time1 = System.nanoTime();
        dijkstra.execute(start);
        long time2 = System.nanoTime();

        System.out.println("DIJKSTRA: Permutations: " + dijkstra.iterations +
                ", time: " + String.format("%.10f seconds", (double)(time2 - time1) / 1_000_000_000));

        new Dijkstra(n, matrix).getPaths(start);

        Roy_Floyd roy_floyd = new Roy_Floyd(n, matrix);
        time1 = System.nanoTime();
        roy_floyd.execute();
        time2 = System.nanoTime();

        System.out.println("\n\nROY-FLOYD: Permutations: " + roy_floyd.iterations +
                ", time: " + String.format("%.10f seconds", (double)(time2 - time1) / 1_000_000_000));

        roy_floyd.getPaths();
    }

    public static void deleteFolder(File folder) {
        File[] files = folder.listFiles();
        if(files != null)
            for(File f: files) {
                if(f.isDirectory() == false)
                    if (f.toString().equals("D:\\Univer\\FCIM_SEM_3\\Analiza si proiectarea algoritmilor (APA)\\Laborator_4_V4\\Laborator_4_V4.iml"))
                        break;
                    f.delete();
            }
    }
}
