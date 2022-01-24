package br.com.fiap.dbstudies;

import java.sql.Connection;

import java.sql.DriverManager;


//Com essa classe, n�o vamos precisar escrever c�digo repetido, utilizando assim os conceitos de orienta��o a objetos
/*
 *Observe que essa classe possui somente um m�todo est�tico que cria e retorna uma conex�o com o banco. O m�todo
 � est�tico para n�o precisar de uma inst�ncia para ser invocada, bastando referenci�-la atrav�s do nome da classe:
EmpresaDBManager.obterConexao();
 * */



public class EmpresaDBManager {

	public static Connection obterConexao() {
		Connection conexao = null; 
		try {
			//Registra o Driver
			Class.forName("oracle.jdbc.driver.OracleDriver");

			//Abre uma conex�o
			conexao = DriverManager.getConnection(
					"jdbc:oracle:thin:@192.168.0.171:1521:XE", "system", "950310");

			System.out.println("Conectado!");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return conexao;
	}
}
