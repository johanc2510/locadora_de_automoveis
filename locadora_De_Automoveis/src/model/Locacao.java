package model;

import java.io.Serializable;

/**
 * Classe que representa uma locação.
 */
public class Locacao implements Serializable {
    private static final long serialVersionUID = 1L;
    private Cliente cliente;
    private Automovel automovel;
    private int dias;

    /**
     * Construtor para inicializar a locação.
     *
     * @param cliente   Cliente que alugou o automóvel.
     * @param automovel Automóvel alugado.
     * @param dias      Número de dias da locação.
     */
    public Locacao(Cliente cliente, Automovel automovel, int dias) {
        this.cliente = cliente;
        this.automovel = automovel;
        this.dias = dias;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Automovel getAutomovel() {
        return automovel;
    }

    /**
     * Retorna o número de dias da locação.
     *
     * @return Número de dias.
     */
    public int getDias() {
        return dias;
    }

    @Override
    public String toString() {
        return String.format("Cliente: %s, Automóvel: %s, Dias: %d", 
            cliente.getNome(), automovel.getPlaca(), dias);
    }
}
