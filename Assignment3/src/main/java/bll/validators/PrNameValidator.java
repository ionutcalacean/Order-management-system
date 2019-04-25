package bll.validators;

import javax.swing.JOptionPane;

import model.Product;

public class PrNameValidator implements Validator<Product> {

	private static final int MAX_LENGTH = 45;

	public void validate(Product t) {
		if (t.getProduct_name().length() > MAX_LENGTH) {
			JOptionPane.showMessageDialog(null,
				    "Numele produsului e prea lung!",
				    "Product name error",
				    JOptionPane.ERROR_MESSAGE);
			throw new IllegalArgumentException("Nume produs prea lung!");
		}
	}
}
