package bll;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.swing.JOptionPane;

import bll.validators.PrCantValidator;
import bll.validators.PrNameValidator;
import bll.validators.PrPriceValidator;
import bll.validators.Validator;
import dao.ProductDAO;
import model.Client;
import model.Product;

public class ProductBLL {

	private List<Validator<Product>> validators;
	private ProductDAO productDAO;
	/**
	 * constructor, se insereaza validatorii necesari
	 */
	public ProductBLL()
	{
		validators=new ArrayList<Validator<Product>>();
		validators.add(new PrNameValidator());
		validators.add(new PrPriceValidator());
		validators.add(new PrCantValidator());
		
		productDAO=new ProductDAO();
		
	}
	/**
	 * gaseste un produs dupa id
	 * @param id id-ul produsului comandat
	 * @return acel produs daca se gaseste, null in caz contrar
	 */
	public Product findProductById(int id)
	{
		Product prod=productDAO.findById(id);
		if(prod==null)
		{
			JOptionPane.showMessageDialog(null,
				    "Id-ul nu exista!",
				    "Update error",
				    JOptionPane.ERROR_MESSAGE);
			throw new NoSuchElementException("Produsul cu id-ul:"+id+" nu a fost gasit");
		}
		return prod;
	}
	/**
	 * cauta toate produsele din tabel
	 * @return lista cu toate produsele gasite
	 */
	public List<Product> findAllProducts()
	{
		List<Product> list=productDAO.findAll();
		return list;
	}
	/**
	 * insereaza un produs in tabel
	 * @param product produsul de inserat
	 * @return acel produs daca se insereaza cu succes si datele sunt valide, null in caz contrar
	 */
	public Product insertProduct(Product product)
	{
		for(Validator<Product> v:validators) {
			v.validate(product);
		}
		Product prod=productDAO.insert(product);
		if(findProductById(prod.getId())==null)
		{
			System.out.println("Produsul nu a putu fi inserat!");
			return null;
		}
		return prod;
	}
	/**
	 * modifica un produs existent
	 * @param product produsul de modificat
	 * @return acel produs daca exista si e modificat, null in caz contrar
	 */
	public Product updateProduct(Product product)
	{
		if(findProductById(product.getId())==null)
		{
			System.out.println("Produsul updatat nu exista");
			return null;
		}
		Product prod=productDAO.update(product);
		return prod;
	}
	/**
	 * sterge un produs existent
	 * @param product produsul de sters
	 * @return acel produs daca se sterge cu succes, null in caz contrar
	 */
	public Product deleteProduct(Product product)
	{
		if(findProductById(product.getId())==null)
		{
			System.out.println("Produsul pentru sters nu exista");
			return null;
		}
		Product prod=productDAO.delete(product);
		return prod;
	}
}
