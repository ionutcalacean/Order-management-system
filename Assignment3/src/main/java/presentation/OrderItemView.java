package presentation;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import bll.OrderItemBLL;
import start.ReflectionTable;

public class OrderItemView {

	private JTable jt=new JTable();
	JScrollPane sp=new JScrollPane(jt);
	OrderItemBLL orderItems=new OrderItemBLL();
	DefaultTableModel myModelOrderIt;
	JFrame orderItFrame=new JFrame();
	private JButton back=new JButton("BACK");
	
	public OrderItemView() {
		orderItFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		orderItFrame.setSize(800, 800);
		orderItFrame.setLocation(450, 100);
		orderItFrame.setTitle("Ordered Items");
		JPanel mainPanel=new JPanel();
		JPanel upPanel=new JPanel();
		JPanel downPanel=new JPanel();
		myModelOrderIt=ReflectionTable.createTable(orderItems.findAllOrderItems());
		this.jt.setModel(myModelOrderIt);
		sp=new JScrollPane(jt);
		upPanel.add(sp);
		downPanel.add(back);
		mainPanel.add(upPanel);
		mainPanel.add(downPanel);
		
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
		
        orderItFrame.setContentPane(mainPanel);		
		orderItFrame.setVisible(true);
	}
	/**
	 * metoda de actualizare a JTable-ului
	 */
	public void updateJt()
	{
		myModelOrderIt=ReflectionTable.createTable(orderItems.findAllOrderItems());
		myModelOrderIt.fireTableDataChanged();
		this.jt.setModel(myModelOrderIt);
    }

	public JButton getBack() {
		return back;
	}

	public void setBack(JButton back) {
		this.back = back;
	}
	
	public void dispose()
	{
		orderItFrame.dispose();
	}
}
