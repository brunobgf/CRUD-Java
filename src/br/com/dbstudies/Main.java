package br.com.fiap.dbstudies;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) {
		try {
	//Registra o Driver
	Class.forName("oracle.jdbc.driver.OracleDriver");

	//Abre uma conexão
	Connection conexao = DriverManager.getConnection(
			"jdbc:oracle:thin:@192.168.0.171:1521:XE", "system", "950310");
			
	System.out.println("Conectado!");
			
  PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM TABELA_COLABORADOR WHERE NOME = ?");
    stmt.setString(1, "Bruno");
    ResultSet result = stmt.executeQuery();
        
    //Percorre todos os registros encontrados
    while (result.next()){
      //Recupera os valores de cada coluna
      int codigo = result.getInt("CODIGO_COLABORADOR");
      String nome = result.getString("NOME");
      String email = result.getString("EMAIL");
      double salario = result.getDouble("SALARIO");
      java.sql.Date data = result.getDate("DATA_CONTRATACAO");
      //Exibe as informações do registro
      System.out.println(codigo + " " + nome + " " + email + " " + salario + " " + data);
  }
        
    //Fecha a conexão
    conexao.close();
        
    //Tratamento de erro	
    } catch (SQLException e) {
      System.err.println("Não foi possível conectar no Banco de Dados");
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      System.err.println("O Driver JDBC não foi encontrado!");
      e.printStackTrace();
    }
  }
}


/*
:: Inclusão:
Statement stmt = conexao.createStatement();
stmt.executeUpdate("INSERT INTO TAB_COLABORADOR(CODIGO_COLABORADOR, NOME, EMAIL, SALARIO, DATA_CONTRATACAO) VALUES (SQ_COLABORADOR.NEXTVAL, 'Leandro', 'leandro@gmail.com', 1500, TO_DATE('10/12/2009','dd/mm/yyyy'))");
	
:: Alteração:
Statement stmt = conexao.createStatement();
stmt.executeUpdate("UPDATE TAB_COLABORADOR SET SALARIO = 5000 WHERE CODIGO_COLABORADOR = 1");

:: Exclusão:
Statement stmt = conexao.createStatement();
stmt.executeUpdate("DELETE FROM TAB_COLABORADOR WHERE CODIGO_COLABORADOR = 1");	

:: Busca:
Statement stmt = conexao.createStatement();
ResultSet rs = stmt.executeQuery("SELECT * FROM TAB_COLABORADOR");

Observe que os comandos SQL são estáticos, ou seja, os valores já estão definidos diretamente na String.
Quando precisamos de comandos SQL configuráveis, devemos utilizar a interface PreparedStatement.

*/


/*
MORE EXAMPLES: 

PreparedStatement stmt = conexao.prepareStatement("INSERT INTO TAB_COLABORADOR(CODIGO_COLABORADOR, NOME, EMAIL, SALARIO, DATA_CONTRATACAO) VALUES (SQ_COLABORADOR.NEXTVAL, ?, ?, ?, ?)");
stmt.setString(1, "Thiago"); //Primero parâmetro (Nome)
stmt.setString(2, "thiago@gmail.com");//Segundo parâmetro (Email)
stmt.setDouble(3, 5000); //Terceiro parâmetro (Salário)
//Instancia um objeto do tipo java.sql.Date com a data atual
java.sql.Date data = new java.sql.Date(new java.util.Date().getTime());
stmt.setDate(4,data);//Quarto parâmetro (data contratação)
			
stmt.executeUpdate();

:: Alteração:
PreparedStatement stmt = conexao.prepareStatement("UPDATE TAB_COLABORADOR SET SALARIO = ? WHERE CODIGO_COLABORADOR = ?");
stmt.setDouble(1, 5000);
stmt.setInt(2, 100);
stmt.executeUpdate(); 

:: Exclusão:
PreparedStatement stmt = conexao.prepareStatement("DELETE FROM TAB_COLABORADOR WHERE CODIGO_COLABORADOR = ?");
stmt.setInt(1, 1);
stmt.executeUpdate(); 

:: Busca:
PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM TAB_COLABORADOR WHERE NOME = ?");
stmt.setString(1, "Thiago");
ResultSet result = stmt.executeQuery();
*/

/*
 * CALLING FUNCTIONS
 * //Cria o CallableStatement
CallableStatement cs = conexao.prepareCall("{call SP_Contar_Colaboradores(?,?)}");
			
//Define o tipo do parâmetro de saída (primeiro ?)
cs.registerOutParameter(1, java.sql.Types.INTEGER);
			
//Define o valor do parâmetro de entrada (segundo ?)
cs.setDouble(2, 1500);
			
//Executa a procedure
cs.executeUpdate();
			
//Recupera o valor do parâmetro de saída
int total = cs.getInt(1);
System.out.println("Total de colaboradores com salário maior que 1500: " + total);
 * 
 * */
