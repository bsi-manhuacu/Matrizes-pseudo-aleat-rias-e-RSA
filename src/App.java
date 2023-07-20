import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class App extends JFrame implements ActionListener {

    JPanel painel1 = new JPanel();
    JPanel painel2 = new JPanel();
    JPanel painel3 = new JPanel();

    BoxLayout boxPrin = new BoxLayout(painel1, BoxLayout.Y_AXIS);
    BoxLayout boxSec = new BoxLayout(painel2, BoxLayout.X_AXIS);
    BoxLayout boxTer = new BoxLayout(painel3, BoxLayout.Y_AXIS);

    JLabel jlTitulo = new JLabel("RSA");
    JLabel jlMsg = new JLabel("Mensagem");
    JLabel jlChave = new JLabel("Chave");
    JLabel jlMsgFinal = new JLabel("Mensagem final");

    JTextField jtMsg = new JTextField(40);
    JTextField jtChave = new JTextField(40);
    JTextField jtMsgFinal = new JTextField(40);

    JButton jbCrip = new JButton("Criptografar");
    JButton jbDecrip = new JButton("Decriptografar");

        int a;
        int e; 
        int n;
        int p = 1;
        String blocoFinal;
        int A;
        int E;
     
        int pCodificacao = 103;
        int qCodificacao = 107;

        int mCodificacao = pCodificacao * qCodificacao;

        // chave pública
        int nCodificacao = (pCodificacao - 1) * (qCodificacao - 1);

        int eCodificacao = 47;
        
      
   
     public String CalculoFermat(int E, int p, int A, int n, int a, int e ) {

        for(int i = 1;E!=0;i++){
            if(E%2==0){
                E = E/2;
            } else {
                p = (A*p)%n;
                E = (E-1)/2;
            }
            A = (A*A) %n;
        }
        blocoFinal = String.valueOf(p);
        return (blocoFinal);
    }
     
     
     public int Diofantina(int aDiofantina, int bDiofantina ) {

        int x = 0, y = 1, lastX = 1, lastY = 0, temp;
        
        while (bDiofantina != 0) {
            int q = aDiofantina / bDiofantina;
            int r = aDiofantina % bDiofantina;
            
            aDiofantina = bDiofantina;
            bDiofantina = r;
            
            temp = x;
            x = lastX - q * x;
            lastX = temp;
            
            temp = y;
            y = lastY - q * y;
            lastY = temp;
        }
        
        if(lastX < 0){
            return lastX + nCodificacao;
        }
        else{
            return lastX;
        }
    }

    public App() {
        super("RSA");
        setLocationRelativeTo(null);
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        painel1.setLayout(boxPrin);
        painel1.add(jlTitulo);
        painel1.add(jlMsg);
        painel1.add(jtMsg);
        painel1.add(jlChave);
        painel1.add(jtChave);

        painel2.setLayout(boxSec);
        painel2.add(Box.createRigidArea(new Dimension(10, 0)));
        painel2.add(jbCrip);
        painel2.add(Box.createHorizontalGlue());
        painel2.add(jbDecrip);
        painel2.add(Box.createRigidArea(new Dimension(10, 0)));

        painel3.setLayout(boxTer);
        painel3.add(jlMsgFinal);
        painel3.add(jtMsgFinal);

        add(painel1, BorderLayout.NORTH);
        add(painel2, BorderLayout.CENTER);
        add(painel3, BorderLayout.SOUTH);
        jbCrip.addActionListener(this);
        jbDecrip.addActionListener(this);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent evento) {
    
        if (evento.getSource() == jbCrip) {
            String blocoExemplo = jtMsg.getText();
            String[] blocoAux = jtMsg.getText().split(" ");
            int[] blocoInt = new int[blocoAux.length];
            int[] blocoMult = new int[blocoAux.length];
            ArrayList<Integer> listaBloco = new ArrayList<Integer>();
            int intAux;
            String codificacao = "";
            Random random = new Random();
            int chave = random.nextInt(9) + 1;

            for (int i = 0; i < blocoInt.length; i++) {

                blocoInt[i] = Integer.parseInt(blocoAux[i]);

            }

            for (int i = 0; i < blocoInt.length; i++) {
                intAux = blocoInt[i];
                blocoMult[i] = Integer.parseInt(CalculoFermat(eCodificacao, p, intAux, mCodificacao, intAux, eCodificacao));
            }

            listaBloco = MatrizMult.multMatriz(blocoMult, chave);

            for (int i = 0; i < listaBloco.size(); i++) {

                codificacao += listaBloco.get(i) + " ";

            }

            jtMsgFinal.setText("" + codificacao);
            jtChave.setText("" + chave);

        }

        if (evento.getSource() == jbDecrip) {
            String blocoExemplo = jtMsg.getText();
            String[] blocoAux = jtMsg.getText().split(" ");
            int[] blocoInt = new int[blocoAux.length];
            int[] blocoMult = new int[blocoAux.length];
            ArrayList<Integer> listaBloco = new ArrayList<Integer>();
            int intAux;
            String decodificacao = "";
            String decodAux;
            int chave = Integer.parseInt(jtChave.getText());

            for (int i = 0; i < blocoInt.length; i++) {

                blocoInt[i] = Integer.parseInt(blocoAux[i]);

            }

            listaBloco = MatrizMult.invMatriz(blocoInt, chave);

            for (int i = 0; i < blocoInt.length; i++) {
                blocoMult[i] = listaBloco.get(i);
            }

            int calDiofantinaD = Diofantina(eCodificacao, nCodificacao);

            for (int i = 0; i < blocoInt.length; i++) {
                intAux = blocoMult[i];
                decodAux = CalculoFermat(calDiofantinaD, p, intAux, mCodificacao, intAux, calDiofantinaD);
                if (!(Integer.parseInt(decodAux) == 0)) {
                    decodificacao += decodAux + " ";
                }
            }

            jtMsgFinal.setText("" + decodificacao);
        }
    }


    public static void main(String[] args) throws Exception {
        new App();
    }
}
