package model;

import java.io.Serializable;

/**
 * Classe base que representa um automóvel.
 * Esta classe é abstrata e será herdada pelas classes específicas de tipos de automóveis.
 */
public abstract class Automovel implements Serializable {
    private static final long serialVersionUID = 1L;
    private String placa;
    private int anoModelo;
    private double valorBaseDiaria;
    private boolean alugado; // Atributo para indicar o estado de aluguel

    /**
     * Construtor para inicializar os atributos principais do automóvel.
     *
     * @param placa          Placa do automóvel (única).
     * @param anoModelo      Ano do modelo do automóvel.
     * @param valorBaseDiaria Valor base da diária para locação.
     */
    public Automovel(String placa, int anoModelo, double valorBaseDiaria) {
        this.placa = placa;
        this.anoModelo = anoModelo;
        this.valorBaseDiaria = valorBaseDiaria;
        this.alugado = false; // Inicialmente, o automóvel não está alugado
    }

    public String getPlaca() {
        return placa;
    }

    public int getAnoModelo() {
        return anoModelo;
    }

    public double getValorBaseDiaria() {
        return valorBaseDiaria;
    }

    /**
     * Verifica se o automóvel está alugado.
     *
     * @return true se estiver alugado, false caso contrário.
     */
    public boolean isAlugado() {
        return alugado;
    }

    /**
     * Define o estado de aluguel do automóvel.
     *
     * @param alugado true para marcar como alugado, false para desmarcar.
     */
    public void setAlugado(boolean alugado) {
        this.alugado = alugado;
    }

    /**
     * Método abstrato que calcula o valor da diária considerando o tipo de automóvel e a idade do modelo.
     *
     * @param anoAtual O ano atual para cálculo da idade do automóvel.
     * @return O valor da diária ajustado.
     */
    public abstract double calcularValorDiaria(int anoAtual);
    
    /**
     * Calcula o valor total da locação com base no número de dias.
     *
     * @param dias Número de dias da locação.
     * @return Valor total da locação.
     */
    public abstract double calcularDiaria(int dias);


    @Override
    public String toString() {
        return String.format("Placa: %s, Ano Modelo: %d, Valor Base: R$%.2f, Alugado: %s", 
            placa, anoModelo, valorBaseDiaria, alugado ? "Sim" : "Não");
    }
}
