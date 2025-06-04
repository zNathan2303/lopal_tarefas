package br.dev.nathan.tarefas.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.dev.nathan.tarefas.factory.FileFactory;
import br.dev.nathan.tarefas.model.Funcionario;
import br.dev.nathan.tarefas.model.Tarefa;

public class TarefaDAO {

	private Tarefa tarefa;
	private FileFactory ff = new FileFactory();

	public TarefaDAO(Tarefa tarefa) {
		this.tarefa = tarefa;
	}

	/*
	 * public void gravar() {
	 * 
	 * try {
	 * 
	 * BufferedWriter bw = ff.getBufferedWriterTarefas();
	 * 
	 * bw.write(tarefa.toString()); bw.flush();
	 * 
	 * } catch (IOException e) { System.out.println(e.getMessage()); }
	 * 
	 * }
	 * 
	 * public List<Tarefa> showTasks() {
	 * 
	 * List<Tarefa> tarefas = new ArrayList<>();
	 * 
	 * try { BufferedReader br = ff.getBufferedReaderTarefas(); String linha =
	 * br.readLine(); // LER A PRIMEIRA LINHA E POSICIONAR O PONTEIRO NA PROXIMA
	 * LINHA
	 * 
	 * do {
	 * 
	 * linha = br.readLine(); // CONDIÇÃO TERNÁRIA = CONDIÇÃO ? SE VERDADEIRO : SE
	 * FALSO String[] tarefa = linha != null ? linha.split(",") : null;
	 * 
	 * Tarefa t = new Tarefa(); t.setCodigo(tarefa[0]); t.setTitulo(tarefa[1]);
	 * t.setDescricao(tarefa[2]);
	 * 
	 * t.setDataInicial(tarefa[3]); t.setPrazo(tarefa[4]);
	 * t.setDataConclusao(tarefa[5]); t.setStatus(tarefa[6]);
	 * t.setResponsavel(tarefa[7]);
	 * 
	 * 
	 * tarefas.add(t);
	 * 
	 * } while (linha != null);
	 * 
	 * } catch (Exception e) { System.out.println(e.getMessage()); }
	 * 
	 * return tarefas; }
	 */

}
