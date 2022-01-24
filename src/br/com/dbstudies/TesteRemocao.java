package br.com.fiap.dbstudies;


public class TesteRemocao {

	public static void main(String[] args) {
		//criando novo objeto colaborador 
		ColaboradorDAO dao = new ClassColaboradorDAO();
		//Remove o colaborador com código 1
		dao.remover(41);
	}
	
}
