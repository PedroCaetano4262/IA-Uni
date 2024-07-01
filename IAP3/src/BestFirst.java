import java.util.*;

/**
 * Classe que implementa o algoritmo de busca Best-First com fila de prioridade.
 */
public class BestFirst {
    /**
     * Fila de prioridade para os estados abertos a serem explorados.
     */
    protected Queue<State> abertos;

    /**
     * Mapa de estados fechados para rastrear os estados ja explorados.
     */
    private Map<Ilayout, State> fechados;

    /**
     * O estado atual sendo considerado pelo algoritmo.
     */
    private State actual;

    /**
     * O layout objetivo que o algoritmo esta a tentar alcancar.
     */
    private Ilayout objective;
    /**
     * Classe interna que representa um estado no contexto do algoritmo Best-First.
     */
    private int expandedNodes = 0;
    private int generatedNodes = 0;
    private int solutionLength = 0;
    private double penetrance = 0.0;
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
         * O custo acumulado para alcançar este estado (g).
         */
        private double g;

        /**
         * Construtor que cria um novo estado com o layout especificado e o estado pai.
         *
         * @param l O layout associado ao estado.
         * @param n O estado pai deste estado na arvore de busca.
         */
        public State(Ilayout l, State n) {
            layout = l;
            father = n;
            if (father != null)
                g = father.g + l.getG();
            else
                g = 0.0;
        }

        /**
         * Obtem a representacao em string deste estado.
         *
         * @return A representação em string do layout deste estado.
         */
        public String toString() { return layout.toString(); }

        /**
         * Obtem o valor g deste estado, que e o custo acumulado para alcancar este estado.
         *
         * @return O valor g deste estado.
         */
        public double getG() {return g;}

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
     * @param n O estado para se deseja obter os sucessores.
     * @return Uma lista de estados sucessores.
     */
    final private List<State> sucessores(State n) {
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

    /**
     * Resolve o problema de busca utilizando o algoritmo Best-First com fila de prioridade.
     *
     * @param s O estado inicial a partir do qual a busca deve comecar.
     * @param goal O estado objetivo que o algoritmo esta a tentar alcançar.
     * @return Um iterador que representa o caminho da solucao, ou null se nenhuma solucao for encontrada.
     */
    final public Iterator<State> solve(Ilayout s, Ilayout goal) {
        expandedNodes = 0;
        generatedNodes = 0;
        solutionLength = 0;
        penetrance = 0.0;
        objective = goal;
        abertos = new PriorityQueue<>(10, (s1, s2) -> (int) Math.signum(s1.getG() - s2.getG()));
        fechados = new HashMap<>();
        abertos.add(new State(s, null));
        List<State> sucs;
        while(actual != objective){
            actual = abertos.poll();
            expandedNodes++;
            if(actual.layout.isGoal(objective)){
                List<State> sequence = new ArrayList<State>();
                State temp = actual;
                while(temp.father != null){
                    sequence.add(0,temp);
                    temp = temp.father;
                }
                sequence.add(0,temp);

                solutionLength = sequence.size();
                penetrance = (double) solutionLength / generatedNodes;

                System.out.println("Nós expandidos (E): " + expandedNodes);
                System.out.println("Nós gerados (G): " + generatedNodes);
                System.out.println("Comprimento da solução (L): " + solutionLength);
                System.out.println("Penetrância (P): " + penetrance);
                return sequence.iterator();
            }
            else {
                sucs = sucessores(actual);
                fechados.put(actual.layout,actual);
                for(State sucessor : sucs){
                    generatedNodes++;
                    if(!fechados.containsKey(sucessor.layout)){
                        abertos.add(sucessor);
                    }
                }
            }
        }
        return null;
    }
}
