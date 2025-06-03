package br.dev.nathan.tarefas.ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class FrameOpcoes {

	private JButton btnFuncionarios;
	private JButton btnTarefas;

	public FrameOpcoes() {
		criarTela();
	}

	private void criarTela() {

		JFrame telaOpoes = new JFrame();
		telaOpoes.setTitle("Selecione alguma opção");
		telaOpoes.setSize(335, 120);
		telaOpoes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		telaOpoes.setResizable(false);
		telaOpoes.setLayout(null);
		telaOpoes.setLocationRelativeTo(null);

		btnFuncionarios = new JButton("Funcionários");
		btnFuncionarios.setBounds(20, 20, 120, 40);

		btnTarefas = new JButton("Tarefas");
		btnTarefas.setBounds(180, 20, 120, 40);

		Container painel = telaOpoes.getContentPane();

		painel.add(btnFuncionarios);
		painel.add(btnTarefas);

		btnFuncionarios.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new FrameListaFuncionario(telaOpoes);

			}
		});

		btnTarefas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new FrameListaTarefa(telaOpoes);

			}
		});

		telaOpoes.setVisible(true);

	}

}
