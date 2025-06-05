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

public class FrameAlterarFuncionario {

	private JLabel labelCodigo;
	private JLabel labelNome;
	private JLabel labelTelefone;
	private JLabel labelEmail;

	private JTextField txtCodigo;
	private JTextField txtNome;
	private JTextField txtTelefone;
	private JTextField txtEmail;

	private JButton btnAlterar;
	private JButton btnSair;

	private FrameListaFuncionario frameLista;

	public FrameAlterarFuncionario(JDialog telaLista, FrameListaFuncionario frameLista, int funcionarioIndex) {
		this.frameLista = frameLista;
		criarTela(telaLista, funcionarioIndex);
	}

	private void criarTela(JDialog telaLista, int funcionarioIndex) {
		JDialog tela = new JDialog(telaLista, "Alteração de dados", true);
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

		labelNome = new JLabel("Nome:");
		labelNome.setBounds(20, 85, 200, 30);
		txtNome = new JTextField(dadosFuncionario[1]);
		txtNome.setBounds(20, 115, 350, 30);

		labelTelefone = new JLabel("Telefone:");
		labelTelefone.setBounds(20, 150, 200, 30);
		txtTelefone = new JTextField(dadosFuncionario[2]);
		txtTelefone.setBounds(20, 180, 200, 30);

		labelEmail = new JLabel("E-mail:");
		labelEmail.setBounds(20, 215, 200, 30);
		txtEmail = new JTextField(dadosFuncionario[3]);
		txtEmail.setBounds(20, 245, 300, 30);

		btnAlterar = new JButton("Alterar");
		btnAlterar.setBounds(20, 290, 100, 40);

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
		painel.add(btnAlterar);
		painel.add(btnSair);

		btnAlterar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (txtNome.getText().isEmpty() || txtTelefone.getText().isEmpty() || txtEmail.getText().isEmpty()) {
					JOptionPane.showMessageDialog(tela, txtNome.getText() + "Nenhum campo pode estar vazio!", "Erro",
							JOptionPane.INFORMATION_MESSAGE);
				} else {

					int resposta = JOptionPane.showConfirmDialog(tela, "Deseja alterar os dados desse funcionário?",
							"Alterar dados", JOptionPane.YES_NO_OPTION);

					if (resposta == 0) {

						Funcionario funcionario = new Funcionario();
						funcionario.setCodigo(txtCodigo.getText());
						funcionario.setNome(txtNome.getText());
						funcionario.setTelefone(txtTelefone.getText());
						funcionario.setEmail(txtEmail.getText());

						FuncionarioDAO dao = new FuncionarioDAO(funcionario);
						dao.alterar(funcionarioIndex);

						frameLista.atualizarTabela();

						JOptionPane.showMessageDialog(tela, txtNome.getText() + " alterado com sucesso!", "Sucesso",
								JOptionPane.INFORMATION_MESSAGE);

						tela.dispose();
					}

				}

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

}
