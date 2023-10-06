package residuos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Cadastros {
	static GeradorComProbabilidades gerador = new GeradorComProbabilidades();
	
	public static void coletaRj() {

		System.out.println("inicio Coleta comum Rj");

		ConexaoBD conexao = new ConexaoBD();
		String sqlColeta = "INSERT INTO tb_coleta (timestamp_inicial,timestamp_final, peso, fk_equipe, fk_bairro, fk_destino) VALUES (?,?, ?, ?, ?, ?)";
		Connection conn = conexao.recuperarConexao();
		Random rand = new Random();

		try {
			PreparedStatement prepColeta = conn.prepareStatement(sqlColeta);

			List<Integer> bairrosUmaCidadeRj = Buscador.bairrosPorCidade(3638);

			List<Integer> equipesColetaComum = Buscador.equipe("Coleta");
			List<Integer> destinosEstadoRjComum = Buscador.destinoPorEstado(19, "comum");

		
			LocalDate dataInicial = LocalDate.of(2022, 9, 13);
            LocalTime horaInicial = LocalTime.of(20, 0);
		
			int x = 0;
			for (int i = 0; i < 6; i++) { // 6 equipes
				int equipeId = equipesColetaComum.get(i);
				
				
				//Set<Integer> bairroEscolhido = new HashSet<>();

				for (int j = 0; j < 60; j++) {// faz 100 coletas em cada um desses bairros

					LocalDate dataColeta = dataInicial.plusDays(j * 7);
                    LocalTime horaColeta = horaInicial;
					
					for (int y = 0; y < 4; y++) {// 4 bairros
						

						int bairroIdRe = bairrosUmaCidadeRj.get(y+x);

						

						 Timestamp timestamp = Timestamp.valueOf(dataColeta.atTime(horaColeta));
						 
						// Adicionar 30 minutos ao timestamp
						 LocalDateTime localDateTime = timestamp.toLocalDateTime().plusMinutes(30);
						 Timestamp novoTimestamp = Timestamp.valueOf(localDateTime);
						 
						int destinoId = destinosEstadoRjComum.get(rand.nextInt(destinosEstadoRjComum.size()));

						prepColeta.setTimestamp(1, timestamp);
						prepColeta.setTimestamp(2, novoTimestamp);
						prepColeta.setFloat(3, 0); // Peso inicial
						prepColeta.setInt(4, equipeId);
						prepColeta.setInt(5, bairroIdRe);
						prepColeta.setInt(6, destinoId);

						prepColeta.executeUpdate(); // Executa a inserção
						
						horaColeta = horaColeta.plusHours(1);
					}
				}
				
				x += 4;
			}

			List<Integer> equipesColetaSeletiva = Buscador.equipe("Reciclagem");
			List<Integer> destinosEstadoRjReciclavel = Buscador.destinoPorEstado(19, "Reciclagem");

			System.out.println("inicio coleta reciclavel Rj");
			
			LocalDate dataInicialRe = LocalDate.of(2022, 9, 13);
            LocalTime horaInicialRe = LocalTime.of(13, 0);
            
			int x1 = 0;
			for (int i = 0; i < 4; i++) { // 6 equipes
				int equipeIdRe = equipesColetaSeletiva.get(i);
				//Set<Integer> bairroEscolhido = new HashSet<>();
				
	

				for (int j = 0; j < 80; j++) {// faz 100 coletas em cada um desses bairros

					LocalDate dataColeta = dataInicialRe.plusDays(j * 7);
                    LocalTime horaColeta = horaInicialRe;

					for (int y = 0; y < 2; y++) {// 4 bairros

						int bairroIdRe = bairrosUmaCidadeRj.get(y+x1);

						 Timestamp timestamp = Timestamp.valueOf(dataColeta.atTime(horaColeta));
						 
						// Adicionar 30 minutos ao timestamp
						 LocalDateTime localDateTime = timestamp.toLocalDateTime().plusMinutes(30);
						 Timestamp novoTimestamp = Timestamp.valueOf(localDateTime);
						 
						int destinoIdRe = destinosEstadoRjReciclavel.get(rand.nextInt(destinosEstadoRjReciclavel.size()));

						prepColeta.setTimestamp(1, timestamp);
						prepColeta.setTimestamp(2, novoTimestamp);
						prepColeta.setFloat(3, 0); // Peso inicial
						prepColeta.setInt(4, equipeIdRe);
						prepColeta.setInt(5, bairroIdRe);
						prepColeta.setInt(6, destinoIdRe);

						prepColeta.executeUpdate(); // Executa a inserção
						
						horaColeta = horaColeta.plusHours(1);
					}

				}
				
				x1 += 4;
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	

	public static void coletaMg() {
	
		System.out.println("inicio Coleta comum Mg");

		ConexaoBD conexao = new ConexaoBD();
		String sqlColeta = "INSERT INTO tb_coleta (timestamp_inicial,timestamp_final, peso, fk_equipe, fk_bairro, fk_destino) VALUES (?, ?, ?, ?, ?, ?)";
		Connection conn = conexao.recuperarConexao();
		Random rand = new Random();

		try {
			PreparedStatement prepColeta = conn.prepareStatement(sqlColeta);

			List<Integer> bairrosUmaCidadeMg = Buscador.bairrosPorCidade(1879);

			List<Integer> equipesColetaComum = Buscador.equipe("Coleta"); // equipes que nao fez coletas
			List<Integer> destinosEstadoMgComum = Buscador.destinoPorEstado(11, "comum");

		
			LocalDate dataInicial = LocalDate.of(2022, 9, 16);
            LocalTime horaInicial = LocalTime.of(19, 0);
		
			int x = 0;
			for (int i = 0; i < 4; i++) { // 6 equipes
				int equipeId = equipesColetaComum.get(i);
				
				
				//Set<Integer> bairroEscolhido = new HashSet<>();

				for (int j = 0; j < 50; j++) {// faz 100 coletas em cada um desses bairros

					LocalDate dataColeta = dataInicial.plusDays(j * 7);
                    LocalTime horaColeta = horaInicial;
					
					for (int y = 0; y < 3; y++) {// 4 bairros
						

						int bairroIdRe = bairrosUmaCidadeMg.get(y+x);

						

						 Timestamp timestamp = Timestamp.valueOf(dataColeta.atTime(horaColeta));
						 
						// Adicionar 30 minutos ao timestamp
						 LocalDateTime localDateTime = timestamp.toLocalDateTime().plusMinutes(30);
						 Timestamp novoTimestamp = Timestamp.valueOf(localDateTime);
						 
						int destinoId = destinosEstadoMgComum.get(rand.nextInt(destinosEstadoMgComum.size()));

						prepColeta.setTimestamp(1, timestamp);
						prepColeta.setTimestamp(2, novoTimestamp);
						prepColeta.setFloat(3, 0); // Peso inicial
						prepColeta.setInt(4, equipeId);
						prepColeta.setInt(5, bairroIdRe);
						prepColeta.setInt(6, destinoId);

						prepColeta.executeUpdate(); // Executa a inserção
						
						horaColeta = horaColeta.plusHours(1);
					}
				}
				
				x += 4;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	
	
	
	
	
	public static void coletaSp() {

		System.out.println("inicio Coleta comum Sp");

		ConexaoBD conexao = new ConexaoBD();
		String sqlColeta = "INSERT INTO tb_coleta (timestamp_inicial,timestamp_final, peso, fk_equipe, fk_bairro, fk_destino) VALUES (?, ?, ?, ?, ?, ?)";
		Connection conn = conexao.recuperarConexao();
		Random rand = new Random();

		try {
			PreparedStatement prepColeta = conn.prepareStatement(sqlColeta);

			List<Integer> bairrosUmaCidadeSp = Buscador.bairrosPorCidade(5270);

			List<Integer> equipesColetaComum = Buscador.equipe("Coleta");
			List<Integer> destinosEstadoSpComum = Buscador.destinoPorEstado(26, "comum");

		
			LocalDate dataInicial = LocalDate.of(2022, 9, 13);
            LocalTime horaInicial = LocalTime.of(20, 0);
		
			int x = 0;
			for (int i = 0; i < 6; i++) { // 6 equipes
				int equipeId = equipesColetaComum.get(i);
				
				
				//Set<Integer> bairroEscolhido = new HashSet<>();

				for (int j = 0; j < 100; j++) {// faz 100 coletas em cada um desses bairros

					LocalDate dataColeta = dataInicial.plusDays(j * 7);
                    LocalTime horaColeta = horaInicial;
					
					for (int y = 0; y < 4; y++) {// 4 bairros
						

						int bairroIdRe = bairrosUmaCidadeSp.get(y+x);

						

						 Timestamp timestamp = Timestamp.valueOf(dataColeta.atTime(horaColeta));
						 
						// Adicionar 30 minutos ao timestamp
						 LocalDateTime localDateTime = timestamp.toLocalDateTime().plusMinutes(30);
						 Timestamp novoTimestamp = Timestamp.valueOf(localDateTime);
						 
						int destinoId = destinosEstadoSpComum.get(rand.nextInt(destinosEstadoSpComum.size()));

						prepColeta.setTimestamp(1, timestamp);
						prepColeta.setTimestamp(2, novoTimestamp);
						prepColeta.setFloat(3, 0); // Peso inicial
						prepColeta.setInt(4, equipeId);
						prepColeta.setInt(5, bairroIdRe);
						prepColeta.setInt(6, destinoId);

						prepColeta.executeUpdate(); // Executa a inserção
						
						horaColeta = horaColeta.plusHours(1);
					}
				}
				
				x += 4;
			}

			List<Integer> equipesColetaSeletiva = Buscador.equipe("Reciclagem");
			List<Integer> destinosEstadoSpReciclavel = Buscador.destinoPorEstado(26, "Reciclagem");

			System.out.println("inicio coleta reciclavel sp");
			
			LocalDate dataInicialRe = LocalDate.of(2022, 9, 13);
            LocalTime horaInicialRe = LocalTime.of(10, 0);
            
			int x1 = 0;
			for (int i = 0; i < 6; i++) { // 6 equipes
				int equipeIdRe = equipesColetaSeletiva.get(i);
				//Set<Integer> bairroEscolhido = new HashSet<>();
				
	

				for (int j = 0; j < 100; j++) {// faz 100 coletas em cada um desses bairros

					LocalDate dataColeta = dataInicialRe.plusDays(j * 7);
                    LocalTime horaColeta = horaInicialRe;

					for (int y = 0; y < 4; y++) {// 4 bairros

						int bairroIdRe = bairrosUmaCidadeSp.get(y+x1);

						 Timestamp timestamp = Timestamp.valueOf(dataColeta.atTime(horaColeta));
						// Adicionar 30 minutos ao timestamp
						 LocalDateTime localDateTime = timestamp.toLocalDateTime().plusMinutes(30);
						 Timestamp novoTimestamp = Timestamp.valueOf(localDateTime);
						 
						int destinoIdRe = destinosEstadoSpReciclavel.get(rand.nextInt(destinosEstadoSpReciclavel.size()));

						prepColeta.setTimestamp(1, timestamp);
						prepColeta.setTimestamp(2, novoTimestamp);
						prepColeta.setFloat(3, 0); // Peso inicial
						prepColeta.setInt(4, equipeIdRe);
						prepColeta.setInt(5, bairroIdRe);
						prepColeta.setInt(6, destinoIdRe);

						prepColeta.executeUpdate(); // Executa a inserção
						
						horaColeta = horaColeta.plusHours(1);
					}

				}
				
				x1 += 4;
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static void coletas() {

		ConexaoBD conexao = new ConexaoBD();

		// codigo inserts

		String sqlColeta = "INSERT INTO tb_coleta (timestamp, peso, fk_equipe, fk_bairro, fk_destino)"
				+ "VALUES (?, ?, ?, ?, ?)";

		Connection conn = conexao.recuperarConexao();

		try {

			PreparedStatement prepColeta = conn.prepareStatement(sqlColeta);

			// lista equipes coleta comum
			List<Integer> equipesColetaComum = Buscador.equipe("Coleta");
			// lista equipes coleta reciclavel
			List<Integer> equipesColetaSeletiva = Buscador.equipe("Reciclagem");

			// sao paulo 5270
			List<Integer> bairrosUmaCidadeSp = Buscador.bairrosPorCidade(5270);
			// rio de janeiro 3638
			List<Integer> bairrosUmaCidadeRj = Buscador.bairrosPorCidade(3638);
			// mg 1879
			List<Integer> bairrosUmaCidadeMg = Buscador.bairrosPorCidade(1879);

			// Destino sao paulo
			List<Integer> destinosEstadoSpComum = Buscador.destinoPorEstado(26, "comum");
			List<Integer> destinosEstadoSpReciclavel = Buscador.destinoPorEstado(26, "Reciclagem");
			// Destino rio de janeiro
			List<Integer> destinoEstadoRjComum = Buscador.destinoPorEstado(19, "comum");
			List<Integer> destinoEstadoRjReciclavel = Buscador.destinoPorEstado(19, "Reciclagem");
			// Destino mg
			List<Integer> destinoEstadoMgComum = Buscador.destinoPorEstado(11, "comum");
			List<Integer> destinoEstadoMgReciclavel = Buscador.destinoPorEstado(11, "Reciclagem");

			// coleta
			prepColeta.setTimestamp(1, null); // datatempo coleta
			prepColeta.setFloat(2, 0); // peso
			prepColeta.setInt(3, 0); // fk equipe
			prepColeta.setInt(4, 0); // fk bairro
			prepColeta.setInt(5, 0); // fk destino

			// prepareStatement.execute();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public static void cadastraFuncionarioEquipe(int coletor, int coletorAuxiliar, int motorista, int equipeId) {
		ConexaoBD conexao = new ConexaoBD();
		Random random = new Random();
		String sqlFuncionarioXEquipe = "INSERT INTO tb_funcionario_equipe (fk_funcionario, fk_equipe)"
				+ "VALUES (?, ?)";

		try (Connection conn2 = conexao.recuperarConexao();
				PreparedStatement prepFuncionarioXEquipe = conn2.prepareStatement(sqlFuncionarioXEquipe);) {

			List<Integer> equipe = new ArrayList<Integer>();

			equipe.add(coletor);
			equipe.add(coletorAuxiliar);
			equipe.add(motorista);

			// System.out.println(equipe);

			for (int eq : equipe) {
				prepFuncionarioXEquipe.setInt(1, eq);
				prepFuncionarioXEquipe.setInt(2, equipeId);
				prepFuncionarioXEquipe.executeUpdate(); // Insere o coletor na equipe

			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public static void cadastroEquipe() {
		System.out.println("inicio cadastro equipes");
		ConexaoBD conexao = new ConexaoBD();
		Random random = new Random();
		String sqlEquipe = "INSERT INTO tb_equipe (nome,fk_veiculo, descricao)" + "VALUES (?, ?, ?)";
		int id = 0;
		try (Connection conn = conexao.recuperarConexao();
				PreparedStatement prepEquipe = conn.prepareStatement(sqlEquipe);) {

			// Empresas aptas para formar equipe
			List<Integer> empresasAptas = Buscador.empresaAptaEquipe();

			for (Integer empresaId : empresasAptas) {

				// Funcionários dessa empresa
				List<Integer> coletorEmpresa = Buscador.funcionariosEmpresa(empresaId, "COLETOR");
				List<Integer> coletorAuxiliarEmpresa = Buscador.funcionariosEmpresa(empresaId, "COLETOR_AUXILIAR");
				List<Integer> motoristaEmpresa = Buscador.funcionariosEmpresa(empresaId, "MOTORISTA");

				// Veículos dessa empresa (comum e reciclagem)
				List<Integer> veiculosEmpresaComum = Buscador.veiculoEmpresa(empresaId, "CAMINHAO_COMPACTADOR");
				List<Integer> veiculosEmpresaReciclagem = Buscador.veiculoEmpresa(empresaId, "CAMINHAO_DE_RECICLAGEM");

				// Loop para criar equipes com um coletor, um coletor auxiliar, um motorista e
				// um veículo
				for (int i = 0; i < random.nextInt(8) + 1 && i < coletorEmpresa.size()
						&& i < coletorAuxiliarEmpresa.size() && i < motoristaEmpresa.size() + 1; i++) {

					prepEquipe.setString(1, "Equipe " + (i + 1)); // Nome da equipe

					// veiculo comum
					if (!veiculosEmpresaComum.isEmpty()) {
						prepEquipe.setInt(2, veiculosEmpresaComum.get(i % veiculosEmpresaComum.size()));
					} else {
						prepEquipe.setInt(2, veiculosEmpresaReciclagem.get(i % veiculosEmpresaReciclagem.size()));
					}

					prepEquipe.setString(3, "Equipe de Coleta"); // Descrição

					prepEquipe.executeUpdate(); // Insere a equipe

					id++;

					cadastraFuncionarioEquipe(coletorEmpresa.get(i), coletorAuxiliarEmpresa.get(i),
							motoristaEmpresa.get(i), id);

				}

				// Repete o processo para equipes com veículos de reciclagem
				for (int i = 0; i < random.nextInt(5) + 1 && i < coletorEmpresa.size()
						&& i < coletorAuxiliarEmpresa.size() && i < motoristaEmpresa.size(); i++) {
					prepEquipe.setString(1, "Equipe Reciclagem " + (i + 1)); // Nome da equipe

					// veiculo reciclagem
					if (!veiculosEmpresaReciclagem.isEmpty()) {
						prepEquipe.setInt(2, veiculosEmpresaReciclagem.get(i % veiculosEmpresaReciclagem.size()));
					} else {
						prepEquipe.setInt(2, veiculosEmpresaComum.get(i % veiculosEmpresaComum.size()));
					}

					prepEquipe.setString(3, "Equipe de Reciclagem"); // Descrição

					prepEquipe.executeUpdate(); // Insere a equipe

					id++;

					cadastraFuncionarioEquipe(coletorEmpresa.get(i + 5), coletorAuxiliarEmpresa.get(i + 5),
							motoristaEmpresa.get(i + 5), id);

				}

			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static void cadastroVeiculos() {
		System.out.println("inicio cadastro veiculos");
		Random random = new Random();

		ConexaoBD conexao = new ConexaoBD();

		String sqlVeiculo = "INSERT INTO tb_veiculo (tipo, capacidade, placa, ano_fabricacao, fk_empresa)"
				+ "VALUES (?, ?, ?, ?, ?)";

		try (Connection conn = conexao.recuperarConexao();
				PreparedStatement prepVeiculo = conn.prepareStatement(sqlVeiculo)) {

			// Cadastrando veiculos em empresas que tem funcionariso

			List<Integer> listaEmpresa = Buscador.empresaComFuncionarios();

			for (int empresa : listaEmpresa) {

				// gerando de 10 a 30 veiculos para essa empresa
				for (int j = 0; j < random.nextInt(18) + 9; j++) {

			

					// cadatrar veiculos empresas que tem funcionarios
					prepVeiculo.setString(1, gerador.tipoVeiculo()); // tipo
					prepVeiculo.setFloat(2, GeradorDeDados.gerarNumeroAleatorio(3, 4)); // capacidade
					prepVeiculo.setString(3, GeradorDeDados.gerarPlacaAleatoriaUnica()); // placa
					prepVeiculo.setInt(4, GeradorDeDados.gerarNumeroAleatorio(4, 4)); // ano fabricação
					prepVeiculo.setInt(5, empresa); // fk empresa

					prepVeiculo.executeUpdate();
				}

			}
			// cadastrando veiculos em empresas aleatorias
			for (int i = 0; i < 200; i++) {
				int empresa = random.nextInt(5000) + 1;

				// gerando de 10 a 30 veiculos para essa empresa
				for (int j = 0; j < random.nextInt(21) + 10; j++) {

					

					// cadatrar veiculos empresas que tem funcionarios
					prepVeiculo.setString(1, gerador.tipoVeiculo()); // tipo
					prepVeiculo.setFloat(2, GeradorDeDados.gerarNumeroAleatorio(3, 4)); // capacidade
					prepVeiculo.setString(3, GeradorDeDados.gerarPlacaAleatoriaUnica()); // placa
					prepVeiculo.setInt(4, GeradorDeDados.gerarNumeroAleatorio(4, 4)); // ano fabricação
					prepVeiculo.setInt(5, empresa); // fk empresa

					prepVeiculo.executeUpdate();
				}
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public static void cadastroFuncionarios() {

		Random random = new Random();
		ConexaoBD conexao = new ConexaoBD();

		String sqlFuncionario = "INSERT INTO tb_funcionario (nome_completo, cpf, data_nascimento, email, fk_empresa, funcao )"
				+ "VALUES (?, ?, ?, ?, ?, ?)";

		try (Connection conn = conexao.recuperarConexao();
				PreparedStatement prepFuncionario = conn.prepareStatement(sqlFuncionario);) {
			for (int i = 0; i < 70; i++) {
				int empresa = random.nextInt(5000) + 1;

				// Cadastro para empresas com 3 funções
				for (int j = 0; j < 18; j++) {
					prepFuncionario.setString(1,GeradorDeDados.gerarNomeAleatorio()); // nome_completo
					prepFuncionario.setString(2, GeradorDeDados.gerarCpfUnico()); // cpf
					prepFuncionario.setDate(3, java.sql.Date.valueOf(GeradorDeDados.gerarDataNascimentoAleatoria())); // data_nascimento
					prepFuncionario.setString(4, GeradorDeDados.gerarEmailUnicoFuncionario()); // email
					prepFuncionario.setInt(5, empresa); // fk empresa
					prepFuncionario.setString(6, "COLETOR"); // função
					prepFuncionario.executeUpdate();
				}

				for (int j = 0; j < 16; j++) {
					prepFuncionario.setString(1, GeradorDeDados.gerarNomeAleatorio()); // nome_completo
					prepFuncionario.setString(2, GeradorDeDados.gerarCpfUnico()); // cpf
					prepFuncionario.setDate(3, java.sql.Date.valueOf(GeradorDeDados.gerarDataNascimentoAleatoria())); // data_nascimento
					prepFuncionario.setString(4, GeradorDeDados.gerarEmailUnicoFuncionario()); // email
					prepFuncionario.setInt(5, empresa); // fk empresa
					prepFuncionario.setString(6, "COLETOR_AUXILIAR"); // função
					prepFuncionario.executeUpdate();
				}

				for (int j = 0; j < 14; j++) {
					prepFuncionario.setString(1,  GeradorDeDados.gerarNomeAleatorio()); // nome_completo
					prepFuncionario.setString(2,  GeradorDeDados.gerarCpfUnico()); // cpf
					prepFuncionario.setDate(3, java.sql.Date.valueOf(GeradorDeDados.gerarDataNascimentoAleatoria())); // data_nascimento
					prepFuncionario.setString(4, GeradorDeDados.gerarEmailUnicoFuncionario()); // email
					prepFuncionario.setInt(5, empresa); // fk empresa
					prepFuncionario.setString(6, "MOTORISTA"); // função
					prepFuncionario.executeUpdate();
				}
			}

			for (int i = 0; i < 105; i++) {
				int empresa = random.nextInt(5000) + 1;

				// Cadastro para empresas com 4 funções
				for (int j = 0; j < 20; j++) {
					prepFuncionario.setString(1, GeradorDeDados.gerarNomeAleatorio()); // nome_completo
					prepFuncionario.setString(2,  GeradorDeDados.gerarCpfUnico()); // cpf
					prepFuncionario.setDate(3, java.sql.Date.valueOf(GeradorDeDados.gerarDataNascimentoAleatoria())); // data_nascimento
					prepFuncionario.setString(4, GeradorDeDados.gerarEmailUnicoFuncionario()); // email
					prepFuncionario.setInt(5, empresa); // fk empresa
					prepFuncionario.setString(6, "COLETOR"); // função
					prepFuncionario.executeUpdate();
				}

				for (int j = 0; j < 25; j++) {
					prepFuncionario.setString(1, GeradorDeDados.gerarNomeAleatorio()); // nome_completo
					prepFuncionario.setString(2, GeradorDeDados.gerarCpfUnico()); // cpf
					prepFuncionario.setDate(3, java.sql.Date.valueOf(GeradorDeDados.gerarDataNascimentoAleatoria())); // data_nascimento
					prepFuncionario.setString(4, GeradorDeDados.gerarEmailUnicoFuncionario()); // email
					prepFuncionario.setInt(5, empresa); // fk empresa
					prepFuncionario.setString(6, "COLETOR_AUXILIAR"); // função
					prepFuncionario.executeUpdate();
				}

				for (int j = 0; j < 20; j++) {
					prepFuncionario.setString(1, GeradorDeDados.gerarNomeAleatorio()); // nome_completo
					prepFuncionario.setString(2, GeradorDeDados.gerarCpfUnico()); // cpf
					prepFuncionario.setDate(3, java.sql.Date.valueOf(GeradorDeDados.gerarDataNascimentoAleatoria())); // data_nascimento
					prepFuncionario.setString(4,  GeradorDeDados.gerarEmailUnicoFuncionario()); // email
					prepFuncionario.setInt(5, empresa); // fk empresa
					prepFuncionario.setString(6, "MOTORISTA"); // função
					prepFuncionario.executeUpdate();
				}

				for (int j = 0; j < 3; j++) {
					prepFuncionario.setString(1, GeradorDeDados.gerarNomeAleatorio()); // nome_completo
					prepFuncionario.setString(2, GeradorDeDados.gerarCpfUnico()); // cpf
					prepFuncionario.setDate(3, java.sql.Date.valueOf(GeradorDeDados.gerarDataNascimentoAleatoria())); // data_nascimento
					prepFuncionario.setString(4,  GeradorDeDados.gerarEmailUnicoFuncionario()); // email
					prepFuncionario.setInt(5, empresa); // fk empresa
					prepFuncionario.setString(6, "SUPERVISOR"); // função
					prepFuncionario.executeUpdate();
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public static void cadastroEmpresas() {

		ConexaoBD conexao = new ConexaoBD();

		String sqlEmpresa = "INSERT INTO tb_empresa (nome, cnpj, telefone, email, tipo)"
				+ "VALUES (?, ?, ?, ?, ?)";

		try (Connection conn = conexao.recuperarConexao();
				PreparedStatement prepEmpresa = conn.prepareStatement(sqlEmpresa)) {
			// Empresa
			for (int i = 0; i < 5000; i++) {
				prepEmpresa.setString(1, "Empresa "+ GeradorDeDados.gerarCombinacaoAleatoria() + i); // nome
				prepEmpresa.setString(2, GeradorDeDados.gerarCnpjAleatorio()); // cnpj
				prepEmpresa.setString(3, GeradorDeDados.gerarNumeroTelefoneAleatorio()); // telefone
				prepEmpresa.setString(4, GeradorDeDados.gerarEmailUnicoEmpresa()); // email
				prepEmpresa.setString(5, gerador.tipoEmpresa()); // tipo

				prepEmpresa.executeUpdate();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public static void cadastroDestinos() {

		Random random = new Random();

		ConexaoBD conexao = new ConexaoBD();

		String sqlDestino = "INSERT INTO tb_destino (nome, tipo_local, fk_cidade, logradouro, numero, cep, capacidade_suportada,"
				+ "fk_empresa )" + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = conexao.recuperarConexao();
				PreparedStatement prepDestino = conn.prepareStatement(sqlDestino)) {
			// Destino
			for (int i = 0; i < 5000; i++) {
				prepDestino.setString(1, "Comp. "+ GeradorDeDados.gerarCombinacaoAleatoria() + i);
				prepDestino.setString(2, gerador.gerarTipoLocal());
				prepDestino.setInt(3, gerador.gerarFkCidade());
				prepDestino.setString(4, GeradorDeDados.gerarLogradouroAleatorio());
				prepDestino.setInt(5, GeradorDeDados.gerarNumeroAleatorio(1,4));
				prepDestino.setString(6, GeradorDeDados.gerarCepAleatorio());
				prepDestino.setFloat(7, GeradorDeDados.gerarNumeroAleatorio(3,5));
				prepDestino.setInt(8, random.nextInt(5000) + 1);

				prepDestino.executeUpdate();
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}



}
