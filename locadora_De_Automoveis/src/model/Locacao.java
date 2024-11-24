package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Classe que representa uma locação de automóvel.
 */
public class Locacao implements Serializable{
	private static final long serialVersionUID = 1L;
	private Cliente cliente;
    private Automovel automovel;
    private int diasLocacao;
    private LocalDate dataInicio;
    private boolean devolvido;
    
    /**
     * Construtor para inicializar os atributos de uma locação.
     *
     * @param cliente    O cliente que realiza a locação.
     * @param automovel  O automóvel alugado.
     * @param diasLocacao Número de dias de locação.
     * @param dataInicio A data de início da locação.
     */
    public Locacao(Cliente cliente, Automovel automovel, int diasLocacao, LocalDate dataInicio) {
        this.cliente = cliente;
        this.automovel = automovel;
        this.diasLocacao = diasLocacao;
        this.dataInicio = dataInicio;
        this.devolvido = false;
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    public Automovel getAutomovel() {
        return automovel;
    }

    public int getDiasLocacao() {
        return diasLocacao;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public boolean isDevolvido() {
        return devolvido;
    }
    
    /**
     * Calcula o valor total da locação considerando os dias extras, se houver.
     *
     * @param dataDevolucao A data real de devolução do automóvel.
     * @param anoAtual      O ano atual para calcular o valor da diária.
     * @return O valor total da locação, incluindo acréscimos por atraso.
     */
    public double calcularValorTotal(LocalDate dataDevolucao, int anoAtual) {
        if (dataDevolucao == null || dataDevolucao.isBefore(dataInicio)) {
            throw new IllegalArgumentException("A data de devolução não pode ser anterior à data de início.");
        }

        long diasTotais = ChronoUnit.DAYS.between(dataInicio, dataDevolucao);
        long diasExtras = Math.max(0, diasTotais - diasLocacao);

        double valorDiaria = automovel.calcularValorDiaria(anoAtual);
        return (diasLocacao * valorDiaria) + (diasExtras * valorDiaria * 1.1);
    }

    /**
     * Marca o automóvel como devolvido.
     */
    public void devolver() {
        this.devolvido = true;
    }

    @Override
    public String toString() {
        return String.format(
            "Locação - Cliente: %s, Automóvel: %s, Dias Contratados: %d, Data Início: %s, Devolvido: %s",
            cliente.getNome(),
            automovel.getPlaca(),
            diasLocacao,
            dataInicio,
            devolvido ? "Sim" : "Não"
        );
    }

}
