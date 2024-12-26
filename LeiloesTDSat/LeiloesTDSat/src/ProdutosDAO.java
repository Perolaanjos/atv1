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
     conn = new conectaDAO().conectar(); 
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
       
    public boolean venderProduto(ProdutosDTO p) {
    conn = new conectaDAO().conectar();
    
    if (conn == null) {
        JOptionPane.showMessageDialog(null, "Erro: não foi possível estabelecer conexão com o banco.");
        return false;
    }

    String sql = "UPDATE produtos SET status = 'vendido' WHERE id = ?";

    try {
        prep = conn.prepareStatement(sql);
        prep.setInt(1, p.getId());
        int rowsAffected = prep.executeUpdate(); 

        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
            return true;  
        } else {
            JOptionPane.showMessageDialog(null, "Produto não encontrado.");
            return false;  
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao vender produto: " + e.getMessage());
        return false; 
    } finally {
        try {
            if (prep != null) prep.close();
            if (conn != null) conn.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + ex.getMessage());
        }
    }


    }
    public ArrayList<ProdutosDTO> listarProdutosVendidos(){
        conn = new conectaDAO().conectar(); 
 ArrayList<ProdutosDTO> listaVendidos = new ArrayList<>();
 String sql = "SELECT * FROM produtos WHERE status = 'vendido'";
    
    try {
        prep = conn.prepareStatement(sql);
        resultset = prep.executeQuery();

        
        while (resultset.next()) {
            ProdutosDTO produto = new ProdutosDTO();
            produto.setId(resultset.getInt("id"));
            produto.setNome(resultset.getString("nome"));
            produto.setValor(resultset.getInt("valor"));
            produto.setStatus(resultset.getString("status"));
            listaVendidos.add(produto); 
        }
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar produtos vendidos: " + e.getMessage());
    } finally {
        try {
           
            if (resultset != null) resultset.close();
            if (prep != null) prep.close();
            if (conn != null) conn.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + ex.getMessage());
        }
    }
    
    return listaVendidos;  
}
   public void excluirProduto(int idProduto) {
    conn = new conectaDAO().conectar();  // Inicializa a conexão
    String sql = "DELETE FROM produtos WHERE id = 7";  // SQL para deletar o produto com o ID informado

    try {
        prep = conn.prepareStatement(sql);
        prep.setInt(1, idProduto);  // Define o ID do produto a ser excluído
        int rowsAffected = prep.executeUpdate();  // Executa o comando SQL
        
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "Produto excluído com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Produto não encontrado.");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao excluir produto: " + e.getMessage());
    } finally {
        try {
            if (prep != null) prep.close();
            if (conn != null) conn.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + ex.getMessage());
        }
    }
}

    
}
    
    
    
    
    
    
    
        


