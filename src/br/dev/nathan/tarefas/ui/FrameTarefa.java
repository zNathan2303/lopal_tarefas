package br.dev.nathan.tarefas.ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.MaskFormatter;

import br.dev.nathan.tarefas.dao.FuncionarioDAO;
import br.dev.nathan.tarefas.dao.TarefaDAO;
import br.dev.nathan.tarefas.model.Funcionario;
import br.dev.nathan.tarefas.model.Status;
import br.dev.nathan.tarefas.model.Tarefa;
import br.dev.nathan.tarefas.utils.LimitadorTextField;
import br.dev.nathan.tarefas.utils.Utils;

public class FrameTarefa {

	private JLabel labelTitulo;
	private JTextField txtTitulo;
	private JLabel labelDescricao;
	private JTextField txtDescricao;
	private JLabel labelDataInicial;
	private JFormattedTextField txtDataInicial;
	private JLabel labelPrazo;
	private JTextField txtPrazo;
	private JLabel labelDataConclusao;
	private JFormattedTextField txtDataConclusao;
	private JLabel labelStatus;
	private JComboBox<Status> cboxStatus;
	private JLabel labelResponsavel;
	private JComboBox<String> cboxResponsavel;
	private JButton btnGravar;
	private JButton btnSair;

	private FrameListaTarefa frameLista;

	public FrameTarefa(JDialog telaLista, FrameListaTarefa frameLista) {
		this.frameLista = frameLista;
		criarTela(telaLista, frameLista);
	}

	private void criarTela(JDialog telaLista, FrameListaTarefa frameLista) {
		JDialog tela = new JDialog(telaLista, "Cadastro de tarefas", true);
		tela.setLayout(null);
		tela.setSize(400, 600);
		tela.setResizable(false);
		tela.setLocationRelativeTo(telaLista);
		// APOS CLICAR NO X FECHA A TELA
		tela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		Container painel = tela.getContentPane();
		DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

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

		// A class MaskFormatter exige que tenha alguma forma de lidar com o
		// ParseException, então é declarado a classe fora do try/catch para poder
		// usá-la fora dele depois
		MaskFormatter mascaraData = null;
		try {
			mascaraData = new MaskFormatter("##/##/####");
			mascaraData.setPlaceholderCharacter('_');

		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		txtDataInicial = new JFormattedTextField(mascaraData);
		txtDataInicial.setDocument(new LimitadorTextField(10));
		LocalDate dataAtual = LocalDate.now();
		LocalDate dataInicio = dataAtual;
		txtDataInicial.setText(dataInicio.format(formatoData));

		txtDataInicial.setHorizontalAlignment(JTextField.CENTER);
		txtDataInicial.setBounds(20, 180, 100, 30);

		labelPrazo = new JLabel("Prazo (dias):");
		labelPrazo.setBounds(20, 215, 200, 30);
		txtPrazo = new JTextField();
		txtPrazo.setDocument(new LimitadorTextField(3));
		txtPrazo.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				String numeros = "1234567890";
				if (!numeros.contains(e.getKeyChar() + "")) {
					e.consume();
				}
			}
		});
		txtPrazo.setBounds(20, 245, 150, 30);

		labelDataConclusao = new JLabel("Data conclusão:");
		labelDataConclusao.setBounds(20, 280, 200, 30);
		txtDataConclusao = new JFormattedTextField(mascaraData);
		txtDataConclusao.setEnabled(false);
		txtDataConclusao.setHorizontalAlignment(JTextField.CENTER);
		txtDataConclusao.setBounds(20, 310, 100, 30);

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
		for (Funcionario f : funcionarios) {
			cboxResponsavel.addItem(f.getNome());
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

		txtPrazo.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				atualizarDataConclusao(dataInicio, formatoData);
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				atualizarDataConclusao(dataInicio, formatoData);
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub

			}
		});

		btnGravar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (txtTitulo.getText().isEmpty() || txtDescricao.getText().isEmpty() || txtPrazo.getText().isEmpty()
						|| txtDataInicial.getText() == "__/__/____") {
					JOptionPane.showMessageDialog(tela, "Nenhum campo pode estar vazio!", "Erro",
							JOptionPane.ERROR_MESSAGE);

				} else {
					Tarefa tarefa = new Tarefa();
					tarefa.setCodigo(Utils.gerarUUID());
					tarefa.setTitulo(txtTitulo.getText());
					tarefa.setDescricao(txtDescricao.getText());

					String[] dadosDataInicio = txtDataInicial.getText().split("/");
					LocalDate dataInicio = LocalDate.now();
					dataInicio = dataInicio.withYear(Integer.valueOf(dadosDataInicio[2]))
							.withMonth(Integer.valueOf(dadosDataInicio[1]))
							.withDayOfMonth(Integer.valueOf(dadosDataInicio[0]));
					tarefa.setDataInicial(dataInicio);

					tarefa.setPrazo(Integer.valueOf(txtPrazo.getText()));

					String[] dadosDataConclusao = txtDataConclusao.getText().split("/");
					LocalDate dataConclusao = LocalDate.now();
					dataConclusao = dataConclusao.withYear(Integer.valueOf(dadosDataConclusao[2]))
							.withMonth(Integer.valueOf(dadosDataConclusao[1]))
							.withDayOfMonth(Integer.valueOf(dadosDataConclusao[0]));
					tarefa.setDataConclusao(dataConclusao);

					tarefa.setStatus((Status) cboxStatus.getSelectedItem());
					// Estrutura responsável por passar o funcionário selecionado na JComboBox
					String nome;
					String nomeSelecionado;
					List<Funcionario> funcionarios = fDao.showEmployees();
					for (Funcionario funcionario : funcionarios) {
						nome = funcionario.getNome();
						nomeSelecionado = String.valueOf(cboxResponsavel.getSelectedItem());
						if (nomeSelecionado.equals(nome)) {
							tarefa.setResponsavel(funcionario);
							TarefaDAO dao = new TarefaDAO(tarefa);
							dao.gravar();
						}
					}

					frameLista.atualizarTabela();

					JOptionPane.showMessageDialog(tela, "Gravado com sucesso!", "Sucesso",
							JOptionPane.INFORMATION_MESSAGE);
					limparFormulario();
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
					System.out.println(txtDataInicial.getText());
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

	private void atualizarDataConclusao(LocalDate dataInicio, DateTimeFormatter formatoData) {
		if (!txtPrazo.getText().isEmpty()) {
			long prazo = Integer.parseInt(txtPrazo.getText());
			txtDataConclusao.setText(dataInicio.plusDays(prazo).format(formatoData));
		}
	}

}
