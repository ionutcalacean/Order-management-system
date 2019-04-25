package model;

public class OrderItem{
      private int id;
      private int order_id;
      private int order_product;
      private int cantitate;
	
      public OrderItem(int id, int order_id, int order_product, int cantitate) {
		super();
		this.id = id;
		this.order_id = order_id;
		this.order_product = order_product;
		this.cantitate = cantitate;
	}

	public OrderItem(int order_id, int order_product, int cantitate) {
		super();
		this.order_id = order_id;
		this.order_product = order_product;
		this.cantitate = cantitate;
	}
      
      public OrderItem()
      {
    	  super();
      }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getOrder_product() {
		return order_product;
	}

	public void setOrder_product(int order_product) {
		this.order_product = order_product;
	}

	public int getCantitate() {
		return cantitate;
	}

	public void setCantitate(int cantitate) {
		this.cantitate = cantitate;
	}
      
      
      
     
}
