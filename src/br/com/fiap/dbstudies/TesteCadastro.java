package br.com.fiap.dbstudies;

import java.util.Calendar;




public class TesteCadastro {

	public static void main(String[] args) {
		//Instancia o DAO
		ColaboradorDAO dao = new ClassColaboradorDAO();

		//Instancia o Colaborador
		Colaborador colaborador = new Colaborador();
		colaborador.setNome("Joana");
		colaborador.setEmail("joana@fiap.com.br");
		colaborador.setSalario(7000);
		colaborador.setDataContratacao(Calendar.getInstance());

		//Cadastra no banco de dados
		dao.cadastrar(colaborador);

		System.out.println("Cadastrado!");
	}

}