package model;

/**
 * Classe que representa um automóvel popular.
 */
public class AutomovelPopular extends Automovel{

	public AutomovelPopular(String placa, int anoModelo, double valorBaseDiaria) {
		super(placa, anoModelo, valorBaseDiaria);
	}

    @Override
    public double calcularValorDiaria(int anoAtual) {
        int idade = anoAtual - getAnoModelo();
        double desconto = Math.min(idade * 0.07, 0.21);
        return getValorBaseDiaria() * (1 - desconto);
    }
}
