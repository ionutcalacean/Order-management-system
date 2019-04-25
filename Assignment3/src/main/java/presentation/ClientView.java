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

import bll.ClientBLL;
import start.ReflectionTable;

public class ClientView {

	private JTable jt = new JTable();
	private JTextField id_client = new JTextField(10);
	private JTextField nume = new JTextField(10);
	private JTextField email = new JTextField(10);
	private JTextField adresa = new JTextField(10);
	private JTextField varsta = new JTextField(10);
	private JButton insert = new JButton("INSERT");
	private JButton update = new JButton("UPDATE");
	private JButton delete = new JButton("DELETE");
	private JButton back = new JButton("BACK");
	private JButton forward=new JButton("SELECT CLIENT FOR ORDER");
	JScrollPane sp = new JScrollPane(jt);
	ClientBLL cl = new ClientBLL();
	DefaultTableModel myModelClient;
	JFrame clFrame = new JFrame();

	public ClientView() {

		clFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		clFrame.setSize(800, 800);
		clFrame.setLocation(100, 100);

		JPanel mainPanel = new JPanel();
		JPanel p_left = new JPanel();

		myModelClient = ReflectionTable.createTable(cl.findAllClients());
		this.jt.setModel(myModelClient);
		sp = new JScrollPane(jt);
		p_left.add(sp);

		JPanel p_right = new JPanel();

		JPanel insertPanel = new JPanel();
		JPanel insertPanel2 = new JPanel();
		JLabel id_clientL = new JLabel("ID:");
		JLabel numeL = new JLabel("NUME:");
		JLabel emailL = new JLabel("EMAIL:");
		JLabel adresaL = new JLabel("ADRESA:");
		JLabel varstaL = new JLabel("VARSTA");
		insertPanel.add(id_clientL);
		insertPanel.add(id_client);
		insertPanel.add(numeL);
		insertPanel.add(nume);
		insertPanel.add(emailL);
		insertPanel.add(email);
		insertPanel2.add(adresaL);
		insertPanel2.add(adresa);
		insertPanel2.add(varstaL);
		insertPanel2.add(varsta);

		JPanel insertPanelDown = new JPanel();
		insertPanelDown.add(insert);
		insertPanelDown.add(update);
		insertPanelDown.add(delete);
		insertPanelDown.add(back);
		JPanel insertPanelDown2=new JPanel();
		insertPanelDown2.add(forward);

		JLabel instructionLabel = new JLabel(
				"Pentru inserare si update se completeaza toate campurile, iar pentru stergere doar id-ul!");
		p_right.add(insertPanel);
		p_right.add(insertPanel2);
		p_right.add(insertPanelDown);
		p_right.add(insertPanelDown2);
		p_right.add(instructionLabel);
		p_right.setLayout(new BoxLayout(p_right, BoxLayout.Y_AXIS));

		mainPanel.add(p_left);
		mainPanel.add(p_right);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		clFrame.setContentPane(mainPanel);
		clFrame.setTitle("Clients Window");
		clFrame.setVisible(true);
	}

	public JTable getJt() {
		return jt;
	}

	/**
	 * metoda de atualizare a JTable-ului
	 */
	public void updateJt() {
		myModelClient = ReflectionTable.createTable(cl.findAllClients());
		myModelClient.fireTableDataChanged();
		this.jt.setModel(myModelClient);

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

	public void setBack(JButton back) {
		this.back = back;
	}

	public JTextField getId_client() {
		return id_client;
	}

	public void setId_client(JTextField id_client) {
		this.id_client = id_client;
	}

	public JTextField getNume() {
		return nume;
	}

	public void setNume(JTextField nume) {
		this.nume = nume;
	}

	public JTextField getEmail() {
		return email;
	}

	public void setEmail(JTextField email) {
		this.email = email;
	}

	public JTextField getAdresa() {
		return adresa;
	}

	public void setAdresa(JTextField adresa) {
		this.adresa = adresa;
	}

	public JTextField getVarsta() {
		return varsta;
	}

	public void setVarsta(JTextField varsta) {
		this.varsta = varsta;
	}

	public void dispose() {
      clFrame.dispose();
	}

	public JButton getForward() {
		return forward;
	}

	public void setForward(JButton forward) {
		this.forward = forward;
	}

	public DefaultTableModel getMyModelClient() {
		return myModelClient;
	}

	public void setMyModelClient(DefaultTableModel myModelClient) {
		this.myModelClient = myModelClient;
	}
	
	

}
