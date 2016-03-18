package br.com.easymedicows;

public class Especialidade {
	private int id;
	private String nome;
	
	public Especialidade(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public int getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}

}
