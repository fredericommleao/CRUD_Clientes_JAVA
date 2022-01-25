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

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class InserirCliente extends JFrame {
	
	private DefaultTableModel modelo = new DefaultTableModel();
	private JPanel painelFundo;
	
	private JButton btSalvar;
	private JButton btLimpar;
	
	private JLabel lbNome;
	private JLabel lbTelefone;
	private JLabel lbEmail;
	
	private JTextField txNome;
	private JTextField txTelefone;
	private JTextField txEmail;
	private JTextField txCep;
	private JTextField txCidade;
	private JTextField txBairro;
	private JTextField txComplemento;
	

	public InserirCliente(DefaultTableModel md) {
		super("Clientes");
		setResizable(false);
		setTitle("CADASTRO DE CLIENTES");
		criaJanela();
		modelo = md;
	}

	
	public void criaJanela() {
		btSalvar = new JButton("SALVAR");
		btSalvar.setBounds(228, 365, 120, 47);
		btLimpar = new JButton("LIMPAR");
		btLimpar.setBounds(67, 365, 132, 47);
		lbNome = new JLabel("DIGITE O NOME ");
		lbNome.setBounds(10, 69, 95, 37);
		lbTelefone = new JLabel("DIGITE O TELEFONE  ");
		lbTelefone.setBounds(10, 130, 120, 37);
		lbEmail = new JLabel("DIGITE O EMAIL");
		lbEmail.setBounds(10, 186, 105, 37);
		txNome = new JTextField(10);
		txNome.setBounds(99, 69, 273, 37);
		txTelefone = new JTextField();
		txTelefone.setBounds(122, 130, 250, 37);
		txEmail = new JTextField();
		txEmail.setBounds(99, 186, 216, 37);

		painelFundo = new JPanel();
		painelFundo.setForeground(Color.GRAY);
		painelFundo.setLayout(null);
		painelFundo.add(lbNome);
		painelFundo.add(txNome);
		painelFundo.add(lbTelefone);
		painelFundo.add(txTelefone);
		painelFundo.add(lbEmail);
		painelFundo.add(txEmail);
		painelFundo.add(btLimpar);
		painelFundo.add(btSalvar);

		getContentPane().add(painelFundo);
		
		JLabel lbCep = new JLabel("DIGITE O CEP");
		lbCep.setBounds(10, 243, 78, 14);
		painelFundo.add(lbCep);
		
		txCep = new JTextField();
		txCep.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if (e.getKeyCode() == KeyEvent.VK_ENTER) { 
			         Cliente obj =  new Cliente();
			         ClienteDAO dao = new ClienteDAO();
			         obj = dao.buscaCep(txCep.getText());
			         
			         txComplemento.setText(obj.getComplemento());
			         txCidade.setText(obj.getCidade());
			         txBairro.setText(obj.getBairro());
			         
			     }
			}
		});
		txCep.setForeground(Color.BLACK);
		txCep.setBounds(99, 240, 172, 20);
		painelFundo.add(txCep);
		txCep.setColumns(10);
		
		JLabel lbCidade = new JLabel("CIDADE :");
		lbCidade.setBounds(10, 289, 56, 14);
		painelFundo.add(lbCidade);
		
		txCidade = new JTextField();
		txCidade.setBounds(65, 286, 105, 20);
		painelFundo.add(txCidade);
		txCidade.setColumns(10);
		
		JLabel lbBairro = new JLabel("BAIRRO :");
		lbBairro.setBounds(180, 289, 64, 14);
		painelFundo.add(lbBairro);
		
		txBairro = new JTextField();
		txBairro.setBounds(228, 286, 144, 20);
		painelFundo.add(txBairro);
		txBairro.setColumns(10);
		
		JLabel lbComplemento = new JLabel("COMPLEMENTO :");
		lbComplemento.setBounds(10, 326, 105, 14);
		painelFundo.add(lbComplemento);
		
		txComplemento = new JTextField();
		txComplemento.setBounds(120, 323, 232, 27);
		painelFundo.add(txComplemento);
		txComplemento.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("TECLE  ENTER");
		lblNewLabel.setBounds(285, 243, 111, 14);
		painelFundo.add(lblNewLabel);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(422, 474);
		setVisible(true);
		btSalvar.addActionListener(new BtSalvarListener());
		btLimpar.addActionListener(new BtLimparListener());
	}

	
	private class BtSalvarListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			Cliente c = new Cliente();
			
			c.setNome(txNome.getText());
			c.setEmail(txEmail.getText());
			c.setTelefone(txTelefone.getText());
			c.setCep(txCep.getText());
			c.setCidade(txCidade.getText());
			c.setBairro(txBairro.getText());
			c.setComplemento(txComplemento.getText());

			ClienteDAO dao = new ClienteDAO();
			dao.inserir(c);
			ListarCliente.pesquisar(modelo);

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