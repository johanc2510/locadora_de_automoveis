package excecoes;

/**
 * Exceção lançada quando uma placa duplicada é detectada no cadastro de automóveis.
 */
public class PlacaDuplicadaException extends Exception {

    /**
     * Construtor que aceita uma mensagem de erro.
     * @param mensagem Descrição do erro.
     */
    public PlacaDuplicadaException(String mensagem) {
        super(mensagem);
    }
}