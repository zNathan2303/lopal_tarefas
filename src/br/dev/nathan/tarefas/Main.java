package br.dev.nathan.tarefas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.dev.nathan.tarefas.dao.FuncionarioDAO;
import br.dev.nathan.tarefas.model.Funcionario;
import br.dev.nathan.tarefas.model.Tarefa;

public class Main {
	
	// duas barras para não dar erro no encontro do arquivo (\ é chamada de scape)
	private static String path = "C:\\Users\\25132656\\tarefa\\tarefas.txt";

	public static void main(String[] args) {
		
		List<String> frutas = new ArrayList<>();
		List<Funcionario> funcionarios = new ArrayList<>();
		List<Double> numeros = new ArrayList<>();
		
		frutas.add("tomate");
		frutas.add("manga");
		frutas.add("morango");
		frutas.add("banana");
		
		numeros.add(12.5);
		numeros.add(1.3);
		numeros.add(45.7);
		
		Funcionario funcionario = new Funcionario();
		funcionario.setCodigo(4);
		funcionario.setNome("oi");
		funcionario.setMatricula("8888");
		funcionario.setEmail("ola@gmail.com");
		
		Funcionario funcionario2 = new Funcionario();
		funcionario2.setCodigo(7);
		funcionario2.setNome("fabio");
		funcionario2.setMatricula("4444");
		funcionario2.setEmail("fabio@gmail.com");
		
		funcionarios.addAll(List.of(funcionario, funcionario2, funcionario, funcionario));
		
		System.out.println(frutas);
		System.out.println(funcionarios);
		
		// para cada funcionario na lista atribuia na variavel f
		for (Funcionario f : funcionarios) {
			System.out.println(f.getNome() + " - " + f.getEmail());
		}
//		
//		FuncionarioDAO dao = new FuncionarioDAO(funcionario);
//		dao.gravar();
//		
//		System.out.println(funcionario.toString());
		
	}
	
	private static void gravarArquivo() {
		// file/arquivo
		FileWriter file = null;
		// writer/escritor
		BufferedWriter writer = null;
		
		try {
			
			// true ativa o modo append (adicionar)
			file = new FileWriter(path, true);
			writer = new BufferedWriter(file);
			
			writer.write("Essa é outra nova linha!!\n");
			
			// flush serve para não reescrever o arquivo
			// flush é descarga da memória ram para o hd
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
			 
			 // após ler uma linha ele passa para a próxima
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
