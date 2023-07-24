package model.dao.api;

import model.entity.Produto;

import java.util.List;

public interface ProdutoDAO extends DAO<Produto> {

    public Produto buscarPorId(int id);
    public List<Produto> listarFaltantes();
}
