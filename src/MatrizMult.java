import java.util.ArrayList;

public class MatrizMult {

    // encriptar a mensagem ------------------------------------------------
    public static ArrayList<Integer> multMatriz(int vetorSoma[], int esc) {


        int i = 0;
        int x = 0;
        int z = 0;

        int n = esc;


        int[][] multMatriz = {
            {1,  (int)Math.pow(n, 2) - n,                  (int)(2*n),                                                (int)(3*n)}, 
            {2,  (int)(2*Math.pow(n, 2) - 2*n + 1),        (int)(2*(n+1) + Math.pow(n, 3) + Math.pow(n, 2) ),    (int)(6*n + 2*n + 3)}, 
            {n,  (int)(Math.pow(n, 3) - Math.pow(n, 2)),   (int)(Math.pow(n, 2)*2 + 1),                          (int)(Math.pow(n, 2)*3 + 4*n)}, 
            {4,  (int)(4*Math.pow(n, 2) - 4*n),            (int)(8*n),                                            (int)(12*n + 1)}
        };

        

        ArrayList<Integer> msg = new ArrayList<>();
        ArrayList<Integer> arrayMult = new ArrayList<>();

        for (int j = 0; j < vetorSoma.length; j++) {

            arrayMult.add(vetorSoma[j]);

        }

        while (!(arrayMult.size()%4 == 0 && arrayMult.size() >= vetorSoma.length)) {   
  
            if (arrayMult.size() < vetorSoma.length) {
                arrayMult.add(vetorSoma[i]);

            } else {
                arrayMult.add(0);

            }

            i++;

        }

        for (int w = 0; w < arrayMult.size(); w++) {

            System.out.print(arrayMult.get(w) + " ");

        }

        x = 0;
        z = 0;

        while (z < vetorSoma.length) {


            while (x < 4) {
                msg.add(
                      multMatriz[x][0] * arrayMult.get(z) 
                    + multMatriz[x][1] * arrayMult.get(z + 1)
                    + multMatriz[x][2] * arrayMult.get(z + 2)
                    + multMatriz[x][3] * arrayMult.get(z + 3)
                );

                x++;

            }

            x = 0;

            if (z == vetorSoma.length) {    
                break;

            } else {
                z += 4;
                
            }


        }

        return msg;
        
    }


    // decriptar a mensagem ------------------------------------------------
    public static ArrayList<Integer> invMatriz(int vetorSoma[], int n) {

        int[][] multMatriz = {
            {1,  (int)Math.pow(n, 2) - n,                  (int)(2*n),                                                (int)(3*n)}, 
            {2,  (int)(2*Math.pow(n, 2) - 2*n + 1),        (int)(2*(n+1) + Math.pow(n, 3) + Math.pow(n, 2) ),    (int)(6*n + 2*n + 3)}, 
            {n,  (int)(Math.pow(n, 3) - Math.pow(n, 2)),   (int)(Math.pow(n, 2)*2 + 1),                          (int)(Math.pow(n, 2)*3 + 4*n)}, 
            {4,  (int)(4*Math.pow(n, 2) - 4*n),            (int)(8*n),                                            (int)(12*n + 1)}
        };

        

        double[][] matrizInvAux = Inversa.invert(multMatriz);
        int[][] invMatriz = new int[4][4];


        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                invMatriz[i][j] = (int)(matrizInvAux[i][j]);
            }
        }

        int i = 0;
        int x = 0;
        int z = 0;


        
        ArrayList<Integer> msg = new ArrayList<>();
        ArrayList<Integer> arrayMult = new ArrayList<>();

        for (int j = 0; j < vetorSoma.length; j++) {

            arrayMult.add(vetorSoma[j]);

        }

        while (!(arrayMult.size()%4 == 0 && arrayMult.size() >= vetorSoma.length)) {   
  
            if (arrayMult.size() < vetorSoma.length) {
                arrayMult.add(vetorSoma[i]);

            } else {
                arrayMult.add(0);
                

            }

            i++;

        }

        x = 0;
        z = 0;

        while (z < vetorSoma.length) {


            while (x < 4) {
                msg.add(
                      invMatriz[x][0] * arrayMult.get(z) 
                    + invMatriz[x][1] * arrayMult.get(z + 1)
                    + invMatriz[x][2] * arrayMult.get(z + 2)
                    + invMatriz[x][3] * arrayMult.get(z + 3)
                );

                x++;

            }

            x = 0;

            if (z == vetorSoma.length) {    
                break;

            } else {
                z += 4;
                
            }


        }

        return msg;
        
    }


}
