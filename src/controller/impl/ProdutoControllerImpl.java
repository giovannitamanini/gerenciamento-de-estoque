package controller.impl;

import controller.api.ProdutoController;
import model.entity.Produto;
import model.exception.NomeInexistenteException;
import model.exception.NumeroInvalidoException;
import model.exception.ProdutoInexistenteException;
import model.service.api.ProdutoService;
import model.service.impl.ProdutoServiceImpl;

import java.util.List;

public class ProdutoControllerImpl implements ProdutoController {

    private ProdutoService produtoService;

    public ProdutoControllerImpl() {
        this.produtoService = new ProdutoServiceImpl();
    }

    @Override
    public void salvar(Produto produto) throws NomeInexistenteException, NumeroInvalidoException {
        this.produtoService.salvar(produto);
    }

    @Override
    public void excluir(int id) throws ProdutoInexistenteException {
        this.produtoService.excluir(id);
    }

    @Override
    public List<Produto> listarTodos() {
        return this.produtoService.listarTodos();
    }

    @Override
    public List<Produto> listarFaltantes() {
        return this.produtoService.listarFaltantes();
    }

    @Override
    public Produto buscarPorId(int id) {
        return this.produtoService.buscarPorId(id);
    }
}
