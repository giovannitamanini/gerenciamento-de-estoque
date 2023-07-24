package utils;

import model.entity.Produto;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class ArquivoUtils {

    private static final String NOME_ARQUIVO_TXT = "produtos.txt";
    private static final String NOME_ARQUIVO_BINARIO = "produtos.binario";

    public static void salvarListaProdutos(List<Produto> listaProdutos) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(NOME_ARQUIVO_TXT, true);

            for (Produto produto : listaProdutos) {
                String valorParaSalvar = produto.getIdProduto() + "##" + produto.getNome() + "##" + produto.getQuantidadeEstoque() + "\n";
                fileWriter.write(valorParaSalvar);
            }
            JOptionPane.showMessageDialog(null, "O arquivo de texto foi salvo com sucesso!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar os produtos!");
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e2) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar o arquivo após salvar os produtos!");
            }
        }
    }
    public static void salvarListaVeiculosBinario(List<Produto> listaProdutos) {
        ObjectOutputStream objectStream = null;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(NOME_ARQUIVO_BINARIO);
            objectStream = new ObjectOutputStream(fileOutputStream);
            for (Produto produto : listaProdutos) {
                objectStream.writeObject(produto);
            }
            JOptionPane.showMessageDialog(null, "O arquivo binário foi salvo com sucesso!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar os produtos em arquivo binário!");
        } finally {
            try {
                if (objectStream != null) {
                    objectStream.close();
                }
            } catch (IOException e2) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar o arquivo após salvar os produtos!");
            }
        }
    }
}