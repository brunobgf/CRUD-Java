package br.com.fiap.dbstudies;

public class TesteBuscaId {
	public static void main(String[] args) {
		Colaborador item;
		//criando novo objeto colaborador 
		ClassColaboradorDAO dao = new ClassColaboradorDAO();
		//Remove o colaborador com código 1
		item = dao.buscaId(42);
		
		System.out.println(item.getCodigo() + " " + item.getNome() + " " + item.getEmail() + " " + item.getSalario() + " " + item.getDataContratacao().getTime());
	}

}
