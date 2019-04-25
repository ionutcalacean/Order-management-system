package bll.validators;

import javax.swing.JOptionPane;

import model.Client;

public class NameValidator implements Validator<Client>{
	
	private static final int MAX_LENGTH=45;
	
	public void validate(Client t) {
		if(t.getName().length()>MAX_LENGTH) {
			JOptionPane.showMessageDialog(null,
				    "Numele e prea lung!",
				    "Name error",
				    JOptionPane.ERROR_MESSAGE);
			throw new IllegalArgumentException("Nume prea lung!");
		}
	}

}
