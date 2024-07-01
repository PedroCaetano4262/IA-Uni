import java.util.*;

/**
 * Classe que implementa o algoritmo IDA* (Iterative Deepening A*), uma busca de custo uniforme iterativa.
 */
public class IDA {
    /**
     * O estado atual sendo considerado pelo algoritmo.
     */
    private State actual;

    /**
     * O layout objetivo que o algoritmo esta a tentar alcancar.
     */
    private Ilayout objective;

    /**
     * O limite de custo atual para a busca.
     */
    private double costLimit;

    /**
     * Pilha de estados abertos a serem explorados.
     */
    private Stack<State> abertos;


    /**
     * Classe interna que representa um estado no contexto do algoritmo IDA*.
     */

    private int expandedNodes = 0;
    private int generatedNodes = 0;
    private int solutionLength = 0;

    static class State {
        /**
         * O layout associado a este estado.
         */
        private Ilayout layout;

        /**
         * O estado pai deste estado na arvore de busca.
         */
        private State father;

        /**
         * O custo acumulado para chegar a este estado (g).
         */
        private double g;

        /**
         * O valor heuristico deste estado (f).
         */
        private double f;

        /**
         * Construtor que cria um novo estado com o layout especificado e o estado pai.
         *
         * @param l O layout associado ao estado.
         * @param n O estado pai deste estado na arvore de busca.
         */
        public State(Ilayout l, State n) {
            layout = l;
            father = n;
            if (father!=null) {
                g = father.g + l.getG();
                f = g + l.getH();
            }
            else g = 0.0;
        }

        /**
         * Obtem a representação em string deste estado.
         *
         * @return A representacao em string do layout deste estado.
         */
        public String toString() {
            return layout.toString();
        }


        /**
         * Obtem o valor g deste estado, que e o custo acumulado para alcancar este estado.
         *
         * @return O valor g deste estado.
         */
        public double getG() {
            return g;
        }

        /**
         * Obtém o valor f deste estado, que e o valor heuristico deste estado.
         *
         * @return O valor f deste estado.
         */
        public double getF(){
            return f;
        }
    }

    private int calculateSolutionLength(State actual) {
        int length = 0;
        while (actual != null) {
            length++;
            actual = actual.father;
        }
        return length;
    }
    /**
     * Resolve o problema de busca utilizando o algoritmo IDA* (Iterative Deepening A*).
     *
     * @param s O estado inicial a partir do qual a busca deve comecar.
     * @param goal O estado objetivo que o algoritmo está a tentar alcancar.
     * @return Um iterador que representa o caminho da solucao, ou null se nenhuma solucao for encontrada.
     */
    public Iterator<State> solve(Ilayout s, Ilayout goal) {
        expandedNodes = 0;
        generatedNodes = 0;
        solutionLength = 0;
        actual = new State(s, null);
        objective = goal;
        costLimit = s.getH();
        abertos = new Stack<>();
        while (true) {
            double newLimit = Double.POSITIVE_INFINITY;
            double result = dfsSearch(actual, costLimit);
            if (result == 0) {
                solutionLength = calculateSolutionLength(actual);
                double penetrance = (double) solutionLength / expandedNodes;

                // Imprime os valores no final
                System.out.println("Nós expandidos (E): " + expandedNodes);
                System.out.println("Nós gerados (G): " + generatedNodes);
                System.out.println("Comprimento da solução (L): " + solutionLength);
                System.out.println("Penetrância (P): " + penetrance);
                return buildPath(actual);
            }
            if (result == Double.POSITIVE_INFINITY) {

                return null; // Caminho nÃ£o encontrado
            }
            newLimit = result;
            costLimit = newLimit;
            abertos.clear();
        }
    }

    /**
     * Realiza uma busca em profundidade limitada a partir do estado inicial, com um limite especifico.
     *
     * @param initialNode O estado inicial a partir do qual a busca em profundidade comeca.
     * @param limit O limite para a busca em profundidade.
     * @return O menor valor f que excede o limite ou 0 se encontrar a solucao.
     */
    private double dfsSearch(State initialNode, double limit) {
        Stack<State> stack = new Stack<>();
        stack.push(initialNode);

        double min = Double.POSITIVE_INFINITY;

        while (!stack.isEmpty()) {
            State node = stack.pop();
            double f = node.getF();

            if (f > limit) {
                min = Math.min(min, f);
                continue;
            }
            expandedNodes++;

            if (node.layout.isGoal(objective)) {
                actual = node;
                return 0; // Encontrou a solução
            }

            for (Ilayout successor : node.layout.children()) {
                generatedNodes++;
                if (!abertos.contains(successor)) {
                    State childState = new State(successor, node);
                    stack.push(childState);
                }
            }
        }

        return min;
    }


    /**
     * Constroi um iterador que representa o caminho da solucao a partir do estado atual ate o estado inicial.
     *
     * @param actual O estado final a partir do qual o caminho da solucao deve ser construido.
     * @return Um iterador que representa o caminho da solucao.
     */
    private Iterator<State> buildPath(State actual) {
        LinkedList<State> sequence = new LinkedList<>();
        while (actual != null) {
            sequence.addFirst(actual);
            actual = actual.father;
        }
        return sequence.iterator();
    }
}