package br.dev.nathan.tarefas.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import br.dev.nathan.tarefas.dao.FuncionarioDAO;

public class FrameInfoFuncionario {

	private JLabel labelCodigo;
	private JLabel labelNome;
	private JLabel labelTelefone;
	private JLabel labelEmail;

	private JTextField txtCodigo;
	private JTextField txtNome;
	private JTextField txtTelefone;
	private JTextField txtEmail;

	private JButton btnSair;

	public FrameInfoFuncionario(JDialog telaLista, int funcionarioIndex) {
		criarTela(telaLista, funcionarioIndex);
	}

	private void criarTela(JDialog telaLista, int funcionarioIndex) {
		JDialog tela = new JDialog(telaLista, "Dados do funcionário", true);
		tela.setLayout(null);
		tela.setSize(400, 400);
		tela.setResizable(false);
		tela.setLocationRelativeTo(telaLista);
		tela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		Container painel = tela.getContentPane();

		FuncionarioDAO dao = new FuncionarioDAO(null);
		String[] dadosFuncionario = dao.buscarFuncionario(funcionarioIndex).split(",");

		labelCodigo = new JLabel("Código:");
		labelCodigo.setBounds(20, 20, 200, 30);
		txtCodigo = new JTextField(dadosFuncionario[0]);
		txtCodigo.setBounds(20, 50, 200, 30);
		txtCodigo.setEnabled(false);
		txtCodigo.setDisabledTextColor(Color.BLACK);

		labelNome = new JLabel("Nome:");
		labelNome.setBounds(20, 85, 200, 30);
		txtNome = new JTextField(dadosFuncionario[1]);
		txtNome.setBounds(20, 115, 350, 30);
		txtNome.setEnabled(false);
		txtNome.setDisabledTextColor(Color.BLACK);

		labelTelefone = new JLabel("Telefone:");
		labelTelefone.setBounds(20, 150, 200, 30);
		txtTelefone = new JTextField(dadosFuncionario[2]);
		txtTelefone.setBounds(20, 180, 200, 30);
		txtTelefone.setEnabled(false);
		txtTelefone.setDisabledTextColor(Color.BLACK);

		labelEmail = new JLabel("E-mail:");
		labelEmail.setBounds(20, 215, 200, 30);
		txtEmail = new JTextField(dadosFuncionario[3]);
		txtEmail.setBounds(20, 245, 300, 30);
		txtEmail.setEnabled(false);
		txtEmail.setDisabledTextColor(Color.BLACK);

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
		painel.add(btnSair);

		btnSair.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tela.dispose();
			}
		});

		tela.setVisible(true);
	}

}
