package residuos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

public class teste {

	public static void main(String[] args) {
		System.out.println("inicio Coleta Sp");

		ConexaoBD conexao = new ConexaoBD();
		String sqlColeta = "INSERT INTO tb_coleta (timestamp, peso, fk_equipe, fk_bairro, fk_destino) VALUES (?, ?, ?, ?, ?)";
		Connection conn = conexao.recuperarConexao();
		Random rand = new Random();

		try {
			PreparedStatement prepColeta = conn.prepareStatement(sqlColeta);

			List<Integer> bairrosUmaCidadeSp = Buscador.bairrosPorCidade(5270);

			List<Integer> equipesColetaComum = Buscador.equipe("Coleta");


		
			LocalDate data = LocalDate.of(2022, 9, 13);
            LocalTime horaInicial = LocalTime.of(20, 0);

			int x = 0;
			for (int i = 0; i < 2; i++) { // 6 equipes
				int equipeId = equipesColetaComum.get(i);
				
				
				//Set<Integer> bairroEscolhido = new HashSet<>();

				for (int j = 0; j < 6; j++) {// faz 100 coletas em cada um desses bairros

					LocalDate dataColeta = data.plusDays(j * 7);
                    LocalTime horaColeta = horaInicial;
					
					for (int y = 0; y < 4; y++) {// 4 bairros
						

						int bairroIdRe = bairrosUmaCidadeSp.get(y+x);

						

						 Timestamp timestamp = Timestamp.valueOf(dataColeta.atTime(horaColeta));
				        
						System.out.println(timestamp);
					
						System.out.println(equipeId);
					
						 horaColeta = horaColeta.plusHours(1);
					}
				}
				
				x += 4;
			}

		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		

	}

}
