import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um estado Triple no contexto de um problema de busca.
 * Implementa a interface Ilayout e e clonável.
 */
public class Triple implements Ilayout, Cloneable {

    /**
     * O valor do estado Triple.
     */
    private int triple;

    /**
     * O valor objetivo que o estado Triple esta a tentar alcancar.
     */
    private int goal;

    /**
     * O custo acumulado para alcancar este estado (g).
     */
    private int g;

    /**
     * Construtor que cria um novo estado Triple com o valor especificado.
     *
     * @param triple O valor do estado Triple.
     */
    public Triple(int triple) {
        this.triple = triple;
        this.g = 0;
        this.goal = triple*3;
    }

    /**
     * Construtor que cria um novo estado Triple com o valor e o objetivo especificados.
     *
     * @param triple O valor do estado Triple.
     * @param goal O valor objetivo que o estado Triple esta a tentar alcancar.
     */
    public Triple(int triple, int goal) {
        this.triple = triple;
        this.g = 0;
        this.goal = goal; // Set the goal to the provided value
    }



    /**
     * Retorna a representacao em string do estado Triple.
     *
     * @return A representacao em string do valor do estado Triple.
     */
    public String toString(){
        return String.valueOf(this.triple);
    }

    /**
     * Verifica se este estado Triple e igual a outro objeto.
     *
     * @param o O objeto a ser comparado com este estado Triple.
     * @return true se os estados forem iguais, caso contrario, false.
     */
    public boolean equals(Object o) {
        if(o instanceof Triple)
            return this.triple == ((Triple) o).triple;
        return false;
    }

    /**
     * Calcula o codigo de hash deste estado Triple com base no valor.
     *
     * @return O codigo de hash do valor do estado Triple.
     */
    public int hashCode() {
        return Integer.hashCode(this.triple);
    }

    /**
     * Cria uma copia deste estado Triple.
     *
     * @return Uma nova instancia de Triple com o mesmo valor.
     */
    public Triple newBoard(){
        Triple copy = new Triple(this.triple);
        return copy;
    }

    /**
     * Retorna uma lista de estados filhos possiveis a partir do estado atual Triple.
     *
     * @return Uma lista de estados filhos gerados a partir deste estado Triple.
     */
    @Override
    public List<Ilayout> children() {
        List<Ilayout> allChild = new ArrayList<Ilayout>();

        //Add
        Triple childAdd = new Triple(triple +1, this.goal);
        childAdd.g += 1;
        allChild.add(childAdd);
        //Sub
        Triple childSub = new Triple(triple -1, this.goal);
        childSub.g += 2;
        allChild.add(childSub);
        //Mul
        Triple childMul = new Triple(triple *2, this.goal);
        childMul.g += 3;
        allChild.add(childMul);


        return allChild;
    }


    /**
     * Verifica se o estado atual e igual ao estado objetivo especificado.
     *
     * @param l O estado objetivo a ser comparado com o estado atual.
     * @return true se o estado atual for igual ao estado objetivo, caso contrario, false.
     */
    @Override
    public boolean isGoal(Ilayout l) {
        if(l instanceof Triple){
            if(this.triple != ((Triple) l).triple)
                return false;
            return true;
        }
        else
            return false;
    }


    /**
     * Obtem o valor do custo acumulado (g) para alcançar este estado Triple.
     *
     * @return O valor do custo acumulado (g) para este estado Triple.
     */

    @Override
    public double getG() {
        return g;
    }

    /**
     * Calcula o valor heuristico (h) deste estado Triple em relacao ao objetivo.
     *
     * @return O valor heuristico (h) deste estado Triple.
     */
    @Override
    public double getH() {
        int n = this.triple;
        double difference = 0;
        double difMin = 0;
        /*
        if(currentState > 0){
            difference =  Math.min(Math.floor(Math.abs(this.goal/2 - currentState) + 3), Math.abs(this.goal - currentState)) ;
        }
        else {
            difMin = Math.min(Math.floor((Math.abs(this.goal/2 - currentState) *2) + 3) , Math.floor(Math.abs(this.goal/4 - currentState) + 6));
            difference = Math.min(difMin,Math.abs(this.goal - currentState) * 2);
        }*/

        if(n % 2 == 0)
            difference = Math.abs(this.goal - n);
        else
            difference = Math.abs(this.goal % n);

        return difference;
    }

}