package br.com.coldigogeladeiras.jdbcinterface;

import java.sql.SQLException;
import java.util.List;

import com.google.gson.JsonObject;

import br.com.coldigogeladeiras.modelo.Produto;

public interface ProdutoDAO {
	
	public boolean inserir(Produto produto) throws SQLException;
	public List<JsonObject> buscarPorNome(String nome);
	public boolean deletar(int id);
	public Produto buscarPorId(int id);
	public boolean alterar(Produto produto);
	
}