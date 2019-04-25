package bll.validators;

import javax.swing.JOptionPane;

import model.Product;

public class PrPriceValidator implements Validator<Product>{
	

	public void validate(Product t) {
		if (t.getPrice() < 0) {
			JOptionPane.showMessageDialog(null,
				    "Pretul produsului e mai mic decat 0!",
				    "Product price error",
				    JOptionPane.ERROR_MESSAGE);
			throw new IllegalArgumentException("Pret incorect!");
		}
	}

}
