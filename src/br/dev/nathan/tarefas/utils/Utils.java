package br.dev.nathan.tarefas.utils;

import java.util.UUID;

public class Utils {
	
	//UUID gera uma string
	public static String gerarUUID() {
		
		UUID uuid = UUID.randomUUID();
		// esse toString vai pegar o uuid e vai transformar em uma string
		String uuidStr = uuid.toString().substring(0, 8);
		
		return uuidStr;
		
	}

}
