package br.com.projeto.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.com.projeto.DAO.ClienteDAO;
import br.com.projeto.model.Cliente;

import java.awt.Font;

public class AtualizarCliente extends JFrame {

	private DefaultTableModel modelo =
	new DefaultTableModel();
	private JPanel painelFundo;
	private JButton btSalvar;
	private JButton btLimpar;
	private JLabel lbNome;
	private JLabel lbTelefone;
	private JLabel lbEmail;
	private JLabel lbId;
	private JTextField txNome;
	private JTextField txId;
	private JTextField txTelefone;
	private JTextField txEmail;
	private int linhaSelecionada;
	private JLabel lbCep;
	private JTextField txCep;
	private JLabel lbCidade;
	private JTextField txCidade;
	private JLabel lbBairro;
	private JTextField txBairro;
	private JLabel lbComplemento;
	private JTextField txComplemento;

	public AtualizarCliente(DefaultTableModel md,
	int id, int linha) {
		
		super("Atualizar");
		setTitle("EDITAR DADOS DO CLIENTE");
		criaJanela();
		modelo = md;
		
		ClienteDAO dao = new ClienteDAO();
		
		Cliente cliente = new Cliente();
		
		cliente = dao.getContatoById(id);
		
		txId.setText(Integer.toString(cliente.getId()));
		txNome.setText(cliente.getNome());
		txEmail.setText(cliente.getEmail());
		txTelefone.setText(cliente.getTelefone());
		txCep.setText(cliente.getCep());
		txCidade.setText(cliente.getCidade());
		txBairro.setText(cliente.getBairro());
		txComplemento.setText(cliente.getComplemento());
		
		linhaSelecionada = linha;
	}

	public void criaJanela() {
		btSalvar = new JButton("ATUALIZAR");
		btSalvar.setBounds(176, 342, 193, 36);
		btLimpar = new JButton("Limpar");
		btLimpar.setBounds(0, 342, 174, 36);
		
		lbNome = new JLabel("ATUALIZAR NOME");
		lbNome.setBounds(10, 78, 97, 36);
		
		lbTelefone = new JLabel("ATUALIZAR TELEFONE");
		lbTelefone.setBounds(10, 125, 134, 36);
		
		lbEmail = new JLabel("ATUALIZAR EMAIL ");
		lbEmail.setBounds(10, 172, 124, 36);
		
		lbCep = new JLabel("ATUALIZAR CEP");
		lbCep.setBounds(10, 230, 97, 14);
		
		txCep = new JTextField();
		txCep.setBounds(117, 227, 134, 20);
		
		lbCidade = new JLabel("CIDADE ");
		lbCidade.setBounds(10, 272, 51, 14);
		
		txCidade = new JTextField();
		txCidade.setBounds(69, 269, 86, 20);
		txCidade.setColumns(10);
		
		lbBairro = new JLabel("BAIRRO");
		lbBairro.setBounds(181, 272, 57, 14);
		
		txBairro = new JTextField();
		txBairro.setBounds(244, 269, 97, 20);
		txBairro.setColumns(10);
		
		lbComplemento = new JLabel("COMPLEMENTO");
		lbComplemento.setBounds(10, 306, 97, 14);
		
		txComplemento = new JTextField();
		txComplemento.setBounds(103, 303, 266, 20);
		txComplemento.setColumns(10);
		
		lbId = new JLabel("ID :");
		lbId.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbId.setBounds(10, 2, 37, 36);
		
		txNome = new JTextField();
		txNome.setBounds(163, 78, 206, 36);
		txTelefone = new JTextField();
		txTelefone.setBounds(163, 125, 206, 36);
		txEmail = new JTextField();
		txEmail.setBounds(163, 172, 206, 36);
		
		
		txId = new JTextField();
		txId.setFont(new Font("Tahoma", Font.BOLD, 15));
		txId.setBounds(57, 3, 30, 36);
		txId.setEditable(false);

		painelFundo = new JPanel();
		painelFundo.setLayout(null);
		
		painelFundo.add(lbId);
		painelFundo.add(txId);
		painelFundo.add(lbNome);
		painelFundo.add(txNome);
		painelFundo.add(lbEmail);
		painelFundo.add(txEmail);
		painelFundo.add(lbTelefone);
		painelFundo.add(txTelefone);
		painelFundo.add(lbCep);
		painelFundo.add(txCep);
		painelFundo.add(lbCidade);
		painelFundo.add(txCidade);
		painelFundo.add(lbBairro);
		painelFundo.add(txBairro);
		painelFundo.add(lbComplemento);
		painelFundo.add(txComplemento);
		
		painelFundo.add(btLimpar);
		painelFundo.add(btSalvar);

		getContentPane().add(painelFundo);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(400, 420);
		setVisible(true);

		btSalvar.addActionListener(new
		AtualizarCliente.BtSalvarListener());
		btLimpar.addActionListener(new
		AtualizarCliente.BtLimparListener());
	}

	private class BtSalvarListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			Cliente c = new Cliente();
			
			c.setId(Integer.parseInt(txId.getText()));
			c.setNome(txNome.getText());
			c.setEmail(txEmail.getText());
			c.setTelefone(txTelefone.getText());
			c.setCep(txCep.getText());
			c.setCidade(txCidade.getText());
			c.setBairro(txBairro.getText());
			c.setComplemento(txComplemento.getText());

			ClienteDAO dao = new ClienteDAO();
			
			dao.atualizar(c);
			modelo.removeRow(linhaSelecionada);
			modelo.addRow(new Object[]{ c.getId(),
			c.getNome(), c.getEmail(), c.getTelefone(),
			c.getCep(), c.getCidade(), c.getBairro(),
			c.getComplemento() });
			
			setVisible(false);
		
		}
	}

	private class BtLimparListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			
			txNome.setText("");
			txEmail.setText("");
			txTelefone.setText("");
			txCep.setText("");
			txCidade.setText("");
			txBairro.setText("");
			txComplemento.setText("");
		}
	}
}