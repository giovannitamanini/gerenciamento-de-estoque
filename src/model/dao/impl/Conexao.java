package model.dao.impl;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static Conexao conexaoSingleton;
    private Connection connection;

    private Conexao() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mariadb://localhost:3307/gerenciamentodeestoque", "root", "12345");
        } catch (SQLException sqlE) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco de dados!");
            sqlE.printStackTrace();
        } catch (ClassNotFoundException classE) {
            JOptionPane.showMessageDialog(null, "Não foi possível carregar a classe de conexão!");
        }
    }

    public static Conexao getInstance() {
        if (conexaoSingleton == null) {
            conexaoSingleton = new Conexao();
        }
        return conexaoSingleton;
    }

    public Connection getConnection() {
        return connection;
    }
}