package br.com.fiap.dbstudies;


import java.util.List;

public interface ColaboradorDAO {

	public void cadastrar(Colaborador colaborador);

	public List<Colaborador> listar();
	
	public void atualizar(Colaborador colaborador);

	public void remover(int codigo);
	
	public Colaborador buscaId(int codigoBusca);
}

