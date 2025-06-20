package br.dev.nathan.tarefas.ui;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.dev.nathan.tarefas.dao.FuncionarioDAO;
import br.dev.nathan.tarefas.model.Funcionario;

public class FrameListaFuncionario {

	private JLabel labelTitulo;
	private DefaultTableModel modeloLista;
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

		modeloLista = new DefaultTableModel() {

			// Sobrescreve o método isCellEditable da classe DefaultTableModel para sempre
			// retornar false, fazendo com que as células da tabela não possam ser editadas
			@Override
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;
			}

		};

		atualizarTabela();

		tableFuncionarios = new JTable(modeloLista);

		// Bloqueia o usuário de reordenar as colunas, pegando o header da tabela e
		// definindo se o usuário pode reordenar ou não
		tableFuncionarios.getTableHeader().setReorderingAllowed(false);

		// Faz o clique do usuário selecionar a linha, e não a célula individual
		tableFuncionarios.setFocusable(false);
		
		tableFuncionarios.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					new FrameInfoFuncionario(tela, tableFuncionarios.getSelectedRow());
				}
			}
		});

		// Adiciona a tabela a um painel com scroll
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
				// Passa a instância atual do FrameListaFuncionario. E o .this é necessário para
				// fazer referencia a classe FrameListaFuncionario
				new FrameFuncionario(tela, FrameListaFuncionario.this);

			}
		});

		btnExcluir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (tableFuncionarios.isRowSelected(tableFuncionarios.getSelectedRow())) {
					int resposta = JOptionPane.showConfirmDialog(tela, "Deseja excluir o funcionário cadastrado?",
							"Exclusão de funcionário", JOptionPane.YES_NO_OPTION);

					// Forma de obter a quantidade de linhas selecionadas:
					// tableFuncionarios.getSelectedRowCount();

					if (resposta == 0) {
						int excluirLinha = tableFuncionarios.getSelectedRow();

						FuncionarioDAO dao = new FuncionarioDAO(null);
						dao.apagar(excluirLinha);
						atualizarTabela();

					}
				} else {
					JOptionPane.showMessageDialog(tela, "É necessário selecionar alguma linha para excluir!", "Erro",
							JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});

		btnAlterar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (tableFuncionarios.isRowSelected(tableFuncionarios.getSelectedRow())) {

					new FrameAlterarFuncionario(tela, FrameListaFuncionario.this, tableFuncionarios.getSelectedRow());

				} else {
					JOptionPane.showMessageDialog(tela, "Precisa selecionar alguma linha para alterar!", "Erro",
							JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});

		btnSair.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				tela.dispose();

			}
		});

		tela.setVisible(true);

	}

	// Atribui os dados e as colunas que a tabela terá
	public void atualizarTabela() {

		// Criação da tabela
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

		modeloLista.setDataVector(dados, colunas);

	}

}
