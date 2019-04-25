package bll;

import java.util.List;
import java.util.NoSuchElementException;

import javax.swing.JOptionPane;

import dao.OrderDAO;
import model.OrderTable;

public class OrderBLL {

	private OrderDAO orderDAO;
	
	public OrderBLL()
	{
		this.orderDAO=new OrderDAO();
	}
	/**
	 * gaseste o comanda dupa id
	 * @param id id-ul comenzii cautate
	 * @return OrderTable -comanda daca se gaseste, null in caz contrar
	 */
	public OrderTable findOrderById(int id)
	{
		OrderTable order=orderDAO.findById(id);
		if(order == null)
		{
			JOptionPane.showMessageDialog(null,
				    "Id-ul nu exista!",
				    "Update error",
				    JOptionPane.ERROR_MESSAGE);
			throw new NoSuchElementException("Comanda cu id-ul:"+id+" nu a fost gasit");
		}
		return order;
	}
	/**
	 * cauta toate comenzile existente in tabel
	 * @return Lista cu comenzile
	 */
	public List<OrderTable> findAllOrders()
	{
		List<OrderTable> list=orderDAO.findAll();
		return list;
	}
	/**
	 * insereaza o comanda in tabel
	 * @param order comanda de inserat
	 * @return comanda daca s-a inserat cu succes, null in caz contrar
	 */
	public OrderTable insertOrder(OrderTable order)
	{
		OrderTable ord=orderDAO.insert(order);
		if(findOrderById(ord.getId())==null)
		{
			System.out.println("Comanda nu a putut fi introdusa");
			return null;
		}
		return ord;
	}
	/**
	 * modifica o comanda existenta
	 * @param order comanda care se doreste a fi modificata
	 * @return comanda daca s-a modificat, null daca nu exista
	 */
	public OrderTable updateOrder(OrderTable order)
	{
		if(findOrderById(order.getId())==null)
		{
			System.out.println("Comanda updatata nu exista");
			return null;
		}
		OrderTable ord=orderDAO.update(order);
		return ord;
	}
	/**
	 * stergere comanda daca exista
	 * @param order comanda de sters
	 * @return comanda daca s-a sters cu succes null in caz contrar
	 */
	public OrderTable deleteOrder(OrderTable order)
	{
		if(findOrderById(order.getId())==null)
		{
			System.out.println("Comanda care se vrea stearsa nu exista");
			return null;
		}
		OrderTable ord=orderDAO.delete(order);
		return ord;
	}
}
