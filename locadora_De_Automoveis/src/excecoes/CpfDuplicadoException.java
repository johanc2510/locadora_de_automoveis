package excecoes;

/**
 * Exceção lançada quando um CPF duplicado é detectado no cadastro de clientes.
 */
public class CpfDuplicadoException extends Exception {

    /**
     * Construtor que aceita uma mensagem de erro.
     * @param mensagem Descrição do erro.
     */
    public CpfDuplicadoException(String mensagem) {
        super(mensagem);
    }
}