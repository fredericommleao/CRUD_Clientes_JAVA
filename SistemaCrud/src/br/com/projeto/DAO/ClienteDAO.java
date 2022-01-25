package br.com.projeto.DAO ;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import br.com.projeto.JDBC.FabricaConexao;
import br.com.projeto.model.Cliente;
import br.com.projeto.model.WebServiceCep;

public class ClienteDAO {

	private final String INSERT = "INSERT INTO cliente (nome, email, telefone, cep, cidade , estado , complemento) "
			+ "VALUES (?,?,?,?,?,?,?)";
	
	private final String UPDATE = "UPDATE cliente SET nome=?, email=?, telefone=?, cep=?, cidade=? , estado=?,"
			+ "complemento=? WHERE ID=?";
	
	private final String LISTBYNOME = "SELECT * FROM cliente WHERE nome LIKE ? ";
	
	private final String DELETE = "DELETE FROM cliente WHERE ID =?";
	
	private final String LIST = "SELECT * FROM cliente";
	
	private final String LISTBYID = "SELECT * FROM cliente WHERE ID=?";

	public void inserir(Cliente cliente) {
		if (cliente != null) {
			Connection conn = null;
			try {
				conn = FabricaConexao.getConexao();
				PreparedStatement pstm;
				pstm = conn.prepareStatement(INSERT);

				pstm.setString(1, cliente.getNome());
				pstm.setString(3, cliente.getEmail());
				pstm.setString(2, cliente.getTelefone());
				pstm.setString(4, cliente.getCep());
				pstm.setString(5, cliente.getCidade());
				pstm.setString(6, cliente.getBairro());
				pstm.setString(7, cliente.getComplemento());

				pstm.execute();
				JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso");
				FabricaConexao.fechaConexao(conn, pstm);

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro ao inserir cliente no banco de"
						+ "dados " + e.getMessage());
			}
		} else {
			System.out.println("O cliente enviado por parâmetro está vazio");
		}
	}

	public void atualizar(Cliente cliente) {
		if (cliente != null) {
			Connection conn = null;
			try {
				conn = FabricaConexao.getConexao();
				PreparedStatement pstm;
				pstm = conn.prepareStatement(UPDATE);
				
				pstm.setInt(1, cliente.getId());
				pstm.setString(2, cliente.getNome());
				pstm.setString(3, cliente.getEmail());
				pstm.setString(4, cliente.getTelefone());
				pstm.setString(5, cliente.getCep());
				pstm.setString(6, cliente.getCidade());
				pstm.setString(7, cliente.getBairro());
				pstm.setString(8, cliente.getComplemento());

				pstm.execute();
				JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso");
				FabricaConexao.fechaConexao(conn);

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro ao atualizar cliente no banco de"
						+ "dados " + e.getMessage());
			}
		} else {
			JOptionPane.showMessageDialog(null, "O cliente enviado por parâmetro está vazio");
		}


	}

	public void remover(int id) {
		Connection conn = null;
		try {
			conn = FabricaConexao.getConexao();
			PreparedStatement pstm;
			pstm = conn.prepareStatement(DELETE);

			pstm.setInt(1, id);

			pstm.execute();
			FabricaConexao.fechaConexao(conn, pstm);

		} catch (Exception e) {
			JOptionPane.showMessageDialog
			(null, "Erro ao excluir cliente do banco de"
			+ "dados " + e.getMessage());
		}
	}

	public List<Cliente> getClientes() {
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		
		try {
			conn = FabricaConexao.getConexao();
			pstm = conn.prepareStatement(LIST);
			rs = pstm.executeQuery();
			
			while (rs.next()) {
				Cliente cliente = new Cliente();

				cliente.setId(rs.getInt("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setEmail(rs.getString("email"));
				cliente.setTelefone(rs.getString("telefone"));
				cliente.setCep(rs.getString("cep"));
				cliente.setCidade(rs.getString("cidade"));
				cliente.setBairro(rs.getString("estado"));
				cliente.setComplemento(rs.getString("complemento"));
				
				clientes.add(cliente);
			}
			FabricaConexao.fechaConexao(conn, pstm, rs);
		} catch (Exception e) {
			JOptionPane.showMessageDialog
			(null, "Erro ao listar cliente" + e.getMessage());
		}
		return clientes;
	}

	public Cliente getContatoById(int id) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Cliente cliente = new Cliente();
		try {
			conn = FabricaConexao.getConexao();
			pstm = conn.prepareStatement(LISTBYID);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			while (rs.next()) {
				
				cliente.setId(rs.getInt("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setEmail(rs.getString("email"));
				cliente.setTelefone(rs.getString("telefone"));
				cliente.setCep(rs.getString("cep"));
				cliente.setCidade(rs.getString("cidade"));
				cliente.setBairro(rs.getString("estado"));
				cliente.setComplemento(rs.getString("complemento"));
			
			}
			FabricaConexao.fechaConexao(conn, pstm, rs);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao listar contatos" + e.getMessage());
		}
		return cliente;
	}
	
		public List<Cliente> getClientes(String nome) {
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		
		try {
			conn = FabricaConexao.getConexao();
			pstm = conn.prepareStatement(LISTBYNOME);
			
			pstm.setString(1, nome);			
			rs = pstm.executeQuery();
		
			while (rs.next()) {
				Cliente cliente = new Cliente();

				cliente.setId(rs.getInt("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setEmail(rs.getString("email"));
				cliente.setTelefone(rs.getString("telefone"));
				cliente.setCep(rs.getString("cep"));
				cliente.setCidade(rs.getString("cidade"));
				cliente.setBairro(rs.getString("estado"));
				cliente.setComplemento(rs.getString("complemento"));
				
				clientes.add(cliente);
			}
			FabricaConexao.fechaConexao(conn, pstm, rs);
		} catch (Exception e) {
			JOptionPane.showMessageDialog
			(null, "Erro ao listar cliente" + e.getMessage());
		}
		return clientes;
	}

		
		public Cliente buscaCep(String cep) {
		       
	        WebServiceCep webServiceCep = WebServiceCep.searchCep(cep);
	       
	        Cliente obj = new Cliente();

	        if (webServiceCep.wasSuccessful()) {
	            
	        	obj.setBairro(webServiceCep.getBairro());
	            obj.setCidade(webServiceCep.getCidade());
	            obj.setComplemento(webServiceCep.getLogradouroFull());
	            
	            return obj;    
	        } else {
	            JOptionPane.showMessageDialog
	            (null, "Erro numero: " + webServiceCep.getResulCode());
	            JOptionPane.showMessageDialog
	            (null, "Descrição do erro: " + webServiceCep.getResultText());
	            return null;
	       }
	 }			
}