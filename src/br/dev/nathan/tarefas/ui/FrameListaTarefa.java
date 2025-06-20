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
import javax.swing.table.DefaultTableModel;

import br.dev.nathan.tarefas.dao.TarefaDAO;
import br.dev.nathan.tarefas.model.Funcionario;
import br.dev.nathan.tarefas.model.Tarefa;

public class FrameListaTarefa {

	private JLabel labelTitulo;
	private DefaultTableModel modeloLista;
	private JTable tableTarefas;
	private JScrollPane scrollTarefas;
	private JButton btnNovo;
	private JButton btnExcluir;
	private JButton btnAlterar;
	private JButton btnSair;

	private Font fontTitulo = new Font("Arial", Font.BOLD, 26);

	public FrameListaTarefa(JFrame telaOpcoes) {
		criarTela(telaOpcoes);
	}

	private void criarTela(JFrame telaOpcoes) {

		JDialog tela = new JDialog(telaOpcoes, "Lista de Tarefas", true);
		tela.setSize(605, 480);
		tela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		tela.setResizable(false);
		tela.setLayout(null);
		tela.setLocationRelativeTo(null);

		Container painel = tela.getContentPane();

		labelTitulo = new JLabel("Atribuição de tarefas");
		labelTitulo.setBounds(20, 20, 500, 30);
		labelTitulo.setFont(fontTitulo);

		btnNovo = new JButton("Atribuir");
		btnExcluir = new JButton("Excluir");
		btnAlterar = new JButton("Alterar");
		btnSair = new JButton("Sair");

		modeloLista = new DefaultTableModel();

		atualizarTabela();

		tableTarefas = new JTable(modeloLista) {
			@Override
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;
			}
		};

		tableTarefas.getTableHeader().setReorderingAllowed(false);
		tableTarefas.setFocusable(false);

		scrollTarefas = new JScrollPane(tableTarefas);

		scrollTarefas.setBounds(20, 70, 550, 300);
		btnNovo.setBounds(20, 380, 130, 40);
		btnExcluir.setBounds(160, 380, 130, 40);
		btnAlterar.setBounds(300, 380, 130, 40);
		btnSair.setBounds(440, 380, 130, 40);

		painel.add(labelTitulo);
		painel.add(scrollTarefas);
		painel.add(btnNovo);
		painel.add(btnExcluir);
		painel.add(btnAlterar);
		painel.add(btnSair);

		btnNovo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new FrameTarefa(tela, FrameListaTarefa.this);

			}
		});
		
		btnExcluir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (tableTarefas.isRowSelected(tableTarefas.getSelectedRow())) {
					int resposta = JOptionPane.showConfirmDialog(tela, "Deseja excluir a tarefa?",
							"Excluir tarefa", JOptionPane.YES_NO_OPTION);

					if (resposta == 0) {
						int excluirLinha = tableTarefas.getSelectedRow();

						TarefaDAO dao = new TarefaDAO(null);
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
				if (tableTarefas.isRowSelected(tableTarefas.getSelectedRow())) {
					new FrameAlterarTarefa(tela, FrameListaTarefa.this, tableTarefas.getSelectedRow());
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

	public void atualizarTabela() {
		String[] colunas = new String[3];
		colunas[0] = "Código";
		colunas[1] = "Nome";
		colunas[2] = "Responsável";

		TarefaDAO dao = new TarefaDAO(null);
		List<Tarefa> tarefas = dao.showTasks();
		Object[][] dados = new Object[tarefas.size()][3];

		int linha = 0;
		for (Tarefa t : tarefas) {
			dados[linha][0] = t.getCodigo();
			dados[linha][1] = t.getTitulo();
			Funcionario funcionario = t.getResponsavel();
			dados[linha][2] = funcionario.getNome();
			linha++;
		}

		modeloLista.setDataVector(dados, colunas);
	}

}
