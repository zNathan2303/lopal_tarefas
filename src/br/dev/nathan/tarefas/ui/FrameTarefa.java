package br.dev.nathan.tarefas.ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.dev.nathan.tarefas.dao.FuncionarioDAO;
import br.dev.nathan.tarefas.dao.TarefaDAO;
import br.dev.nathan.tarefas.model.Funcionario;
import br.dev.nathan.tarefas.model.Status;
import br.dev.nathan.tarefas.model.Tarefa;
import br.dev.nathan.tarefas.utils.Utils;

public class FrameTarefa {

	private JLabel labelTitulo;
	private JTextField txtTitulo;
	private JLabel labelDescricao;
	private JTextField txtDescricao;
	private JLabel labelDataInicial;
	private JTextField txtDataInicial;
	private JLabel labelPrazo;
	private JTextField txtPrazo;
	private JLabel labelDataConclusao;
	private JTextField txtDataConclusao;
	private JLabel labelStatus;
	private JComboBox<Status> cboxStatus;
	private JLabel labelResponsavel;
	private JComboBox<String> cboxResponsavel;
	private JButton btnGravar;
	private JButton btnSair;

	public FrameTarefa(JDialog telaLista) {
		criarTela(telaLista);
	}

	private void criarTela(JDialog telaLista) {
		JDialog tela = new JDialog(telaLista, "Cadastro de tarefas", true);
		tela.setLayout(null);
		tela.setSize(400, 600);
		tela.setResizable(false);
		tela.setLocationRelativeTo(telaLista);
		// APOS CLICAR NO X FECHA A TELA
		tela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		Container painel = tela.getContentPane();

		labelTitulo = new JLabel("Título:");
		labelTitulo.setBounds(20, 20, 200, 30);
		txtTitulo = new JTextField();
		txtTitulo.setBounds(20, 50, 200, 30);

		labelDescricao = new JLabel("Descrição:");
		labelDescricao.setBounds(20, 85, 200, 30);
		txtDescricao = new JTextField();
		txtDescricao.setBounds(20, 115, 350, 30);

		labelDataInicial = new JLabel("Data inicial:");
		labelDataInicial.setBounds(20, 150, 200, 30);
		txtDataInicial = new JTextField();
		txtDataInicial.setBounds(20, 180, 150, 30);

		labelPrazo = new JLabel("Prazo:");
		labelPrazo.setBounds(20, 215, 200, 30);
		txtPrazo = new JTextField();
		txtPrazo.setBounds(20, 245, 150, 30);

		labelDataConclusao = new JLabel("Data conclusão:");
		labelDataConclusao.setBounds(20, 280, 200, 30);
		txtDataConclusao = new JTextField();
		txtDataConclusao.setBounds(20, 310, 150, 30);

		labelStatus = new JLabel("Status:");
		labelStatus.setBounds(20, 345, 200, 30);
		cboxStatus = new JComboBox<Status>();
		for (Status status : Status.values()) {
			cboxStatus.addItem(status);
		}
		cboxStatus.setBounds(20, 375, 200, 30);

		labelResponsavel = new JLabel("Responsável:");
		labelResponsavel.setBounds(20, 410, 200, 30);
		cboxResponsavel = new JComboBox<String>();
		cboxResponsavel.setBounds(20, 440, 250, 30);
		FuncionarioDAO fDao = new FuncionarioDAO(null);
		List<Funcionario> funcionarios = fDao.showEmployees();

		int linha = 0;
		for (Funcionario f : funcionarios) {
			cboxResponsavel.addItem(f.getNome());
			linha++;
		}

		btnGravar = new JButton("Salvar");
		btnGravar.setBounds(20, 490, 100, 40);

		btnSair = new JButton("Sair");
		btnSair.setBounds(130, 490, 100, 40);

		painel.add(labelTitulo);
		painel.add(txtTitulo);
		painel.add(labelDescricao);
		painel.add(txtDescricao);
		painel.add(labelDataInicial);
		painel.add(txtDataInicial);
		painel.add(labelPrazo);
		painel.add(txtPrazo);
		painel.add(labelDataConclusao);
		painel.add(txtDataConclusao);
		painel.add(labelStatus);
		painel.add(cboxStatus);
		painel.add(labelResponsavel);
		painel.add(cboxResponsavel);
		painel.add(btnGravar);
		painel.add(btnSair);

		btnGravar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Tarefa tarefa = new Tarefa();
				tarefa.setCodigo(Utils.gerarUUID());
				tarefa.setTitulo(txtTitulo.getText());
				tarefa.setDescricao(txtDescricao.getText());
				tarefa.setDataInicial(null);
				tarefa.setPrazo(1);
				tarefa.setDataConclusao(null);
				tarefa.setStatus((Status) cboxStatus.getSelectedItem());
				// Estrutura responsável por passar o funcionário selecionado na JComboBox
				int linha = 0;
				String nome;
				for (Funcionario funcionario : funcionarios) {
					nome = funcionario.getNome();
					if (cboxResponsavel.getSelectedItem() == nome) {
						tarefa.setResponsavel(funcionario);
						TarefaDAO dao = new TarefaDAO(tarefa, funcionario);
						dao.gravar();
					}
					linha++;
				}
				
//				tarefa.setDataInicial(txtDataInicial.getText());
//				tarefa.setPrazo(txtPrazo.getText());
//				tarefa.setDataConclusao(txtDataConclusao.getText());
				
				JOptionPane.showMessageDialog(tela, "Gravado com sucesso!", "Sucesso",
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
		txtTitulo.setText(null);
		txtDescricao.setText(null);
		txtDataInicial.setText(null);
		txtPrazo.setText(null);
		txtDataConclusao.setText(null);
		cboxStatus.setSelectedItem(null);
		cboxResponsavel.setSelectedItem(null);
	}

}
