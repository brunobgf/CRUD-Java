package br.com.fiap.dbstudies;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ClassColaboradorDAO implements ColaboradorDAO {
	
	/*
	 * Agora estamos prontos para desenvolver a classe Java responsável pela manipulação da tabela
	TABELA_COLABORADOR. A classe possui a nomenclatura ColaboradorDAO, pois segue um padrão de projeto chamado
	de DAO (Data Access Object), que é responsável pelo código de acesso aos dados, centralizando assim a implemen-
	tação dessa responsabilidade no projeto.
	 */


		private Connection conexao;

		public void cadastrar(Colaborador colaborador) {

			PreparedStatement stmt = null;

			try {
				conexao = EmpresaDBManager.obterConexao();
				String sql = "INSERT INTO TABELA_COLABORADOR(CODIGO_COLABORADOR,NOME,EMAIL,SALARIO,DATA_CONTRATACAO) VALUES(SQ_COLABORADOR.NEXTVAL,?,?,?,?)";
				stmt = conexao.prepareStatement(sql);
				stmt.setString(1, colaborador.getNome());
				stmt.setString(2, colaborador.getEmail());
				stmt.setDouble(3, colaborador.getSalario());
				java.sql.Date data = new java.sql.Date(colaborador.getDataContratacao().getTimeInMillis());
				stmt.setDate(4, data);

				stmt.executeUpdate();
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					stmt.close();
					conexao.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		public List<Colaborador> listar() {
			//Criar lista de colaboradores
			List<Colaborador> lista = new ArrayList<Colaborador>();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try {
				conexao = EmpresaDBManager.obterConexao();
				stmt = conexao.prepareStatement("SELECT * FROM TABELA_COLABORADOR");
				rs = stmt.executeQuery();
				
				//percorre os registros encontrados
				while(rs.next()) {
					int codigo = rs.getInt("CODIGO_COLABORADOR");
					String nome = rs.getString("NOME");
					String email = rs.getString("EMAIL");
					double salario = rs.getDouble("SALARIO");
					java.sql.Date data = rs.getDate("DATA_CONTRATACAO");
					Calendar dataContratacao = Calendar.getInstance();
					dataContratacao.setTimeInMillis(data.getTime());
					//cria um objeto Colaborador com as informações encontradas
					Colaborador colaborador = new Colaborador(codigo,nome,email,salario,dataContratacao);
					//Adiciona o colaborador na lista criada 
					lista.add(colaborador);
				}
				
			} catch(SQLException e) {
				e.printStackTrace();
				
			} finally {
				try {
					stmt.close();
					rs.close();
					conexao.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
			return lista;
		}
		
		public void remover(int codigo) {
			PreparedStatement stmt = null;
			
			try {
				conexao = EmpresaDBManager.obterConexao();
				String sql = "DELETE FROM TABELA_COLABORADOR WHERE CODIGO_COLABORADOR = ?";
				stmt = conexao.prepareStatement(sql);
				stmt.setInt(1, codigo);
				stmt.executeUpdate();
			} catch(SQLException e) {
				e.printStackTrace();
			}finally {
				try{
					stmt.close();
					conexao.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		public Colaborador buscaId(int codigoBusca){
			Colaborador colaborador = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
			  conexao = EmpresaDBManager.obterConexao();
			  stmt = conexao.prepareStatement("SELECT * FROM TABELA_COLABORADOR WHERE CODIGO_COLABORADOR = ?");
			  stmt.setInt(1, codigoBusca);
			  rs = stmt.executeQuery();

			  if (rs.next()){
			    int codigo = rs.getInt("CODIGO_COLABORADOR");
			    String nome = rs.getString("NOME");
			    String email = rs.getString("EMAIL");
			    double salario = rs.getDouble("SALARIO");
			    java.sql.Date data = rs.getDate("DATA_CONTRATACAO");
			    Calendar dataContratacao = Calendar.getInstance();
			    dataContratacao.setTimeInMillis(data.getTime());
			    colaborador = new Colaborador(codigo, nome, email, salario, dataContratacao);
			  }
			  
			} catch (SQLException e) {
			  e.printStackTrace();
			}finally {
			  try {
			    stmt.close();
			    rs.close();
			    conexao.close();
			  } catch (SQLException e) {
			    e.printStackTrace();
			  }
			}
			return colaborador;
			}
			
		
		public void atualizar(Colaborador colaborador){
			  PreparedStatement stmt = null;

			  try {
			    conexao = EmpresaDBManager.obterConexao();
			    //desabilita o autocomit
			    conexao.setAutoCommit(false);
			    String sql = "UPDATE TABELA_COLABORADOR SET NOME = ?, EMAIL = ?, SALARIO = ?, DATA_CONTRATACAO = ? WHERE CODIGO_COLABORADOR = ?";
			    stmt = conexao.prepareStatement(sql);
			    stmt.setString(1, colaborador.getNome());
			    stmt.setString(2, colaborador.getEmail());
			    stmt.setDouble(3, colaborador.getSalario());
			    java.sql.Date data = new java.sql.Date(colaborador.getDataContratacao().getTimeInMillis());
			    stmt.setDate(4, data);
			    stmt.setInt(5, colaborador.getCodigo());

			    stmt.executeUpdate();
			    
			    //Executa a transação, dessa maneira tem um controle do que está em commit
			    
			    conexao.commit();
			  } catch (SQLException se) {
			    try {
			    	//Não efetiva as transações
					conexao.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			  } finally {
			    try {
			      stmt.close();
			      conexao.close();
			    } catch (SQLException e) {
			      e.printStackTrace();
			    } 
			  }
			}
		
	}

