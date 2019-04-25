package bll.validators;

import javax.swing.JOptionPane;

import model.Client;

public class AdresaValidator implements Validator<Client>{
	private static final int MAX_LENGTH=45;
	
	public void validate(Client t) {
		if(t.getAdresa().length()>MAX_LENGTH) {
			JOptionPane.showMessageDialog(null,
				    "Adresa e prea lunga!",
				    "Address error",
				    JOptionPane.ERROR_MESSAGE);
			throw new IllegalArgumentException("Adresa prea lunga!");
		}
	}



}
