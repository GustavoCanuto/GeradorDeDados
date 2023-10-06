package residuos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
	
	public Connection recuperarConexao() {	
		try {
			return  DriverManager.getConnection("jdbc:postgresql://localhost:5432/projeto_residuos",
					"postgres","root");
		
		} catch (SQLException e) {
			
			throw new RuntimeException(e);
		}
	}
}
