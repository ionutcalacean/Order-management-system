package presentation;

import java.awt.*;

import javax.swing.*;

public class View {
  
	private JButton clientsButton=new JButton("CLIENTS");
	private JButton productsButton=new JButton("PRODUCTS");
	private JButton ordersButton=new JButton("ORDERS");
	JFrame myFrame=new JFrame();
	
	public View(){
	
		myFrame.setSize(400, 200);
		myFrame.setLocation(700,500);
		myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel mainPanel=new JPanel();
		
		
		mainPanel.add(clientsButton);
		mainPanel.add(productsButton);
		mainPanel.add(ordersButton);
	
		
		
		myFrame.setTitle("Application Menu");
        myFrame.setContentPane(mainPanel);		
		myFrame.setVisible(true);
	}


	public JButton getClientsButton() {
		return clientsButton;
	}


	public void setClientsButton(JButton clientsButton) {
		this.clientsButton = clientsButton;
	}


	public JButton getProductsButton() {
		return productsButton;
	}


	public void setProductsButton(JButton productsButton) {
		this.productsButton = productsButton;
	}


	public JButton getOrdersButton() {
		return ordersButton;
	}


	public void setOrdersButton(JButton ordersButton) {
		this.ordersButton = ordersButton;
	}
	/**
	 * inchidere a ferestrei
	 */
	public void dispose()
	{
		myFrame.dispose();
	}
}
