package view;

import controller.api.ProdutoController;
import controller.impl.ProdutoControllerImpl;
import model.entity.Produto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ListagemProdutos extends JFrame {

    private JPanel panel;
    private JList<Produto> listProdutos;
    private DefaultListModel<Produto> listProdutoModel;
    private ProdutoController produtoController;

    public ListagemProdutos() {
        setTitle("Listagem de produtos");
        setLayout(new FlowLayout());

        produtoController = new ProdutoControllerImpl();

        this.panel = new JPanel();
        this.panel.setLayout(new FlowLayout());
        this.panel.setPreferredSize(new Dimension(500, 800));
        add(this.panel);

        criarBotao("Voltar", new ButtonVoltarHandler());

        criarList();

        List<Produto> produtosCarregados = produtoController.listarTodos();
        listProdutoModel.addAll(produtosCarregados);

        setSize(new Dimension(500, 800));
        setPreferredSize(new Dimension(500, 800));
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public ListagemProdutos(boolean faltantes) {
        setTitle("Produtos para repor estoque");
        setLayout(new FlowLayout());

        produtoController = new ProdutoControllerImpl();

        this.panel = new JPanel();
        this.panel.setLayout(new FlowLayout());
        this.panel.setPreferredSize(new Dimension(500, 800));
        add(this.panel);

        criarBotao("Voltar", new ListagemProdutos.ButtonVoltarHandler());

        criarList();

        List<Produto> produtosCarregados = produtoController.listarFaltantes();
        listProdutoModel.addAll(produtosCarregados);

        setSize(new Dimension(500, 800));
        setPreferredSize(new Dimension(500, 800));
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void criarBotao(String label, ActionListener handler) {
        JButton button = new JButton(label);
        button.addActionListener(handler);
        button.setPreferredSize((new Dimension(300, 80)));
        this.panel.add(button);
    }

    private void criarList() {
        listProdutoModel = new DefaultListModel<Produto>();
        listProdutos = new JList<Produto>(listProdutoModel);
        listProdutos.setPreferredSize(new Dimension(400, 500));
        JScrollPane scrollPane = new JScrollPane(listProdutos);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        panel.add(scrollPane);
    }

    private class ButtonVoltarHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
        }
    }
}
