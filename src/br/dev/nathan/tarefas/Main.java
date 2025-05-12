package br.dev.nathan.tarefas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import br.dev.nathan.tarefas.model.Funcionario;
import br.dev.nathan.tarefas.model.Tarefa;

public class Main {
	
	private static String path = "C:\\Users\\25132656\\tarefa\\tarefas.txt";

	public static void main(String[] args) {
		
		Tarefa f = new Tarefa();
		
		gravarArquivo();
		
		lerArquivo();
		
	}
	
	private static void gravarArquivo() {
		FileWriter file = null;
		BufferedWriter writer = null;
		
		try {
			
			// true ativa o modo append (adicionar)
			file = new FileWriter(path, true);
			writer = new BufferedWriter(file);
			
			writer.write("Essa é outra nova linha!!\n");
			
			// flush serve para não reescrever o arquivo
			writer.flush();
			
		} catch (Exception erro) {
			System.out.println(erro.getMessage());
		}
		
	}

	private static void lerArquivo() {
		
		// abrir o arquivo para leitura
		FileReader file = null;
		BufferedReader buffer = null;
		try {
			 file = new FileReader(path);
			 buffer = new BufferedReader(file);
			 
			 String linha = buffer.readLine();
			 
			 while (linha != null) {
				 System.out.println(linha);
				 linha = buffer.readLine();
			 }
			 
		} catch (FileNotFoundException erro) {
			System.out.println("Arquivo não achado!");
			System.out.println(erro.getMessage());
		} catch (IOException erro) {
			System.out.println("Você não pode ler o arquivo!");
			System.out.println(erro.getMessage());
		} catch (Exception erro) {
			System.out.println("Erro genérico!");
			System.out.println(erro.getMessage());
		}
	}

}
