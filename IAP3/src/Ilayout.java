import java.util.List;

/**
 * Interface que define um layout ou estado em um problema de busca.
 */
public interface Ilayout {
    /**
     * Obtem a lista de estados filhos a partir do estado atual.
     *
     * @return Uma lista de estados filhos gerados a partir do estado atual.
     */
    List<Ilayout> children();
    /**
     * Verifica se o estado atual e igual ao estado especificado.
     *
     * @param l O estado a ser comparado com o estado atual.
     * @return true se os estados forem iguais, caso contrario, false.
     */
    boolean isGoal(Ilayout l);

    /**
     * Obtem o custo para mover do estado de entrada para o estado atual.
     *
     * @return O custo para mover do estado de entrada para o estado atual.
     */
    double getG();

    /**
     * Obtem o valor heurístico para o estado atual em relacao ao objetivo.
     *
     * @return O valor heurístico para o estado atual.
     */
    double getH();
}
