package br.dev.nathan.tarefas.ui;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
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

}
