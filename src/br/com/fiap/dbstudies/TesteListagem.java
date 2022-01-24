package br.com.fiap.dbstudies;

import java.util.List;

public class TesteListagem {

	public static void main(String[] args) {
		
		//instanciando a classe de colaborador dao

		ColaboradorDAO dao = new ClassColaboradorDAO();
		
		//utilizando o método listar 
		
		List<Colaborador> lista = dao.listar();
		//For each para percorrer a lista
		for (Colaborador item : lista) {
			System.out.println(item.getCodigo() + " " + item.getNome() + " " + item.getEmail() + " " + item.getSalario() + " " + item.getDataContratacao().getTime());
		}
	}
	
}