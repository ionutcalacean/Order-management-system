package bll;

import java.util.List;
import java.util.NoSuchElementException;

import javax.swing.JOptionPane;

import dao.OrderItemDAO;
import model.OrderItem;

public class OrderItemBLL {

	private OrderItemDAO orderItemDAO;
	
	public OrderItemBLL()
	{
		orderItemDAO=new OrderItemDAO();
	}
	/**
	 * gaseste un produs comandat dupa id
	 * @param id id-ul cautat
	 * @return OrderItem- produsul comandat daca exista, null in caz contrar
	 */
	public OrderItem findOrderItemById(int id)
	{
		OrderItem orderIt=orderItemDAO.findById(id);
		if(orderIt==null)
		{
			JOptionPane.showMessageDialog(null,
				    "Id-ul nu exista!",
				    "Update error",
				    JOptionPane.ERROR_MESSAGE);
			throw new NoSuchElementException("OrderItem=ul cu id-ul:"+id+" nu a fost gasit");
		}
		return orderIt;
	}
	/**
	 * cauta toate produsele comandate
	 * @return Lista cu toate produsele comandate din tabela
	 */
	public List<OrderItem> findAllOrderItems()
	{
		List<OrderItem> list=orderItemDAO.findAll();
		return list;
	}
	/**
	 * insereaza un produs comandat
	 * @param orderItem produsul comandat de inserat
	 * @return acel produs comandat daca s-a inserat cu succes, null in caz contrar
	 */
	public OrderItem insertOrderItem(OrderItem orderItem)
	{
		OrderItem orderIt=orderItemDAO.insert(orderItem);
		if(findOrderItemById(orderIt.getId())==null)
		{
			System.out.println("Produsul comandat nu a putut fi inserat");
			return null;
		}
		return orderIt;
	}
	/**
	 * modifica un produs comandat existent
	 * @param orderItem produsul comandat de modificat
	 * @return acel produs comandat daca modificare se efectueaza cu succes, null in caz contrar
	 */
	public OrderItem updateOrderItem(OrderItem orderItem)
	{
		if(findOrderItemById(orderItem.getId())==null)
		{
			System.out.println("Produsul comandat care se incearca updatat nu exista");
			return null;
		}
		OrderItem orderIt=orderItemDAO.update(orderItem);
		return orderIt;
	}
	/**
	 * sterge un produs comantat din lista de produse comandate daca exista
	 * @param orderItem produsul comandat de sters
	 * @return acel produs daca se sterge cu succes, null in caz contrar
	 */
	public OrderItem deleteOrderItem(OrderItem orderItem)
	{
		if(findOrderItemById(orderItem.getId())==null)
		{
			System.out.println("Comanda si produsele care se doresc sterse nu exista");
			return null;
		}
		OrderItem orderIt=orderItemDAO.delete(orderItem);
		return orderIt;
	}
	
	
}
