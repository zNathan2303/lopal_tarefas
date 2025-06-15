package br.dev.nathan.tarefas.utils;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

// Classe responsável por limitar a quantidade de caracteres digitados em um textfield, 
// que precisa passar um numero inteiro como parametro para limitar as casas de caracteres.
// Precisa extender a classe PlainDocument, pois ela que é utilizada pelos elementos de texto
// do swing para o controle de conteúdo deles.
public class LimitaCaracteres extends PlainDocument {

	int limite;

	public LimitaCaracteres(int limite) {
		super();
		this.limite = limite;
	}

	// Sobrescreve o método padrão utilizado para inserir o que é digitado dentro
	// dos textfield
	@Override
	public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {

		// Pega a quantidade de caracteres já dentro do local mais a quantidade que será
		// digitada, e se for menor ou igual que o limite passado como parametro é
		// escrito o que será digitado, se não nada será escrito
		if ((getLength() + str.length()) <= limite) {
				super.insertString(offs, str, a);
		}

	}

}
