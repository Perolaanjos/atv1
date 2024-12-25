
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
public class conectaDAO {

    Connection conn = null;

    public Connection conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/uc11?useSSL=false", "root", "mysql1995"); 
            System.out.println("Conexão realizada!");

           
            java.sql.Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM produtos");
              rs.next() ;
               System.out.println("Número de produtos: " + rs.getInt(1));

            return conn; 
              } catch (ClassNotFoundException | SQLException ex) {
                 System.out.println("Falha na conexão com o banco de dados! " + ex.getMessage());
                   return null; 
        }
    }

    public void desconectar() {
        try {
            if (conn != null) {
                 conn.close();
              System.out.println("Conexão encerrada.");
            }
        } catch (SQLException ex) {
          System.out.println("Erro ao encerrar conexão: " + ex.getMessage());
        }
    }
}