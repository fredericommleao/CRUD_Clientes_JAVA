package br.com.projeto.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import br.com.projeto.DAO.ClienteDAO;
import br.com.projeto.model.Cliente;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.SystemColor;

public class ListarCliente extends JFrame {

	private JPanel painelFundo;
	private JPanel painelBotoes;
	private JTable tabela;
	private JScrollPane barraRolagem;
	private JButton btInserir;
	private JButton btExcluir;
	private JButton btEditar;
	private DefaultTableModel modelo = new DefaultTableModel();
	private JTextField txPesquisar;

	public ListarCliente() {
		super("Clientes");
		setResizable(false);
		criaJTable();
		criaJanela();
	}

	public void criaJanela() {
		btInserir = new JButton("Inserir");
		btExcluir = new JButton("Excluir");
		btEditar = new JButton("Editar");
		painelBotoes = new JPanel();
		painelBotoes.setBackground(SystemColor.activeCaption);
		painelBotoes.setBounds(0, 328, 884, 33);
		barraRolagem = new JScrollPane(tabela);
		barraRolagem.setBounds(22, 42, 839, 286);
		painelFundo = new JPanel();
		painelFundo.setBackground(SystemColor.activeCaption);
		painelFundo.setLayout(null);
		painelFundo.add(barraRolagem);
		painelBotoes.add(btInserir);
		painelBotoes.add(btEditar);
		painelBotoes.add(btExcluir);
		painelFundo.add(painelBotoes);

		getContentPane().add(painelFundo);
		
		txPesquisar = new JTextField();
		txPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				String nome = "%"+txPesquisar.getText()+"%";
				ClienteDAO dao = new ClienteDAO();
								
				modelo.setNumRows(0);
				
				for (Cliente c : dao.getClientes(nome)) {
					modelo.addRow(new Object[]{c.getId(), c.getNome(),
							c.getEmail(), c.getTelefone(),
							c.getCep(), c.getCidade() , c.getBairro(),
							c.getComplemento()
					});	
				}	
			}
		});
		txPesquisar.setBounds(24, 9, 244, 20);
		painelFundo.add(txPesquisar);
		txPesquisar.setColumns(10);
		
		JButton btnPesquisar = new JButton("PESQUISAR");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nome = "%"+txPesquisar.getText()+"%";
				ClienteDAO dao = new ClienteDAO();
								
				modelo.setNumRows(0);
				
				for (Cliente c : dao.getClientes(nome)) {
					modelo.addRow(new Object[]{c.getId(), c.getNome(),
							c.getEmail(), c.getTelefone(),
							c.getCep(), c.getCidade() , c.getBairro(),
							c.getComplemento()
					});	
				}	
			}
		});
		btnPesquisar.setFont(new Font("Arial", Font.PLAIN, 15));
		btnPesquisar.setBounds(278, 8, 158, 23);
		painelFundo.add(btnPesquisar);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(900, 400);
		setVisible(true);
		btInserir.addActionListener(new BtInserirListener());
		btEditar.addActionListener(new BtEditarListener());
		btExcluir.addActionListener(new BtExcluirListener());
	}

	private void criaJTable() {
		tabela = new JTable(modelo);
		
		modelo.addColumn("Id");
		modelo.addColumn("Nome");
		modelo.addColumn("Email");
		modelo.addColumn("Telefone");
		modelo.addColumn("CEP");
		modelo.addColumn("Cidade");
		modelo.addColumn("Complemento");
		modelo.addColumn("Bairro");
		
		tabela.getColumnModel().getColumn(0)
		.setPreferredWidth(10);
		tabela.getColumnModel().getColumn(1)
		.setPreferredWidth(120);
		tabela.getColumnModel().getColumn(1)
		.setPreferredWidth(80);
		tabela.getColumnModel().getColumn(1)
		.setPreferredWidth(120);
		tabela.getColumnModel().getColumn(1)
		.setPreferredWidth(120);
		tabela.getColumnModel().getColumn(1)
		.setPreferredWidth(80);
		tabela.getColumnModel().getColumn(1)
		.setPreferredWidth(120);
		tabela.getColumnModel().getColumn(1)
		.setPreferredWidth(80);
		
		pesquisar(modelo);
	}

	public static void pesquisar(DefaultTableModel modelo) {
		modelo.setNumRows(0);
		ClienteDAO dao = new ClienteDAO();

		for (Cliente c : dao.getClientes()) {
			modelo.addRow(new Object[]{c.getId(), c.getNome(),
					c.getEmail(), c.getTelefone(),
					c.getCep(), c.getCidade() , c.getBairro(),
					c.getComplemento()
			});	
		}
	}
	
	private class BtInserirListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			InserirCliente ic = new InserirCliente(modelo);
			ic.setVisible(true);
		}
	}

	private class BtEditarListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			int linhaSelecionada = -1;
			linhaSelecionada = tabela.getSelectedRow();
			if (linhaSelecionada >= 0) {
				int idContato = (int) 
				tabela.getValueAt(linhaSelecionada, 0);
				AtualizarCliente ic = new AtualizarCliente
				(modelo, idContato, linhaSelecionada);
				ic.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null,
				"É necesário selecionar uma linha.");
			}
		}
	}

	private class BtExcluirListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			int linhaSelecionada = -1;
			linhaSelecionada = tabela.getSelectedRow();
			if (linhaSelecionada >= 0) {
				int idCliente = (int)
				tabela.getValueAt(linhaSelecionada, 0);
				ClienteDAO dao = new ClienteDAO();
				dao.remover(idCliente);
				modelo.removeRow(linhaSelecionada);
			
				JOptionPane.showMessageDialog
				(null, "Cliente excluído com sucesso");
				
			} else {
				JOptionPane.showMessageDialog(null,
				"É necesário selecionar uma linha.");
			}
		}
	}

	public static void main(String[] args) {
		ListarCliente lc = new ListarCliente();
		lc.setVisible(true);
	}
}