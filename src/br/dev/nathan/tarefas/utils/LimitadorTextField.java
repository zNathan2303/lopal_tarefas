package br.dev.nathan.tarefas.utils;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class LimitadorTextField extends PlainDocument {
	
	int limite;

	public LimitadorTextField(int limite) {
		super();
		this.limite = limite;
	}

	@Override
	public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {

		if ((getLength() + str.length()) <= limite) {
			super.insertString(offs, str, a);
		}
		
	}
	
}
