package br.dev.nathan.tarefas.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.dev.nathan.tarefas.factory.FileFactory;
import br.dev.nathan.tarefas.model.Funcionario;
import br.dev.nathan.tarefas.model.Status;
import br.dev.nathan.tarefas.model.Tarefa;

public class TarefaDAO {

	private Tarefa tarefa;
	private Funcionario funcionario;
	private FileFactory ff = new FileFactory();

	public TarefaDAO(Tarefa tarefa, Funcionario funcionario) {
		this.tarefa = tarefa;
		this.funcionario = funcionario;
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
	
	public List<Tarefa> showTasks() {
		
		List<Tarefa> tarefas = new ArrayList<>();

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
				t.setResponsavel(funcionario);

				tarefas.add(t);

			} while (linha != null);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return tarefas;
	}

}
