
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
    
     Connection conn;
    public boolean conectar() {
       //usando o try e catch para conectar o banco de dados
     try {
            Class.forName("com.mysql.cj.jdbc.Driver");
             conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/uc11","root","mysql1995"); 
               System.out.println("Conexão realizada!");
                 java.sql.Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM produtos"); 
            rs.next();
            System.out.println(rs.getInt("COUNT(*)"));
            return true;
            
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Falha na conexão com o banco de dados " + ex.getMessage());
            return false;
        }
   
   }
   public void desconectar(){
        try {
            conn.close();
        } catch (SQLException ex) {
        
        }
    
}
    
}
