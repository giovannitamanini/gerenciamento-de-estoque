package model.service.api;

import model.entity.Produto;

import java.util.List;

public interface ProdutoService {

    public void salvar(Produto produto);
    public void excluir(int id);
    public List<Produto> listarTodos();
    public List<Produto> listarFaltantes();
    public Produto buscarPorId(int id);
}
