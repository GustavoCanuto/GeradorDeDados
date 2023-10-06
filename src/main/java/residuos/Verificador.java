package residuos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Verificador {
	
	public static boolean verificaPlaca(String placa) {
	    ConexaoBD conexao = new ConexaoBD();

	   
	        String sql = "SELECT id FROM tb_veiculo WHERE placa = ?";

	        try ( Connection conn = conexao.recuperarConexao();
	        		PreparedStatement preparedStatement = conn.prepareStatement(sql)){

	        // Define o valor do parâmetro da placa na consulta
	        preparedStatement.setString(1, placa);

	        ResultSet resultSet = preparedStatement.executeQuery();
	        
	
	        
	        // Verifica se a consulta retornou algum resultado (se a placa já existe)
	        if (resultSet.next()) {
	            return true; // A placa existe no banco de dados
	        }
	    } catch (SQLException e) {
	        throw new RuntimeException(e);
	    }
		

	    return false; // A placa não existe no banco de dados
	}

	
}
