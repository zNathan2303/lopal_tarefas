package br.dev.nathan.tarefas.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.dev.nathan.tarefas.factory.FileFactory;
import br.dev.nathan.tarefas.model.Funcionario;
import br.dev.nathan.tarefas.utils.Utils;

public class FuncionarioDAO {

	private Funcionario funcionario;
	private FileFactory ff = new FileFactory();

	// Método construtor
	public FuncionarioDAO(Funcionario funcionario) {

		this.funcionario = funcionario;

	}

	public void gravar() {

		try {

			BufferedWriter bw = ff.getBufferedWriterFuncionarios();

			bw.write(funcionario.toString());
			bw.flush();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	public void apagar(int linhaASerApagada) {

		try {

			BufferedReader br = ff.getBufferedReaderFuncionarios();
			BufferedWriter bw = ff.getBufferedWriterFuncionarios();

			List<String> linhas = new ArrayList<>();
			String linha;
			int contador = 0;

			while ((linha = br.readLine()) != null) {
				if (contador != linhaASerApagada) {
					linhas.add(linha);
				}
				contador++;
			}
			
			for (String l : linhas) {
				bw.write(l);
				bw.newLine();
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	public List<Funcionario> showEmployees() {

		List<Funcionario> funcionarios = new ArrayList<>();

		try {
			BufferedReader br = ff.getBufferedReaderFuncionarios();
			String linha = br.readLine(); // LER A PRIMEIRA LINHA E POSICIONAR O PONTEIRO NA PROXIMA LINHA

			do {

				linha = br.readLine();
				// CONDIÇÃO TERNÁRIA = condição ? se verdadeiro : se falso
				String[] funcionario = linha != null ? linha.split(",") : null;

				Funcionario f = new Funcionario();
				f.setCodigo(funcionario[0]);
				f.setNome(funcionario[1]);
				f.setTelefone(funcionario[2]);
				f.setEmail(funcionario[3]);

				funcionarios.add(f);

			} while (linha != null);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return funcionarios;
	}

}
