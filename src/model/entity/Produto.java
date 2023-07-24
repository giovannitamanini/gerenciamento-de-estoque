package model.entity;

import java.io.Serializable;

public class Produto implements Serializable {

    private int idProduto;
    private String nome;
    private int quantidadeEstoque;

    public Produto() {}

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    @Override
    public String toString() {
        return "Código: " + this.idProduto + " - " + "Nome: " + this.nome + " - " + "Quantidade: " + this.quantidadeEstoque;
    }
}
