package residuos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GeradorComProbabilidades {

	private List<Integer> ListaCidadeSp;
	private List<Integer> ListaCidadeRj;
	private List<Integer> ListaCidadeMg;
	private Random random;

	public GeradorComProbabilidades() {
		random = new Random();
		ListaCidadeSp = cidadeSp();
		ListaCidadeRj = cidadeRj();
		ListaCidadeMg = cidadeMg();
	}

	public int gerarFkCidade() {
		int randomIndex = random.nextInt(100); // Número aleatório entre 0 e 99

		if (randomIndex < 30) { // 30% de chance de ser de SP
			return ListaCidadeSp.get(random.nextInt(ListaCidadeSp.size()));
		} else if (randomIndex < 50) { // 20% de chance de ser do RJ
			return ListaCidadeRj.get(random.nextInt(ListaCidadeRj.size()));
		} else if (randomIndex < 60) { // 10% de chance de ser de MG
			return ListaCidadeMg.get(random.nextInt(ListaCidadeMg.size()));
		} else { // 40% de chance de ser um número aleatório de 1 a 5550
			return random.nextInt(5550) + 1;
		}
	}

	public String gerarTipoLocal() {
		int randomIndex = random.nextInt(100); // Número aleatório entre 0 e 99

		if (randomIndex < 50) { // 50% de chance de ser Aterro Sanitário
			return "ATERRO_SANITARIO";
		} else if (randomIndex < 80) { // 30% de chance de ser Reciclagem
			return "RECICLAGEM";
		} else { // 20% de chance de ser um dos restantes
			String[] restantes = { "ATERRO_CONTROLADO", "LIXOES", "COMPOSTAGEM", "INCINERADOR", "EXPORTACAO", "OUTRO" };
			return restantes[random.nextInt(restantes.length)];
		}
	}

	public String tipoEmpresa() {
		int randomIndex = random.nextInt(100); // Número aleatório entre 0 e 99

		if (randomIndex < 50) { // 50% de chance de Publico
			return "PUBLICA";
		} else if (randomIndex < 80) { // 30% de chance de ser privado
			return "PRIVADA";
		} else { // 20% um dos dois
			String[] restantes = { "PUBLICA", "PRIVADA" };
			return restantes[random.nextInt(restantes.length)];
		}
	}
		public String tipoVeiculo() {
			int randomIndex = random.nextInt(100); // Número aleatório entre 0 e 99

			if (randomIndex < 50) { // 50% de chance caminhao compactador
				return "CAMINHAO_COMPACTADOR";
			} else if (randomIndex < 80) { // 30% de chance de caminhao de reciclagem
				return "CAMINHAO_DE_RECICLAGEM";
			} else { // 20% um dos dois
				String[] restantes = { "CAMINHAO_COMPACTADOR", "CAMINHAO_DE_RECICLAGEM" };
				return restantes[random.nextInt(restantes.length)];
			}
	}
	public static List<Integer> cidadeSp() {
		ConexaoBD conexao = new ConexaoBD();

		List<Integer> idList = new ArrayList<>();

		

			String sql = "SELECT id FROM tb_cidade WHERE fk_estado = 26";



			
			try( Connection conn = conexao.recuperarConexao();
					PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				idList.add(id);
			}

			// Embaralhar a lista de IDs de forma aleatória
			Collections.shuffle(idList);

			/*
			 * // Exibir os IDs aleatórios
			 * System.out.println("IDs aleatórios de bairros com fk_estado = 26:"); for
			 * (Integer id : idList) { System.out.println(id); }
			 * 
			 */

			return idList;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		

	}

	public static List<Integer> cidadeRj() {
		ConexaoBD conexao = new ConexaoBD();

		List<Integer> idList = new ArrayList<>();

	

			String sql = "SELECT id FROM tb_cidade WHERE fk_estado = 19";



			
			try( Connection conn = conexao.recuperarConexao();
					PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				idList.add(id);
			}

			// Embaralhar a lista de IDs de forma aleatória
			Collections.shuffle(idList);

			/*
			 * // Exibir os IDs aleatórios
			 * System.out.println("IDs aleatórios de bairros com fk_estado = 19:"); for
			 * (Integer id : idList) { System.out.println(id); }
			 * 
			 */

			return idList;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		

	}

	public static List<Integer> cidadeMg() {
		ConexaoBD conexao = new ConexaoBD();

		List<Integer> idList = new ArrayList<>();

		

			String sql = "SELECT id FROM tb_cidade WHERE fk_estado = 11";



			
			try( Connection conn = conexao.recuperarConexao();
					PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				idList.add(id);
			}

			// Embaralhar a lista de IDs de forma aleatória
			Collections.shuffle(idList);

			/*
			 * // Exibir os IDs aleatórios
			 * System.out.println("IDs aleatórios de bairros com fk_estado = 11:"); for
			 * (Integer id : idList) { System.out.println(id); }
			 * 
			 */
			return idList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
}