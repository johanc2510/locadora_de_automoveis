package excecoes;

/**
 * Exceção lançada quando um automóvel solicitado para locação está indisponível.
 */
public class AutomovelIndisponivelException extends Exception {

    /**
     * Construtor que aceita uma mensagem de erro.
     * @param mensagem Descrição do erro.
     */
    public AutomovelIndisponivelException(String mensagem) {
        super(mensagem);
    }
}