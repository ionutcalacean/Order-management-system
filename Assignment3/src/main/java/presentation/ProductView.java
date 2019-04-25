package presentation;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import bll.ProductBLL;
import start.ReflectionTable;

public class ProductView {

	private JTable jt=new JTable();
	private JTextField id_product=new JTextField(10);
	private JTextField productName=new JTextField(10);
	private JTextField price=new JTextField(10);
	private JTextField cantitate=new JTextField(10);
	private JButton insert=new JButton("INSERT");
	private JButton update=new JButton("UPDATE");
	private JButton delete=new JButton("DELETE");
	private JButton back=new JButton("BACK");
	private JButton forward=new JButton("SELECT PRODUCT FOR PREVIOUS SELECTED CLIENT");
	JScrollPane sp=new JScrollPane(jt);
	ProductBLL products=new ProductBLL();
	DefaultTableModel myModelProduct;
	JFrame prodFrame=new JFrame();
	
	public ProductView()
	{
		
		prodFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		prodFrame.setSize(800, 800);
		prodFrame.setLocation(900, 100);
		
		
		JPanel mainPanel=new JPanel();
		JPanel p_up=new JPanel();
		
		
		myModelProduct=ReflectionTable.createTable(products.findAllProducts());
		this.jt.setModel(myModelProduct);
		sp=new JScrollPane(jt);
		p_up.add(sp);
		
		
		JPanel p_down=new JPanel();
		
		JPanel insertPanel=new JPanel();
		JPanel insertPanel2=new JPanel();
		JLabel id_clientL=new JLabel("ID:");
		JLabel numeL=new JLabel("NUME:");
		JLabel pretL=new JLabel("PRET:");
		JLabel cantL=new JLabel("CANTITATE:");
		insertPanel.add(id_clientL);
		insertPanel.add(id_product);
		insertPanel.add(numeL);
		insertPanel.add(productName);
		insertPanel2.add(pretL);
		insertPanel2.add(price);
		insertPanel2.add(cantL);
		insertPanel2.add(cantitate);

		
		JPanel insertPanelDown=new JPanel();
		insertPanelDown.add(insert);
		insertPanelDown.add(update);
		insertPanelDown.add(delete);
		insertPanelDown.add(back);
		
		JPanel insertPanelDown2=new JPanel();
		insertPanelDown2.add(forward);
		
		JLabel instructionLabel=new JLabel("Pentru inserare si update se completeaza toate campurile, iar pentru stergere doar id-ul!");
		p_down.add(insertPanel);
		p_down.add(insertPanel2);
		p_down.add(insertPanelDown);
		p_down.add(insertPanelDown2);
		p_down.add(instructionLabel);
		p_down.setLayout(new BoxLayout(p_down,BoxLayout.Y_AXIS));
		
		
		mainPanel.add(p_up);
		mainPanel.add(p_down);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		prodFrame.setContentPane(mainPanel);
		prodFrame.setTitle("Products Window");
		prodFrame.setVisible(true);
	}

	public JTable getJt() {
		return jt;
	}
	/**
	 * metoda de actualizare a JTable-ului
	 */
	public void updateJt() {
	    myModelProduct=ReflectionTable.createTable(products.findAllProducts());
        myModelProduct.fireTableDataChanged();
        this.jt.setModel(myModelProduct);
	}

	public JButton getInsert() {
		return insert;
	}

	public void setInsert(JButton insert) {
		this.insert = insert;
	}

	public JButton getUpdate() {
		return update;
	}

	public void setUpdate(JButton update) {
		this.update = update;
	}

	public JButton getDelete() {
		return delete;
	}

	public void setDelete(JButton delete) {
		this.delete = delete;
	}

	public JButton getBack() {
		return back;
	}

	public void setback(JButton back) {
		this.back = back;
	}

	public JTextField getId_product() {
		return id_product;
	}

	public void setId_product(JTextField id_product) {
		this.id_product = id_product;
	}

	public JTextField getProductName() {
		return productName;
	}

	public void setProductName(JTextField productName) {
		this.productName = productName;
	}

	public JTextField getPrice() {
		return price;
	}

	public void setPrice(JTextField price) {
		this.price = price;
	}

	public JTextField getCantitate() {
		return cantitate;
	}

	public void setCantitate(JTextField cantitate) {
		this.cantitate = cantitate;
	}
	public void dispose()
	{
		prodFrame.dispose();
	}
	
	public JButton getForward() {
		return forward;
	}

	public void setForward(JButton forward) {
		this.forward = forward;
	}

	public DefaultTableModel getMyModelProduct() {
		return myModelProduct;
	}

	public void setMyModelProduct(DefaultTableModel myModelProduct) {
		this.myModelProduct = myModelProduct;
	}
	
}
