package bll.validators;

import javax.swing.JOptionPane;

import model.Client;

public class ClientAgeValidator implements Validator<Client> {
	private static final int MIN_AGE=12;
    private static final int MAX_AGE=90;
    
    public void validate(Client t) {
    	if(t.getVarsta() <MIN_AGE || t.getVarsta()>MAX_AGE) {
    		JOptionPane.showMessageDialog(null,
				    "Varsta nu respecta limitele!",
				    "Age error",
				    JOptionPane.ERROR_MESSAGE);
    		throw new IllegalArgumentException("Varsta clientului nu respecta limitele");
    	}
    }
}
