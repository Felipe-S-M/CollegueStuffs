package br.com.coldigogeladeiras.jdbcinterface;

import java.sql.SQLException;
import java.util.List;
import br.com.coldigogeladeiras.modelo.Marca;
import br.com.coldigogeladeiras.modelo.Produto;

public interface MarcaDAO {
	
	public List<Marca> buscar();
	public boolean inserir(String marca);
	public boolean deletar(int id) throws SQLException;
	public boolean alterar(Marca marca);
	public boolean validarUsoMarca(int id) throws SQLException;
	public boolean alterarStatus(int id) throws SQLException;
	public boolean marcaExiste(String marca);
	public int buscarStatusAlterado(int id) throws SQLException;
}
