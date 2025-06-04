package br.dev.nathan.tarefas.ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.dev.nathan.tarefas.model.Funcionario;

public class FrameTarefa {

	private JLabel labelCodigo;
	private JTextField txtCodigo;
	private JLabel labelNome;
	private JTextField txtNome;
	private JLabel labelDescricao;
	private JTextField txtDescricao;
	private JLabel labelDataInicial;
	private JTextField txtDataInicial;
	private JLabel labelPrazo;
	private JTextField txtPrazo;
	private JLabel labelDataFinal;
	private JTextField txtDataFinal;
	private JLabel labelFuncionario;
	private JComboBox<Funcionario> cboxFuncionario;
	private JButton btnGravar;
	private JButton btnSair;

	public FrameTarefa(JDialog telaLista) {
		criarTela(telaLista);
	}

	private void criarTela(JDialog telaLista) {
		JDialog tela = new JDialog(telaLista, "Atribuição", true);
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
		// Para o código não poder ser editado
		txtCodigo.setEnabled(false);

		labelNome = new JLabel("Nome:");
		labelNome.setBounds(20, 85, 200, 30);
		txtNome = new JTextField();
		txtNome.setBounds(20, 115, 350, 30);

		labelDescricao = new JLabel("Descrição:");
		labelDescricao.setBounds(20, 150, 200, 30);
		txtDescricao = new JTextField();
		txtDescricao.setBounds(20, 180, 200, 30);

		labelDataInicial = new JLabel("Data inicial:");
		labelDataInicial.setBounds(20, 215, 200, 30);
		txtDataInicial = new JTextField();
		txtDataInicial.setBounds(20, 245, 300, 30);

		btnGravar = new JButton("Salvar");
		btnGravar.setBounds(20, 290, 100, 40);

		btnSair = new JButton("Sair");
		btnSair.setBounds(130, 290, 100, 40);

		painel.add(labelCodigo);
		painel.add(txtCodigo);
		painel.add(labelNome);
		painel.add(txtNome);
		painel.add(labelDescricao);
		painel.add(txtDescricao);
		painel.add(labelDataInicial);
		painel.add(txtDataInicial);
		painel.add(btnGravar);
		painel.add(btnSair);
		
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
