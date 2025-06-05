package br.dev.nathan.tarefas.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.dev.nathan.tarefas.factory.FileFactory;
import br.dev.nathan.tarefas.model.Funcionario;

public class FuncionarioDAO {

	private Funcionario funcionario;
	private FileFactory ff = new FileFactory();

	// Método construtor
	public FuncionarioDAO(Funcionario funcionario) {

		this.funcionario = funcionario;

	}

	// Método responsável por gravar funcionários no sistema
	public void gravar() {

		try {

			BufferedWriter bw = ff.getBufferedWriterFuncionarios();

			bw.write(funcionario.toString());
			bw.flush();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	// Método responsável por apagar funcionários do sistema
	public void apagar(int linhaASerApagada) {

		try {

			BufferedReader br = ff.getBufferedReaderFuncionarios();
			BufferedWriter bw = ff.getBufferedWriterFuncionarios();

			List<String> linhas = new ArrayList<>();
			String linha;
			int linhaIndex = 0;
			linhaASerApagada += 1;

			// Irá adicionar as linhas do arquivo na List linhas, enquanto verifica se a
			// linha lida é nula, para fazer o while funcionar enquanto não chegar no final
			// do arquivo (em que a linha é nula)
			while ((linha = br.readLine()) != null) {
				// Fazer uma exceção para não adicionar a linhaASerApagada (que representa os
				// dados do funcionário em questão) na List
				if (linhaIndex != linhaASerApagada) {
					linhas.add(linha);
				}
				linhaIndex++;
			}

			// Chama o método de reset, que já limpa o arquivo de forma automática,
			// permitindo que os dados que foram passados para o List linhas possam entrar
			// no arquivo vazio
			ff.resetArquivoFuncionarios();

			// Passa os dados salvos no List linhas para o arquivo
			for (String l : linhas) {
				// Write e flush servem para escrever e mandar da memória RAM para o arquivo,
				// respectivamente
				bw.write(l);
				bw.flush();
				// Coloca o "ponteiro" na linha abaixo
				bw.newLine();
			}

			// Após todo os dados terem sido colocados no arquivo, o último newLine não irá
			// criar uma nova linha sozinho, então é necessário escrever algo vazio ("") e
			// mandá-lo para o arquivo
			bw.write("");
			bw.flush();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	// Método responsável por retornar dados de algum funcionario selecionado na
	// tabela
	public String buscarFuncionario(int funcionarioIndex) {

		String dadosFuncionario = "";
		try {
			BufferedReader br = ff.getBufferedReaderFuncionarios();

			String linha;
			int linhaIndex = 0;
			funcionarioIndex += 1;

			while ((linha = br.readLine()) != null) {

				if (linhaIndex == funcionarioIndex) {
					dadosFuncionario = linha;
				}
				linhaIndex++;
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return dadosFuncionario;

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
					linhas.add(funcionario.toString());
				}
				linhaIndex++;
			}

			
			ff.resetArquivoFuncionarios();

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

	public List<Funcionario> showEmployees() {

		List<Funcionario> funcionarios = new ArrayList<>();

		try {
			BufferedReader br = ff.getBufferedReaderFuncionarios();
			// Já chama a leitura de linha do br, mesmo apenas atribuindo para uma variável.
			// O motivo é para pular a primeira linha (títulos das colunas) e já posicionar
			// o "ponteiro" na próxima linha
			String linha = br.readLine();

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
