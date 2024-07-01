import java.util.* ;

/**
 * Classe que contem o metodo principal (main) para a execução do programa.
 */
public class Main {

    /**
     * Metodo principal que inicia a execução do programa.
     *
     * @param args Argumentos da linha de comando (nao utilizados neste programa).
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        IDA s = new IDA();
        int value = sc.nextInt();

        // Resolve o problema com base no valor inserido
        Iterator<IDA.State> it = s.solve(new Triple(value), new Triple(value*3));

        while(it.hasNext()) {
            IDA.State i = it.next();
            System.out.println(i);
            if (!it.hasNext()) {
                System.out.println("\n" + (int) i.getG());
                break;
            }

        }
        sc.close();
    }
}