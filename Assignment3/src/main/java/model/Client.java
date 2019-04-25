package model;

public class Client {

	private int id;
	private String name;
	private String email;
	private String adresa;
	private int varsta;
	
	public Client(int id, String name, String email, String adresa, int varsta) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.adresa = adresa;
		this.varsta = varsta;
	}
	

	public Client(String name, String email, String adresa, int varsta) {
		super();
		this.name = name;
		this.email = email;
		this.adresa = adresa;
		this.varsta = varsta;
	}

    public Client() {
    	super();
    }
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public int getVarsta() {
		return varsta;
	}

	public void setVarsta(int varsta) {
		this.varsta = varsta;
	}
	
   
	
	
	
	
}
