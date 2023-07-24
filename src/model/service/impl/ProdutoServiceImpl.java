package model.service.impl;

import model.dao.api.ProdutoDAO;
import model.dao.impl.ProdutoDaoImpl;
import model.entity.Produto;
import model.exception.NomeInexistenteException;
import model.exception.NumeroInvalidoException;
import model.exception.ProdutoInexistenteException;
import model.service.api.ProdutoService;

import java.util.List;

public class ProdutoServiceImpl implements ProdutoService {

    private ProdutoDAO produtoDao;

    public ProdutoServiceImpl() {
        this.produtoDao = new ProdutoDaoImpl();
    }
    @Override
    public void salvar(Produto produto) {
        if (produto.getNome().isEmpty()) {
            throw new NomeInexistenteException("O produto precisa ter um nome para o cadastro!");
        }
        if (produto.getQuantidadeEstoque() < 0) {
            throw new NumeroInvalidoException("Número inválido para a quantidade de estoque!");
        }
        this.produtoDao.salvar(produto);
    }

    @Override
    public void excluir(int id) {
        Produto produtoParaExclusao = this.buscarPorId(id);
        if (produtoParaExclusao == null) {
            throw new ProdutoInexistenteException("Não foi possível obter o produto que você deseja remover!");
        }
        this.produtoDao.excluir(id);
    }

    @Override
    public List<Produto> listarTodos() {
        return this.produtoDao.listarTodos();
    }

    @Override
    public List<Produto> listarFaltantes() {
        return this.produtoDao.listarFaltantes();
    }

    public Produto buscarPorId(int id) {
        return this.produtoDao.buscarPorId(id);
    }
}
