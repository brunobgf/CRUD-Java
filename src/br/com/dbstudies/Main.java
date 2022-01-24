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

	//Abre uma conex�o
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
      //Exibe as informa��es do registro
      System.out.println(codigo + " " + nome + " " + email + " " + salario + " " + data);
  }
        
    //Fecha a conex�o
    conexao.close();
        
    //Tratamento de erro	
    } catch (SQLException e) {
      System.err.println("N�o foi poss�vel conectar no Banco de Dados");
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      System.err.println("O Driver JDBC n�o foi encontrado!");
      e.printStackTrace();
    }
  }
}


/*
:: Inclus�o:
Statement stmt = conexao.createStatement();
stmt.executeUpdate("INSERT INTO TAB_COLABORADOR(CODIGO_COLABORADOR, NOME, EMAIL, SALARIO, DATA_CONTRATACAO) VALUES (SQ_COLABORADOR.NEXTVAL, 'Leandro', 'leandro@gmail.com', 1500, TO_DATE('10/12/2009','dd/mm/yyyy'))");
	
:: Altera��o:
Statement stmt = conexao.createStatement();
stmt.executeUpdate("UPDATE TAB_COLABORADOR SET SALARIO = 5000 WHERE CODIGO_COLABORADOR = 1");

:: Exclus�o:
Statement stmt = conexao.createStatement();
stmt.executeUpdate("DELETE FROM TAB_COLABORADOR WHERE CODIGO_COLABORADOR = 1");	

:: Busca:
Statement stmt = conexao.createStatement();
ResultSet rs = stmt.executeQuery("SELECT * FROM TAB_COLABORADOR");

Observe que os comandos SQL s�o est�ticos, ou seja, os valores j� est�o definidos diretamente na String.
Quando precisamos de comandos SQL configur�veis, devemos utilizar a interface PreparedStatement.

*/


/*
MORE EXAMPLES: 

PreparedStatement stmt = conexao.prepareStatement("INSERT INTO TAB_COLABORADOR(CODIGO_COLABORADOR, NOME, EMAIL, SALARIO, DATA_CONTRATACAO) VALUES (SQ_COLABORADOR.NEXTVAL, ?, ?, ?, ?)");
stmt.setString(1, "Thiago"); //Primero par�metro (Nome)
stmt.setString(2, "thiago@gmail.com");//Segundo par�metro (Email)
stmt.setDouble(3, 5000); //Terceiro par�metro (Sal�rio)
//Instancia um objeto do tipo java.sql.Date com a data atual
java.sql.Date data = new java.sql.Date(new java.util.Date().getTime());
stmt.setDate(4,data);//Quarto par�metro (data contrata��o)
			
stmt.executeUpdate();

:: Altera��o:
PreparedStatement stmt = conexao.prepareStatement("UPDATE TAB_COLABORADOR SET SALARIO = ? WHERE CODIGO_COLABORADOR = ?");
stmt.setDouble(1, 5000);
stmt.setInt(2, 100);
stmt.executeUpdate(); 

:: Exclus�o:
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
			
//Define o tipo do par�metro de sa�da (primeiro ?)
cs.registerOutParameter(1, java.sql.Types.INTEGER);
			
//Define o valor do par�metro de entrada (segundo ?)
cs.setDouble(2, 1500);
			
//Executa a procedure
cs.executeUpdate();
			
//Recupera o valor do par�metro de sa�da
int total = cs.getInt(1);
System.out.println("Total de colaboradores com sal�rio maior que 1500: " + total);
 * 
 * */
