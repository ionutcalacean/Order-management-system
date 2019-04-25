package model;

public class OrderTable {
	private int id;
	private int idclient;
	private int varsta;
	
	
	
	public OrderTable(int id, int idclient,int varsta) {
		super();
		this.id = id;
		this.idclient = idclient;
		this.varsta=varsta;
	}



	public OrderTable(int idclient,int varsta) {
		super();
		this.idclient = idclient;
		this.varsta=varsta;

	}
	
	public OrderTable()
	{
		super();
	}



	public int getId() {
		return id;
	}



	public void setId(int idorder) {
		this.id = idorder;
	}



	public int getIdclient() {
		return idclient;
	}



	public void setIdclient(int idclient) {
		this.idclient = idclient;
	}



	public int getVarsta() {
		return varsta;
	}



	public void setVarsta(int varsta) {
		this.varsta = varsta;
	}

    
	
	
	
	
	
	
}
