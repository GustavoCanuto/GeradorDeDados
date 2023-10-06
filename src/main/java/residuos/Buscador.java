package residuos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Buscador {
	
	public static List<Integer> empresaComFuncionarios() {
		ConexaoBD conexao = new ConexaoBD();

		List<Integer> idList = new ArrayList<>();

		

			String sql = "SELECT distinct e.id "
					+ "FROM tb_empresa e "
					+ "join tb_funcionario f ON e.id = f.fk_empresa";

	
			try( Connection conn = conexao.recuperarConexao();
					PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
			

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				idList.add(id);
			}

	
			return idList;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
			public static List<Integer> bairrosPorCidade(int cidade) {
				ConexaoBD conexao = new ConexaoBD();

				List<Integer> idList = new ArrayList<>();

				

				 String sql = "SELECT id FROM tb_bairro WHERE fk_cidade = ? limit 50";
			
					try( Connection conn = conexao.recuperarConexao();
							PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
					
						  // Define o valor do parâmetro da placa na consulta
				        preparedStatement.setInt(1, cidade);

				        ResultSet resultSet = preparedStatement.executeQuery();
				        

					while (resultSet.next()) {
						int id = resultSet.getInt("id");
						idList.add(id);
					}

				
					Collections.shuffle(idList);

			
					
					
					return idList;

				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
				

	}

			
			
			public static List<Integer> destinoPorEstado(int estado, String tipo) {
				ConexaoBD conexao = new ConexaoBD();
				
				List<Integer> idList = new ArrayList<>();
				
				String sql = "SELECT d.id FROM tb_destino d "
					  		+ "join tb_cidade c on d.fk_cidade = c.id "
					  		+ "WHERE c.fk_estado = ? and d.tipo_local = 'RECICLAGEM' limit 5";
			

				if (tipo.equals("comum")) {
					 sql = "SELECT d.id FROM tb_destino d "
						  		+ "join tb_cidade c on d.fk_cidade = c.id "
						  		+ "WHERE c.fk_estado = ? and d.tipo_local <> 'RECICLAGEM' limit 5";
				} 
				
				
			
				
					try( Connection conn = conexao.recuperarConexao();
							PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
					
						  // Define o valor do parâmetro da placa na consulta
				        preparedStatement.setInt(1, estado);
				     

				        ResultSet resultSet = preparedStatement.executeQuery();
				        

					while (resultSet.next()) {
						int id = resultSet.getInt("id");
						idList.add(id);
					}
					
					
					return idList;

				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
				

	}
			
			public static List<Integer> empresaAptaEquipe() {
				
				ConexaoBD conexao = new ConexaoBD();
				
				List<Integer> idList = new ArrayList<>();
				
				String sql = "select distinct te.id from tb_veiculo v "
						+"join tb_empresa te on te.id = v.fk_empresa  "
						+"join tb_funcionario tf on tf.fk_empresa  = te.id  ";
					
	
				
					try( Connection conn = conexao.recuperarConexao();
							PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
					
						ResultSet resultSet = preparedStatement.executeQuery();

						while (resultSet.next()) {
							int id = resultSet.getInt("id");
							idList.add(id);
						}

	
					return idList;

				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
				

	}
			
			public static List<Integer> funcionariosEmpresa(int empresa, String funcao) {
				
				ConexaoBD conexao = new ConexaoBD();
				
				List<Integer> idList = new ArrayList<>();
				
				String sql = "select id from tb_funcionario "
						    +"where fk_empresa = ? and funcao = ?";
	
				
					try( Connection conn = conexao.recuperarConexao();
							PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
					
						 preparedStatement.setInt(1, empresa);
						 preparedStatement.setString(2, funcao);
						 
						ResultSet resultSet = preparedStatement.executeQuery();

						while (resultSet.next()) {
							int id = resultSet.getInt("id");
							idList.add(id);
						}
					
					return idList;

				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
				

	}
			
			public static List<Integer> veiculoEmpresa(int empresa, String tipo) {
				
				ConexaoBD conexao = new ConexaoBD();
				
				List<Integer> idList = new ArrayList<>();
				
				String sql = "select id from tb_veiculo "
					    +"where fk_empresa = ? and tipo = ?";
	
				
					try( Connection conn = conexao.recuperarConexao();
							PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
					
						preparedStatement.setInt(1, empresa);
						preparedStatement.setString(2, tipo);
						 
						ResultSet resultSet = preparedStatement.executeQuery();

						while (resultSet.next()) {
							int id = resultSet.getInt("id");
							idList.add(id);
						}

			
						Collections.shuffle(idList);
			
					
					return idList;

				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
				

	}

			
			public static List<Integer> equipe(String descricao) {
				
				ConexaoBD conexao = new ConexaoBD();
				
				List<Integer> idList = new ArrayList<>();
				
				String sql = "select e.id from tb_equipe e "
						+"left join tb_coleta c on c.fk_equipe = e.id "
					    +"where descricao like  ? and c.fk_equipe is null limit 30";
	
				
					try( Connection conn = conexao.recuperarConexao();
							PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
					
				
						preparedStatement.setString(1, "%" + descricao + "%");
						 
						ResultSet resultSet = preparedStatement.executeQuery();

						while (resultSet.next()) {
							int id = resultSet.getInt("id");
							idList.add(id);
						}
		
						Collections.shuffle(idList);
						
					return idList;

				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
				

	}


}
