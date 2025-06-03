package br.dev.nathan.tarefas.ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

	// METODO CONSTRUTOR NÃO RETORNA NADA E SEMPRE É PUBLICO
	public FrameFuncionario(JDialog telaLista) {
		criarTela(telaLista);
	}

	private void criarTela(JDialog telaLista) {
		JDialog tela = new JDialog(telaLista, "Cadastro", true);
		tela.setLayout(null);
		tela.setSize(400, 400);
		tela.setResizable(false);
		tela.setLocationRelativeTo(telaLista);
		// APOS CLICAR NO X FECHA A TELA
		tela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		Container painel = tela.getContentPane();

		labelCodigo = new JLabel("Código:");
		labelCodigo.setBounds(20, 20, 200, 30);
		txtCodigo = new JTextField();
		txtCodigo.setBounds(20, 50, 200, 30);
		// PARA A PESSOA NÃO PODER MEXER NA LABEL DO CODIGO
		txtCodigo.setEnabled(false);

		labelNome = new JLabel("Nome:");
		labelNome.setBounds(20, 85, 200, 30);
		txtNome = new JTextField();
		txtNome.setBounds(20, 115, 350, 30);

		labelTelefone = new JLabel("Telefone:");
		labelTelefone.setBounds(20, 150, 200, 30);
		txtTelefone = new JTextField();
		txtTelefone.setBounds(20, 180, 200, 30);

		labelEmail = new JLabel("E-mail:");
		labelEmail.setBounds(20, 215, 200, 30);
		txtEmail = new JTextField();
		txtEmail.setBounds(20, 245, 300, 30);

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
				Funcionario funcionario = new Funcionario();
				funcionario.setCodigo(Utils.gerarUUID());
				funcionario.setNome(txtNome.getText());
				funcionario.setTelefone(txtTelefone.getText());
				funcionario.setEmail(txtEmail.getText());

				FuncionarioDAO dao = new FuncionarioDAO(funcionario);
				dao.gravar();

				JOptionPane.showMessageDialog(tela, txtNome.getText() + " gravado com sucesso!", "Sucesso",
						JOptionPane.INFORMATION_MESSAGE);
				limparFormulario();

			}
		});

		btnSair.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int resposta = JOptionPane.showConfirmDialog(tela, "Confirma a saída do sistema?", "Sair do sistema",
						JOptionPane.YES_NO_OPTION);

				if (resposta == 0) {
					tela.dispose();
				}

			}
		});

		tela.setVisible(true);
	}

	private void limparFormulario() {
		txtNome.setText(null);
		txtEmail.setText(null);
		txtTelefone.setText(null);
		// MANDA O PONTEIRO PARA A TXTFIELD NOME, QUE O FOCO VAI PARA ELE
		txtNome.requestFocus();
	}

}
