package controller.api;

import model.entity.Produto;
import model.exception.NomeInexistenteException;
import model.exception.NumeroInvalidoException;
import model.exception.ProdutoInexistenteException;

import java.util.List;

public interface ProdutoController {

    public void salvar(Produto produto) throws NomeInexistenteException, NumeroInvalidoException;
    public void excluir(int id) throws ProdutoInexistenteException;
    public List<Produto> listarTodos();
    public List<Produto> listarFaltantes();
    public Produto buscarPorId(int id);
}
