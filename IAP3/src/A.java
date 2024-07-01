import java.util.*;

/**
 * Classe que representa um algoritmo de busca que utiliza uma fila de estados abertos
 * e um mapa de estados fechados para encontrar um estado objetivo.
 */
public class A {
    /**
     * Fila de estados abertos a serem explorados.
     */
    protected Queue<State> abertos;

    /**
     * Mapa que mantem o mapeamento entre layouts e estados fechados.
     */
    private Map<Ilayout, State> fechados;

    /**
     * O estado atual sendo considerado pelo algoritmo.
     */
    private State actual;

    /**
     * O layout objetivo que o algoritmo esta a tentar alcançar.
     */
    private Ilayout objective;

    /**
     * Classe interna que representa um estado no contexto do algoritmo.
     */

    private int expandedNodes;
    private int generatedNodes;
    private int solutionLength;

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
         * Classe interna que representa um estado no contexto do algoritmo de busca.
         */
        public State(Ilayout l, State n) {
            /**
             * Construtor que cria um novo estado com o layout especificado e o estado pai.
             *
             * @param l O layout associado a este estado.
             * @param n O estado pai deste estado na arvore de busca. Pode ser nulo se for o estado inicial.
             */
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
        public String toString() { return layout.toString(); }
        /**
         * Obtem o valor g deste estado, que e o custo acumulado para alcancar este estado.
         *
         * @return O valor g deste estado.
         */
        public double getG() {return g;}
        /**
         * Obtem o valor f deste estado, que e o valor heuristico deste estado.
         *
         * @return O valor f deste estado.
         */
        public double getF(){
            return f;
        }

        /**
         * Calcula o codigo de hash deste estado com base no layout.
         *
         * @return O codigo de hash do layout deste estado.
         */
        public int hashCode() {
            return layout.hashCode();
        }

        /**
         * Verifica se este estado e igual a outro objeto.
         *
         * @param o O objeto a ser comparado com este estado.
         * @return true se os estados forem iguais, caso contrario, false.
         */
        public boolean equals (Object o) {
            if (o==null) return false;
            if (this.getClass() != o.getClass()) return false;
            State n = (State) o;
            return this.layout.equals(n.layout);
        }
    }

    /**
     * Obtem uma lista de estados sucessores do estado especificado.
     *
     * @param n O estado para pretendo obter os sucessores.
     * @return Uma lista de estados sucessores.
     */
    private List<State> sucessores(State n) {
        List<State> sucs = new ArrayList<>();
        List<Ilayout> children = n.layout.children();
        for (Ilayout e : children) {
            if (n.father == null || !e.equals(n.father.layout)) {
                State nn = new State(e, n);
                sucs.add(nn);
            }
        }
        return sucs;
    }

    private int calculateSolutionLength(State finalState) {
        int length = 0;
        State temp = finalState;

        while (temp != null) {
            length++;
            temp = temp.father;
        }

        return length;
    }

    /**
     * Resolve o problema de busca a partir de um estado inicial para um estado objetivo.
     *
     * @param s O estado inicial a partir do qual a busca deve comecar.
     * @param goal O estado objetivo que o algoritmo esta a tentar alcancar.
     * @return Um iterador que representa o caminho da solucao, ou null se nenhuma solucao for encontrada.
     */
    final public Iterator<State> solve(Ilayout s, Ilayout goal) {
        expandedNodes = 0;
        generatedNodes = 0;
        solutionLength = 0;
        objective = goal;
        abertos = new PriorityQueue<>(10, Comparator.comparingDouble(State::getF));
        fechados = new HashMap<>();
        Map<Ilayout, State> abertosMap = new HashMap<>(); // Mapeia layouts para estados na fila aberta

        State startState = new State(s, null);
        abertos.add(startState);
        abertosMap.put(s, startState);

        while (!abertos.isEmpty()) {
            actual = abertos.poll();
            abertosMap.remove(actual.layout);

            // Incrementa o número de nós expandidos
            expandedNodes++;

            if (actual.layout.isGoal(objective)) {
                // Calcula o comprimento da solução
                solutionLength = calculateSolutionLength(actual);

                double penetrance = (double) solutionLength / expandedNodes;

                System.out.println("Nós expandidos (E): " + expandedNodes);
                System.out.println("Nós gerados (G): " + generatedNodes);
                System.out.println("Comprimento da solução (L): " + solutionLength);
                System.out.println("Penetrância (P): " + penetrance);

                return buildPath(actual);
            }

            fechados.put(actual.layout, actual);
            List<State> sucs = sucessores(actual);
            for (State sucessor : sucs) {
                generatedNodes++;
                State openState = abertosMap.get(sucessor.layout);
                if (openState == null && !fechados.containsKey(sucessor.layout)) {
                    abertos.add(sucessor);
                    abertosMap.put(sucessor.layout, sucessor);
                } else if (openState != null && sucessor.getF() < openState.getF()) {
                    abertos.remove(openState);
                    abertos.add(sucessor);
                    abertosMap.put(sucessor.layout, sucessor);
                } else if (fechados.containsKey(sucessor.layout) && sucessor.getF() < fechados.get(sucessor.layout).getF()) {
                    fechados.remove(sucessor.layout);
                    abertos.add(sucessor);
                    abertosMap.put(sucessor.layout, sucessor);
                }
            }
        }
        // Imprime os valores no caso de nenhuma solução ser encontrada
        System.out.println("Nós expandidos (E): " + expandedNodes);
        System.out.println("Nós gerados (G): " + generatedNodes);
        System.out.println("Comprimento da solução (L): " + solutionLength);
        // Calcule e imprima a penetrância (P) conforme necessário
        return null;
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
