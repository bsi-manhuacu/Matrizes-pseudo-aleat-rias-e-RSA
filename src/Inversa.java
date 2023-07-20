public class Inversa {

    public static double[][] invert(int[][] A) {
        int n = A.length;
        double[][] B = new double[n][n];

        for (int i = 0; i < n; i++) {
            B[i][i] = 1;
        }

        for (int k = 0; k < n; k++) {
            double temp = A[k][k];
            for (int j = 0; j < n; j++) {
                A[k][j] /= temp;
                B[k][j] /= temp;
            }

            for (int i = k + 1; i < n; i++) {
                temp = A[i][k];
                for (int j = 0; j < n; j++) {
                    A[i][j] -= A[k][j] * temp;
                    B[i][j] -= B[k][j] * temp;
                }
            }
        }

        for (int k = n - 1; k > 0; k--) {
            for (int i = k - 1; i >= 0; i--) {
                double temp = A[i][k];
                for (int j = 0; j < n; j++) {
                    A[i][j] -= A[k][j] * temp;
                    B[i][j] -= B[k][j] * temp;
                }
            }
        }

        return B;
    }

}