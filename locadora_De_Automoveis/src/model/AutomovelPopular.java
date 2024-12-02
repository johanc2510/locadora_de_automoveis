package model;

/**
 * Classe que representa um automóvel popular.
 */
public class AutomovelPopular extends Automovel{
	private static final long serialVersionUID = 1L;

	public AutomovelPopular(String placa, int anoModelo, double valorBaseDiaria) {
		super(placa, anoModelo, valorBaseDiaria);
	}

    @Override
    public double calcularValorDiaria(int anoAtual) {
        int idade = anoAtual - getAnoModelo();
        double desconto = Math.min(idade * 0.07, 0.21);
        return getValorBaseDiaria() * (1 - desconto);
    }
    @Override
    public double calcularDiaria(int dias) {
        double desconto = Math.min(0.07 * getAnoModelo(), 0.21); // Desconto de 7% ao ano até 21%
        double diaria = getValorBaseDiaria() * (1 - desconto);
        return diaria * dias;
    }
}
