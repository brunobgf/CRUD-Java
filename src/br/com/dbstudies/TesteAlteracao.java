package br.com.fiap.dbstudies;



public class TesteAlteracao {

  public static void main(String[] args) {

    ClassColaboradorDAO dao = new ClassColaboradorDAO();
    //Recupera o colaborador com código 1
    Colaborador colaborador = dao.buscaId(42);
    //Imprime os valores do colaborador
    System.out.println(colaborador.getCodigo() + " "
        + colaborador.getNome() + " " + colaborador.getEmail() + " "
        + colaborador.getSalario() + " "
        + colaborador.getDataContratacao().getTime());
    //Altera os valores de alguns atributos do colaborador
    colaborador.setSalario(1500);
    colaborador.setEmail("tes@fiap.com.br");
    //Atualiza no banco de dados
    dao.atualizar(colaborador);
  }

}