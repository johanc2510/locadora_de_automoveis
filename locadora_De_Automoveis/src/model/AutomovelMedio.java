package model;

/**
 * Classe que representa um automóvel medio.
 */
public class AutomovelMedio extends Automovel{
	private static final long serialVersionUID = 1L;

	public AutomovelMedio(String placa, int anoModelo, double valorBaseDiaria) {
		super(placa, anoModelo, valorBaseDiaria);
	}

	@Override
    public double calcularValorDiaria(int anoAtual) {
        int idade = anoAtual - getAnoModelo();
        double desconto = Math.min(idade * 0.05, 0.15);
        return getValorBaseDiaria() * (1 - desconto);
    }
	
	@Override
    public double calcularDiaria(int dias) {
        double desconto = Math.min(0.05 * getAnoModelo(), 0.15); // Desconto de 5% ao ano até 15%
        double diaria = getValorBaseDiaria() * (1 - desconto);
        return diaria * dias;
    }

}
