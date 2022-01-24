package br.com.fiap.dbstudies;

import java.sql.Connection;

import java.sql.DriverManager;


//Com essa classe, não vamos precisar escrever código repetido, utilizando assim os conceitos de orientação a objetos
/*
 *Observe que essa classe possui somente um método estático que cria e retorna uma conexão com o banco. O método
 é estático para não precisar de uma instância para ser invocada, bastando referenciá-la através do nome da classe:
EmpresaDBManager.obterConexao();
 * */



public class EmpresaDBManager {

	public static Connection obterConexao() {
		Connection conexao = null; 
		try {
			//Registra o Driver
			Class.forName("oracle.jdbc.driver.OracleDriver");

			//Abre uma conexão
			conexao = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.0.171:1521:XE", "system", "950310");

			System.out.println("Conectado!");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return conexao;
	}
}
