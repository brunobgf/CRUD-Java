package br.com.fiap.dbstudies;

import java.util.Calendar;

public class Colaborador {
	
	private int codigo;

	private String nome;
	
	private String email;
	
	private double salario;
	
	private Calendar dataContratacao;
	
	public Colaborador(int codigo, String nome, String email, double salario, Calendar dataContratacao ) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.email = email;
		this.salario = salario;
		this.dataContratacao = dataContratacao;
	
	}
	//Construtor 
	
	public Colaborador() {
		super();
	}
	
	//Criação dos getters e setters

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public Calendar getDataContratacao() {
		return dataContratacao;
	}

	public void setDataContratacao(Calendar dataContratacao) {
		this.dataContratacao = dataContratacao;
	}
	
	//Essa classe possui grande importância por facilitar o processo de passagem de valores do banco de dados para a aplicação Java e vice-versa.

}
