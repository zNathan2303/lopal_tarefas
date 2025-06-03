package br.dev.nathan.tarefas.ui;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import br.dev.nathan.tarefas.dao.FuncionarioDAO;
import br.dev.nathan.tarefas.model.Funcionario;

public class FrameListaFuncionario {

	private JLabel labelTitulo;
	private JTable tableFuncionarios;
	private JScrollPane scrollFuncionarios;
	private JButton btnNovo;
	private JButton btnExcluir;
	private JButton btnAlterar;
	private JButton btnSair;

	private Font fontTitulo = new Font("Arial", Font.BOLD, 26);

	public FrameListaFuncionario(JFrame telaOpcoes) {
		criarTela(telaOpcoes);
	}

	private void criarTela(JFrame telaOpcoes) {
		JDialog tela = new JDialog(telaOpcoes, "Cadastro de funcionários", true);
		tela.setSize(605, 480);
		tela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		tela.setResizable(false);
		tela.setLayout(null);
		tela.setLocationRelativeTo(null);

		Container painel = tela.getContentPane();

		labelTitulo = new JLabel("Cadastro de funcionários");
		labelTitulo.setBounds(20, 20, 500, 30);
		labelTitulo.setFont(fontTitulo);

		btnNovo = new JButton("Cadastrar");
		btnExcluir = new JButton("Excluir");
		btnAlterar = new JButton("Alterar");
		btnSair = new JButton("Sair");

		// CRIAÇÃO DA TABELA
		String[] colunas = new String[3];
		colunas[0] = "Código";
		colunas[1] = "Nome";
		colunas[2] = "Email";

		// Obter lista de funcionários
		FuncionarioDAO dao = new FuncionarioDAO(null);
		List<Funcionario> funcionarios = dao.showEmployees();
		Object[][] dados = new Object[funcionarios.size()][3];

		int linha = 0;
		for (Funcionario f : funcionarios) {
			dados[linha][0] = f.getCodigo();
			dados[linha][1] = f.getNome();
			dados[linha][2] = f.getEmail();
			linha++;
		}

		tableFuncionarios = new JTable(dados, colunas);

		// Bloqueia o usuário de reordenar as colunas, pegando o header da tabela e
		// definindo se o usuário pode reorganizar ou não
		tableFuncionarios.getTableHeader().setReorderingAllowed(false);
		
		

		scrollFuncionarios = new JScrollPane(tableFuncionarios);

		scrollFuncionarios.setBounds(20, 70, 550, 300);
		btnNovo.setBounds(20, 380, 130, 40);
		btnExcluir.setBounds(160, 380, 130, 40);
		btnAlterar.setBounds(300, 380, 130, 40);
		btnSair.setBounds(440, 380, 130, 40);

		painel.add(labelTitulo);
		painel.add(scrollFuncionarios);
		painel.add(btnNovo);
		painel.add(btnExcluir);
		painel.add(btnAlterar);
		painel.add(btnSair);

		btnNovo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new FrameFuncionario(tela);

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
