package model;

/**
 * Classe que representa um automóvel grande.
 */
public class AutomovelGrande extends Automovel{
	private static final long serialVersionUID = 1L;

	public AutomovelGrande(String placa, int anoModelo, double valorBaseDiaria) {
		super(placa, anoModelo, valorBaseDiaria);
	}
	
	@Override
	public double calcularValorDiaria(int anoAtual) {
		int idade = anoAtual - getAnoModelo();
        double desconto = Math.min(idade * 0.02, 0.08);
        return getValorBaseDiaria() * (1 - desconto);
	}
}
