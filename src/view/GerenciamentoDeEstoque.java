package view;

import controller.api.ProdutoController;
import controller.impl.ProdutoControllerImpl;
import model.entity.Produto;
import model.exception.ProdutoInexistenteException;
import utils.ArquivoUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GerenciamentoDeEstoque extends JFrame {

    private JPanel panel;
    private JTextField textFieldId;
    private JTextField textFieldNome;
    private JTextField textFieldQuantidadeEstoque;
    private ProdutoController produtoController;

    public GerenciamentoDeEstoque() {
        setTitle("Gerenciamento de Estoque");
        setLayout(new FlowLayout());

        produtoController = new ProdutoControllerImpl();

        panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setPreferredSize(new Dimension(500, 800));
        add(panel);

        criarTextFieldId("Código do produto");
        criarTextFieldNome("Nome do produto");
        criarTextFieldQuantidadeEstoque("Quantidade em estoque");

        criarBotao("Salvar", new ButtonSalvarHandler());
        criarBotao("Excluir", new ButtonExcluirHandler());
        criarBotao("Listar todos produtos", new ButtonListarHandler());
        criarBotao("Produtos com baixo estoque", new ButtonListarFaltantesHandler());
        criarBotao("Buscar por código", new ButtonBuscarPorIdHandler());
        criarBotao("Backup (arquivo texto)", new ButtonBackupTextoHandler());
        criarBotao("Backup (arquivo binário)", new ButtonBackupBinarioHandler());

        setSize(500, 800);
        setPreferredSize((new Dimension(500, 800)));
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void criarLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setPreferredSize(new Dimension(400, 40));
        panel.add(label);
    }

    private void criarTextFieldId(String label) {
        this.criarLabel(label);
        textFieldId = new JTextField();
        textFieldId.setPreferredSize(new Dimension(400, 40));
        panel.add(textFieldId);
    }

    private void criarTextFieldNome(String label) {
        this.criarLabel(label);
        textFieldNome = new JTextField();
        textFieldNome.setPreferredSize(new Dimension(400, 40));
        panel.add(textFieldNome);
    }

    private void criarTextFieldQuantidadeEstoque(String label) {
        this.criarLabel(label);
        textFieldQuantidadeEstoque = new JTextField();
        textFieldQuantidadeEstoque.setPreferredSize(new Dimension(400, 40));
        panel.add(textFieldQuantidadeEstoque);
    }

    private void criarBotao(String label, ActionListener handler) {
        JButton button = new JButton(label);
        button.addActionListener(handler);
        button.setPreferredSize(new Dimension(200, 80));
        panel.add(button);
    }

    private Produto criarObjetoProduto() {
        Produto produto = new Produto();
        if (!textFieldId.getText().isEmpty()) {
            produto.setIdProduto((Integer.parseInt(textFieldId.getText())));
        }
        produto.setNome(textFieldNome.getText());
        produto.setQuantidadeEstoque(Integer.parseInt(textFieldQuantidadeEstoque.getText()));
        return produto;
    }

    private void limparCampos() {
        textFieldId.setText("");
        textFieldNome.setText("");
        textFieldQuantidadeEstoque.setText("");
    }

    private class ButtonSalvarHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Produto produto = criarObjetoProduto();
            produtoController.salvar(produto);
            limparCampos();
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
        }
    }

    private class ButtonExcluirHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                produtoController.excluir(Integer.parseInt(textFieldId.getText()));
                limparCampos();
                JOptionPane.showMessageDialog(null, "Produto removido com sucesso!");
            } catch (ProdutoInexistenteException exception) {
                JOptionPane.showMessageDialog(null, "É obrigatório informar um valor numérico no id para exclusão!");
            }
        }
    }

    private class ButtonListarHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new ListagemProdutos();
        }
    }

    private class ButtonListarFaltantesHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new ListagemProdutos(true);
        }
    }

    private class ButtonBuscarPorIdHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Produto produto = produtoController.buscarPorId(Integer.parseInt(textFieldId.getText()));
            textFieldId.setText(String.valueOf(produto.getIdProduto()));
            textFieldNome.setText(produto.getNome());
            textFieldQuantidadeEstoque.setText(String.valueOf(produto.getQuantidadeEstoque()));
        }
    }

    private class ButtonBackupTextoHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            produtoController = new ProdutoControllerImpl();
            List<Produto> produtosList = produtoController.listarTodos();
            ArquivoUtils.salvarListaProdutos(produtosList);
        }
    }

    private class ButtonBackupBinarioHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            produtoController = new ProdutoControllerImpl();
            List<Produto> produtosList = produtoController.listarTodos();
            ArquivoUtils.salvarListaVeiculosBinario(produtosList);
        }
    }

    public static void main(String[] args) {
        new GerenciamentoDeEstoque();
    }

}