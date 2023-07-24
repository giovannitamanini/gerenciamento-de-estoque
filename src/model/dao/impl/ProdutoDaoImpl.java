package model.dao.impl;

import model.dao.api.ProdutoDAO;
import model.entity.Produto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDaoImpl implements ProdutoDAO {

    private static final String INSERT = "INSERT INTO produto (nome, quantidadeEstoque) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE produto SET quantidadeEstoque = ? WHERE idProduto = ?";
    private static final String EXCLUIR = "DELETE FROM produto WHERE idProduto = ?";
    private static final String LISTAR_TODOS = "SELECT * FROM produto";
    private static final String LISTAR_FALTANTES = "SELECT * FROM produto WHERE quantidadeEstoque < 10";
    private static final String BUSCAR_POR_ID = "SELECT * FROM produto WHERE idProduto = ?";


    @Override
    public void salvar(Produto produto) {
        if (produto != null && produto.getIdProduto() == 0) {
            this.salvarProduto(produto);
        } else {
            this.alterarProduto(produto);
        }
    }

    private void salvarProduto(Produto produto) {
        try (PreparedStatement ps = Conexao.getInstance().getConnection().prepareStatement(INSERT)) {
            ps.setString(1, produto.getNome());
            ps.setInt(2, produto.getQuantidadeEstoque());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void alterarProduto(Produto produto) {
        try (PreparedStatement ps = Conexao.getInstance().getConnection().prepareStatement(UPDATE)) {
            ps.setInt(1, produto.getQuantidadeEstoque());
            ps.setInt(2, produto.getIdProduto());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void excluir(int id) {
        try (PreparedStatement ps = Conexao.getInstance().getConnection().prepareStatement(EXCLUIR)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Produto> listarTodos() {
        List<Produto> produtos = new ArrayList<Produto>();
        try (
                PreparedStatement ps = Conexao.getInstance().getConnection().prepareStatement(LISTAR_TODOS);
                ResultSet resultSet = ps.executeQuery();
                ) {
            while (resultSet.next()) {
                Produto produto = new Produto();
                produto.setIdProduto(resultSet.getInt("idProduto"));
                produto.setNome(resultSet.getString("nome"));
                produto.setQuantidadeEstoque(resultSet.getInt("quantidadeEstoque"));
                produtos.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }

    @Override
    public List<Produto> listarFaltantes() {
        List<Produto> produtos = new ArrayList<Produto>();
        try (
                PreparedStatement ps = Conexao.getInstance().getConnection().prepareStatement(LISTAR_FALTANTES);
                ResultSet resultSet = ps.executeQuery();
        ) {
            while (resultSet.next()) {
                Produto produto = new Produto();
                produto.setIdProduto(resultSet.getInt("idProduto"));
                produto.setNome(resultSet.getString("nome"));
                produto.setQuantidadeEstoque(resultSet.getInt("quantidadeEstoque"));
                produtos.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();}
        return produtos;
    }

    @Override
    public Produto buscarPorId(int id) {
        Produto produto = null;
        try (
                PreparedStatement ps = Conexao.getInstance().getConnection().prepareStatement(BUSCAR_POR_ID);
        ) {
            ps.setInt(1, id);
            try (
                    ResultSet resultSet = ps.executeQuery();
            ) {
                while (resultSet.next()) {
                    produto = new Produto();
                    produto.setIdProduto(resultSet.getInt("idproduto"));
                    produto.setNome(resultSet.getString("nome"));
                    produto.setQuantidadeEstoque(resultSet.getInt("quantidadeestoque"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produto;
    }
}
