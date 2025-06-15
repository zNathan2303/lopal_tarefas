package br.dev.nathan.tarefas.ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.dev.nathan.tarefas.dao.FuncionarioDAO;
import br.dev.nathan.tarefas.model.Funcionario;
import br.dev.nathan.tarefas.utils.Utils;

public class FrameFuncionario {

	private JLabel labelCodigo;
	private JLabel labelNome;
	private JLabel labelTelefone;
	private JLabel labelEmail;

	private JTextField txtCodigo;
	private JTextField txtNome;
	private JTextField txtTelefone;
	private JTextField txtEmail;

	private JButton btnSalvar;
	private JButton btnSair;

	private FrameListaFuncionario frameLista;

	// Método construtor não retorno nada e sempre é público
	public FrameFuncionario(JDialog telaLista, FrameListaFuncionario frameLista) {
		this.frameLista = frameLista;
		criarTela(telaLista);
	}

	private void criarTela(JDialog telaLista) {
		JDialog tela = new JDialog(telaLista, "Cadastro", true);
		tela.setLayout(null);
		tela.setSize(400, 400);
		tela.setResizable(false);
		tela.setLocationRelativeTo(telaLista);
		// Após clicar no X fecha a tela
		tela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		Container painel = tela.getContentPane();

		labelCodigo = new JLabel("Código:");
		labelCodigo.setBounds(20, 20, 200, 30);
		txtCodigo = new JTextField();
		txtCodigo.setBounds(20, 50, 200, 30);
		// Desativa o JTextField para ninguem colocar nada nele, pois já será obtido de
		// forma automática
		txtCodigo.setEnabled(false);

		labelNome = new JLabel("Nome:");
		labelNome.setBounds(20, 85, 200, 30);
		txtNome = new JTextField();
		txtNome.setBounds(20, 115, 350, 30);
		bloquearVirgula(txtNome);

		labelTelefone = new JLabel("Telefone:");
		labelTelefone.setBounds(20, 150, 200, 30);
		txtTelefone = new JTextField();
		txtTelefone.setBounds(20, 180, 200, 30);
		bloquearVirgula(txtTelefone);

		labelEmail = new JLabel("E-mail:");
		labelEmail.setBounds(20, 215, 200, 30);
		txtEmail = new JTextField();
		txtEmail.setBounds(20, 245, 300, 30);
		bloquearVirgula(txtEmail);

		btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(20, 290, 100, 40);

		btnSair = new JButton("Sair");
		btnSair.setBounds(130, 290, 100, 40);

		painel.add(labelCodigo);
		painel.add(txtCodigo);
		painel.add(labelNome);
		painel.add(txtNome);
		painel.add(labelTelefone);
		painel.add(txtTelefone);
		painel.add(labelEmail);
		painel.add(txtEmail);
		painel.add(btnSalvar);
		painel.add(btnSair);

		// Adicionar os ouvintes de ação dos botões

		btnSalvar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// Irá retornar um erro se algum dos campos está vazio, para evitar problemas de
				// linhas sem informações no arquivo
				if (txtNome.getText().isEmpty() || txtTelefone.getText().isEmpty() || txtEmail.getText().isEmpty()) {
					JOptionPane.showMessageDialog(tela, "Nenhum campo pode estar vazio!", "Erro",
							JOptionPane.ERROR_MESSAGE);
				} else {

					Funcionario funcionario = new Funcionario();
					funcionario.setCodigo(Utils.gerarUUID());
					funcionario.setNome(txtNome.getText());
					funcionario.setTelefone(txtTelefone.getText());
					funcionario.setEmail(txtEmail.getText());

					FuncionarioDAO dao = new FuncionarioDAO(funcionario);
					dao.gravar();

					frameLista.atualizarTabela();

					JOptionPane.showMessageDialog(tela, txtNome.getText() + " gravado com sucesso!", "Sucesso",
							JOptionPane.INFORMATION_MESSAGE);
					limparFormulario();

				}

			}
		});

		btnSair.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int resposta = JOptionPane.showConfirmDialog(tela, "Confirma a saída do cadastro do funcionário?", "Sair do sistema",
						JOptionPane.YES_NO_OPTION);

				if (resposta == 0) {
					tela.dispose();
				}

			}
		});

		tela.setVisible(true);
	}
	
	private void bloquearVirgula(JTextField localBloqueio) {
		
		localBloqueio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteresExcluidos = ",";
				if (caracteresExcluidos.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		
	}

	private void limparFormulario() {
		txtNome.setText(null);
		txtEmail.setText(null);
		txtTelefone.setText(null);
		// Manda o foco para o JTextField do nome, em que ele fica ativo para digitar de
		// forma automática
		txtNome.requestFocus();
	}

}
