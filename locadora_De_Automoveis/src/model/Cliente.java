package model;

import java.io.Serializable;

/**
 * Classe que representa um cliente da locadora de automóveis.
 */
public class Cliente implements Serializable{
	private static final long serialVersionUID = 1L;
	private String nome;
	private String cpf;
	
	/**
     * Construtor para inicializar os atributos principais do cliente.
     *
     * @param nome          Nome do Cliente (único).
     * @param anoModelo      CPF do cliente(único).
     */
	public Cliente(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	@Override
    public String toString() {
        return String.format("Nome: %s, CPF: %s", nome, cpf);
    }
}
