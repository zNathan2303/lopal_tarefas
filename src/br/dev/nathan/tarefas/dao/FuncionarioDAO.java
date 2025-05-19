package br.dev.nathan.tarefas.dao;

import java.io.BufferedWriter;
import java.io.IOException;

import br.dev.nathan.tarefas.factory.FileFactory;
import br.dev.nathan.tarefas.model.Funcionario;

public class FuncionarioDAO {

	private Funcionario funcionario;

	// Método construtor
	public FuncionarioDAO(Funcionario funcionario) {

		this.funcionario = funcionario;

	}
	
	public void gravar() {
		
		FileFactory ff = new FileFactory();
		
		try {
			
			BufferedWriter bw = ff.getBufferedWriter();
			
			bw.write(funcionario.toString());
			bw.flush();
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void showEmployees() {
		
	}

}
