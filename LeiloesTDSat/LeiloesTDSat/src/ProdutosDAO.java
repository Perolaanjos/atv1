/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
     conn = new conectaDAO().conectar(); // Inicializa a conexão
    if (conn == null) {
        JOptionPane.showMessageDialog(null, "Erro: conexão não foi estabelecida.");
        return;
    }

    String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";

        try {
        prep = conn.prepareStatement(sql);
        prep.setString(1, produto.getNome());   
         prep.setDouble(2, produto.getValor()); 
          prep.setString(3, produto.getStatus()); 
           prep.executeUpdate();

            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
        } catch (Exception e) {
            
                JOptionPane.showMessageDialog(null, "Erro! " + e.getMessage());
            
        } finally {
            
            try {
                
                if (prep != null) prep.close();
                 if (conn != null) conn.close();
                    }catch (Exception ex) {
                  JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + ex.getMessage());
            }
        }
        
        
        
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        conn = new conectaDAO().conectar(); 
        String sql = "SELECT * FROM produtos";
        
        
        try {
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();

            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                 listagem.add(produto); 
            }
             } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + e.getMessage());
        } finally {
            try {
                if (resultset != null) resultset.close();
                if (prep != null) prep.close();
                if (conn != null) conn.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + ex.getMessage());
            }
        }
             return listagem;
        }
       
    public void venderProduto (ProdutosDTO p) {
    
    }
    public ArrayList<ProdutosDTO> listarProdutosVendidos(){
ArrayList<ProdutosDTO> listaVendidos = null;
return listaVendidos;
}
    
    
    
    }
    
    
    
        


