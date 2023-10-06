package residuos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InserirDadosColetaResiduos {

	public static void main(String[] args) {

		Cadastros.cadastroEmpresas();
		Cadastros.cadastroDestinos();
		Cadastros.cadastroFuncionarios();
		Cadastros.cadastroVeiculos();
		Cadastros.cadastroEquipe();
		Cadastros.coletaSp();
		Cadastros.coletaMg();
		Cadastros.coletaRj();

		System.out.println("Ok");

	}
}
