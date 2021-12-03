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

        int n = 10;
        int start = 1;

        int[][] matrix = new RandAdjMatrix(n).getMatrix(Cazuri.RAR);

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
