package bll.validators;

import javax.swing.JOptionPane;

import model.Product;

public class PrCantValidator implements Validator<Product>{
	
	private static final int MAX_CANT = 1000;

	public void validate(Product t) {
		if (t.getCantitate() > MAX_CANT || t.getCantitate() < 0) {
			JOptionPane.showMessageDialog(null,
				    "Cantitate invalida!",
				    "Cantity error",
				    JOptionPane.ERROR_MESSAGE);
			throw new IllegalArgumentException("Cantitate incorecta!");
		}
	}

}
