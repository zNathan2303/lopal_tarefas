package br.dev.nathan.tarefas.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.dev.nathan.tarefas.factory.FileFactory;
import br.dev.nathan.tarefas.model.Tarefa;

public class TarefaDAO {

	private Tarefa tarefa;
	private FileFactory ff = new FileFactory();

	public TarefaDAO(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

	public void gravar() {

		try {

			BufferedWriter bw = ff.getBufferedWriterTarefas();

			bw.write(tarefa.toString());
			bw.flush();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	public void apagar(int linhaASerApagada) {
		try {

			BufferedReader br = ff.getBufferedReaderTarefas();
			BufferedWriter bw = ff.getBufferedWriterTarefas();

			List<String> linhas = new ArrayList<>();
			String linha;
			int linhaIndex = 0;
			linhaASerApagada += 1;

			while ((linha = br.readLine()) != null) {
				if (linhaIndex != linhaASerApagada) {
					linhas.add(linha);
				}
				linhaIndex++;
			}

			ff.limparArquivoTarefas();

			for (String l : linhas) {

				bw.write(l);
				bw.flush();

				bw.newLine();
			}

			bw.write("");
			bw.flush();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public String buscarTarefa(int tarefaIndex) {

		String dadosTarefa = "";
		try {
			BufferedReader br = ff.getBufferedReaderTarefas();

			String linha;
			int linhaIndex = 0;
			tarefaIndex += 1;

			while ((linha = br.readLine()) != null) {

				if (linhaIndex == tarefaIndex) {
					dadosTarefa = linha;
				}
				linhaIndex++;
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return dadosTarefa;

	}

	public void alterar(int linhaASerModificada) {

		try {
			BufferedReader br = ff.getBufferedReaderFuncionarios();
			BufferedWriter bw = ff.getBufferedWriterFuncionarios();

			List<String> linhas = new ArrayList<>();
			String linha;
			int linhaIndex = 0;
			linhaASerModificada += 1;

			while ((linha = br.readLine()) != null) {

				if (linhaIndex != linhaASerModificada) {
					linhas.add(linha);
				} else {
					linhas.add(tarefa.toString());
				}
				linhaIndex++;
			}

			ff.limparArquivoTarefas();

			for (String l : linhas) {

				bw.write(l);
				bw.flush();

				bw.newLine();
			}

			bw.write("");
			bw.flush();
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public List<Tarefa> showTasks() {

		List<Tarefa> tarefas = new ArrayList<>();
		FuncionarioDAO dao = new FuncionarioDAO(null);

		try {
			BufferedReader br = ff.getBufferedReaderTarefas();
			String linha = br.readLine();

			do {

				linha = br.readLine();
				// CONDIÇÃO TERNÁRIA = condição ? se verdadeiro : se falso
				String[] tarefa = linha != null ? linha.split(",") : null;

				Tarefa t = new Tarefa();
				t.setCodigo(tarefa[0]);
				t.setTitulo(tarefa[1]);
				t.setResponsavel(dao.reconstruirFuncionario(tarefa[7], tarefa[8], tarefa[9], tarefa[10]));

				tarefas.add(t);

			} while (linha != null);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return tarefas;
	}

}
