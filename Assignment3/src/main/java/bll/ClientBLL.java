package bll;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.swing.JOptionPane;

import bll.validators.AdresaValidator;
import bll.validators.ClientAgeValidator;
import bll.validators.EmailValidator;
import bll.validators.NameValidator;
import bll.validators.Validator;
import dao.ClientDAO;
import model.Client;

public class ClientBLL {

	private List<Validator<Client>> validators;
	private ClientDAO clientDAO;
	
	/**
	 * constructor, se adauga validatorii necesari
	 */
	public ClientBLL() {
		validators=new ArrayList<Validator<Client>>();
		validators.add(new EmailValidator());
		validators.add(new NameValidator());
		validators.add(new AdresaValidator());
		validators.add(new ClientAgeValidator());
		
		clientDAO=new ClientDAO();
	}
	/**
	 * 
	 * @param id id-ul cautat
	 * @return Clientul in cazul in care il gaseste, null daca nu
	 */
	public Client findClientByID(int id) {
		Client cl=clientDAO.findById(id);
		if(cl==null)
		{
			JOptionPane.showMessageDialog(null,
				    "Id-ul nu exista!",
				    "Update error",
				    JOptionPane.ERROR_MESSAGE);
			throw new NoSuchElementException("Clientul cu id-ul:"+id+" nu a fost gasit");
		}
		return cl;
	}
	/**
	 * gaseste toti clientii din tabel
	 * @return Lista cu clientii gasiti in tabel
	 */
	public List<Client> findAllClients()
	{
		List<Client> list =clientDAO.findAll();
		return list;
	}
	/**
	 * insereaza un client in baza de date daca sunt valide datele
	 * @param clientul care se insereaza
	 * @return clientul daca e inserat cu succes, null in caz contrar
	 */
	public Client insertClient(Client client) {
	
		for(Validator<Client> v:validators) {
			v.validate(client);
		}
		Client cl=clientDAO.insert(client);
		if(findClientByID(cl.getId())==null)
		{
			System.out.println("Clientul nu a putut fi introdus");
			return null;
		}
		return cl;
	}
	/**
	 * updateaza-un client daca acesta exista
	 * @param clientul care se doreste a fi modificat
	 * @return clientul daca se modifica , daca nu exista se retureneaza null
	 */
	public Client updateClient(Client client) {
		if(findClientByID(client.getId())==null)
		{
			System.out.println("Clientul updatad nu exista");
			return null;
		}
		Client cl=clientDAO.update(client);
	    return cl;
	}
	/**
	 * sterge un client daca esita
	 * @param clientul care se doreste a fi sters
	 * @return clientul daca se sterge cu succes, null in caz contrar
	 */
	public Client deleteClient(Client client) {
		if(findClientByID(client.getId())==null)
		{
			System.out.println("Clientul care se vrea sters nu exista");
		    return null;
		}
		Client cl=clientDAO.delete(client);
		return cl;
	}
	
	
	
}
