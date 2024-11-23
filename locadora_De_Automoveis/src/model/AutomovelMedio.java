package model;

/**
 * Classe que representa um automóvel medio.
 */
public class AutomovelMedio extends Automovel{

	public AutomovelMedio(String placa, int anoModelo, double valorBaseDiaria) {
		super(placa, anoModelo, valorBaseDiaria);
	}

	@Override
    public double calcularValorDiaria(int anoAtual) {
        int idade = anoAtual - getAnoModelo();
        double desconto = Math.min(idade * 0.05, 0.15);
        return getValorBaseDiaria() * (1 - desconto);
    }
}
