package com.company;

public class RandAdjMatrix {

    private int n;
    private int[][] matrix;

    public RandAdjMatrix(int n) {
        this.n = n;
        this.matrix = new int[n + 5][n + 5];
    }

    public int[][] getMatrix(Cazuri caz){

        double phi = getCaz(caz);
        int egdes = 0;
        int totalEgdes = n * (n + 1);

        while ((double) egdes / totalEgdes < phi) {

            int a = (int) (Math.random() * n + 1);
            int b = (int) (Math.random() * n + 1);
            int weight = (int) (Math.random() * 20 + 1);

            if (matrix[a][b] == 0 && a != b) {
                egdes ++;
                matrix[a][b] = weight;
            }
        }

        for (int i = 1; i < n + 1; i ++)
            for (int j = 1; j < n + 1; j ++)
                if (i != j && matrix[i][j] == 0)
                    matrix[i][j] = 1_000_000;

        return matrix;
    }

    private double getCaz(Cazuri caz) {

        double phi;

        while (true) {

            phi = Math.random();

            if (caz == Cazuri.RAR && phi > 0.32 && phi <= 0.41)
                break;
            else if (caz == Cazuri.MEDIU && phi > 0.41 && phi <= 0.68)
                break;
            else if (caz == Cazuri.DENS && phi > 0.68 && phi <= 1)
                break;
            else continue;
        }

        return phi;
    }
}
