package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.OrderItemBLL;
import bll.ProductBLL;
import model.Client;
import model.OrderItem;
import model.OrderTable;
import model.Product;

public class Controller {

	private static View myView;
	private static ClientView clView;
	private static ProductView prodView;
	private static OrderItemView orderItView;
	private static OrderTable currentOrder;

    public Controller(View myView)
    {
    	this.myView=myView;
        addActionListeners3(myView);

    }
    

  /**
   * adaugare ascultatori pentru butoanele din view-ul principal
   * @param myView2 view-ul de app menu
   */
	private void addActionListeners3(View myView2) {
		
		
		myView.getClientsButton().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				Controller.clView=new ClientView();
				addActionListeners(clView);
				myView.dispose();
				
			}
			
		});
		myView.getProductsButton().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Controller.prodView=new ProductView();
				addActionListeners2(prodView);
				
				myView.dispose();
				
			}
			
		});
		
		
		myView.getOrdersButton().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				Controller.orderItView=new OrderItemView();
				addActionListeners4(orderItView);
				
				myView.dispose();
				
			}
			
		});
	}


   /**
    * adaugare ascultatori fereastra client
    * @param clView view-ul client
    */
	public void addActionListeners(final ClientView clView)
    {
    	clView.getInsert().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				String idS=clView.getId_client().getText();
				int id=Integer.parseInt(idS);
				String nume=clView.getNume().getText();
				String email=clView.getEmail().getText();
				String adresa=clView.getAdresa().getText();
				String varstaS=clView.getVarsta().getText();
				int varsta=Integer.parseInt(varstaS);
				Client newClient=new Client(id,nume,email,adresa,varsta);
				
				ClientBLL clientBLL= new ClientBLL();
				clientBLL.insertClient(newClient);
	
				clView.updateJt();
				
				
				
				
			}
    		
    	});
    	
    	clView.getUpdate().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				String idS=clView.getId_client().getText();
				int id=Integer.parseInt(idS);
				String nume=clView.getNume().getText();
				String email=clView.getEmail().getText();
				String adresa=clView.getAdresa().getText();
				String varstaS=clView.getVarsta().getText();
				int varsta=Integer.parseInt(varstaS);
				
				ClientBLL clientBLL= new ClientBLL();
				if (clientBLL.findClientByID(id)!=null)
				{
					Client newClient=new Client(id,nume,email,adresa,varsta);
					clientBLL.updateClient(newClient);
					clView.updateJt();
				}
			}
    		
    	});
    	
    	clView.getDelete().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				String idS=clView.getId_client().getText();
				int id=Integer.parseInt(idS);
				
				ClientBLL clientBLL= new ClientBLL();
				Client toBeDeleted = new Client();
				toBeDeleted=clientBLL.findClientByID(id);
			
					clientBLL.deleteClient(toBeDeleted);
					clView.updateJt();
			}
    		
    	});
    	
    	clView.getBack().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Controller.myView=new View();
				addActionListeners3(myView);
				clView.dispose();
			}
    		
    	});
    	
    	clView.getForward().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				int row=clView.getJt().getSelectedRow();
				int columns=clView.getJt().getColumnCount();
				DefaultTableModel myModel=clView.getMyModelClient();
				ArrayList<Object> values=new ArrayList<Object>();
			    
				for(int i=0;i<columns;i++)
				{
					values.add(myModel.getValueAt(row, i));
				}
			    OrderBLL orderBLL=new OrderBLL();
			    String intS=values.get(0).toString()+values.get(4).toString()+"";
			    int id_order=Integer.parseInt(intS);
			    OrderTable newOrder=new OrderTable(id_order,Integer.parseInt(values.get(0).toString()),Integer.parseInt(values.get(4).toString()));
			    orderBLL.insertOrder(newOrder);
			    
			    currentOrder=newOrder;
			    
			    Controller.prodView=new ProductView();
				addActionListeners2(prodView);
				
				clView.dispose();
			    
			}
    		
    	});
    }
	
	 /**
	  * adaugare ascultatori fereastra product
	  * @param prodView2 fereastra product
	  */
    private void addActionListeners2(ProductView prodView2) {
		
    	prodView.getInsert().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				String idS=prodView.getId_product().getText();
				int id=Integer.parseInt(idS);
				String nume=prodView.getProductName().getText();
                String priceS=prodView.getPrice().getText();
                int price=Integer.parseInt(priceS);
                String cantS=prodView.getCantitate().getText();
                int cant=Integer.parseInt(cantS);
                Product newProd=new Product(id,nume,price,cant);
                
                ProductBLL productBLL=new ProductBLL();
                productBLL.insertProduct(newProd);
                
                prodView.updateJt();
				
			}
    		
    	});
    	
    	prodView.getUpdate().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String idS=prodView.getId_product().getText();
				int id=Integer.parseInt(idS);
				String nume=prodView.getProductName().getText();
                String priceS=prodView.getPrice().getText();
                int price=Integer.parseInt(priceS);
                String cantS=prodView.getCantitate().getText();
                int cant=Integer.parseInt(cantS);
                
                ProductBLL productBLL=new ProductBLL();
                
                if(productBLL.findProductById(id)!=null)
                {
                	Product updatedProd=new Product(id,nume,price,cant);
                	productBLL.updateProduct(updatedProd);
                	prodView.updateJt();
                }
				
			}
    		
    	});
    	
    	prodView.getDelete().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String idS=prodView.getId_product().getText();
				int id=Integer.parseInt(idS);
				
				ProductBLL productBLL=new ProductBLL();
				Product toBeDeleted=new Product();
				toBeDeleted=productBLL.findProductById(id);
				
				productBLL.deleteProduct(toBeDeleted);
				prodView.updateJt();
				
			}
    		
    	});
    	
    	prodView.getBack().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Controller.myView=new View();
				addActionListeners3(myView);
				prodView.dispose();
				
			}
    		
    	});
    	
    	prodView.getForward().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				
				int row=prodView.getJt().getSelectedRow();
				int columns=prodView.getJt().getColumnCount();
				DefaultTableModel myModel=prodView.getMyModelProduct();
				ArrayList<Object> values=new ArrayList<Object>();
			    
				for(int i=0;i<columns;i++)
				{
					values.add(myModel.getValueAt(row, i));
				}
				
				OrderItemBLL orderIt=new OrderItemBLL();
				ProductBLL prodBLL=new ProductBLL();
				
				String idS=currentOrder.getId()+values.get(0).toString();
				int id=Integer.parseInt(idS);
				
				int idProd=Integer.parseInt(values.get(0).toString());
				Product prod=prodBLL.findProductById(idProd);
				
				
				int cantitate=Integer.parseInt(JOptionPane.showInputDialog(null, "Introduceti cantitatea:"));
				if(cantitate>prod.getCantitate())
				{
					JOptionPane.showMessageDialog(null,
						    "Cantitate insuficienta!",
						    "Update error",
						    JOptionPane.ERROR_MESSAGE);
					throw new NoSuchElementException("Cantitate insuficienta");
				}
				else {
					prod.setCantitate(prod.getCantitate()-cantitate);
					prodBLL.updateProduct(prod);
				}
				
				OrderItem ordIt=new OrderItem(id,currentOrder.getId(),Integer.parseInt(values.get(0).toString()),cantitate);
				orderIt.insertOrderItem(ordIt);
				
				ClientBLL clientBLL=new ClientBLL();
				
				String filename=idS+".txt";
				PrintWriter writer=null;
				try {
					 writer=new PrintWriter(filename,"UTF-8");
					String clientName=clientBLL.findClientByID(currentOrder.getIdclient()).getName();
					String productName=values.get(1).toString();
					String productPriceS=values.get(2).toString();
					int productPrice=Integer.parseInt(productPriceS);
					int pretTotal=productPrice*cantitate;
					writer.print("Clientul "+clientName+ " a comandat produsul:"+productName+" cantitate:"+cantitate+" pret total:"+pretTotal+"");
				} catch (FileNotFoundException e) {
					
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
		
					e.printStackTrace();
				}finally {
					writer.close();
				}
				
				Controller.orderItView=new OrderItemView();
				addActionListeners4(orderItView);
				prodView.dispose();
				
				
			}
    		
    	});
		
	}
    /**
     * adaugare ascultatori fereastra produselor comandate
     * @param orderItView fereastra produselor comandate
     */
    private void addActionListeners4( final OrderItemView orderItView)
    {
    	orderItView.getBack().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				Controller.myView=new View();
				addActionListeners3(myView);
				orderItView.dispose();
			}
    		
    	});
    }
}
