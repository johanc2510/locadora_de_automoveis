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
	
	@Override
    public double calcularDiaria(int dias) {
        double desconto = Math.min(0.02 * getAnoModelo(), 0.08); // Desconto de 2% ao ano até 8%
        double diaria = getValorBaseDiaria() * (1 - desconto);
        return diaria * dias;
    }
}
