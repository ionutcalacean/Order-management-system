package start;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ReflectionTable {

	/**
	 * Creaza datele din lista de obiecte sub forma de model de tabel(randuri,coloane), folosit apoi la JTable
	 * @param objects lista de obiecte
	 * @return un DefaultTableModel cu datele noastre, care va fi folosit ca model pentru JTable
	 */
	public static DefaultTableModel createTable(List<? extends Object> objects)
	{
		//JTable toReturn;
		DefaultTableModel tableModel;
		int nbFields=0;
		for(Field field:objects.get(0).getClass().getDeclaredFields())
		{
			field.setAccessible(true);
			nbFields++;
		}
		
		String[] column =new String[nbFields];
		String[][] data = new String[objects.size()][nbFields];
		
		int fieldsIndex=0;
		for(Field field:objects.get(0).getClass().getDeclaredFields())
		{
			field.setAccessible(true);
			column[fieldsIndex++]=field.getName();
		}
		Iterator<? extends Object> it=objects.iterator();
		int rowIndex=0;
		while(it.hasNext())
		{
			
			Object myOb=it.next();
			int columnIndex=0;
			for(Field field:myOb.getClass().getDeclaredFields())
			{
				field.setAccessible(true);
				Object value;
				try {
					value=field.get(myOb);
					data[rowIndex][columnIndex++]=""+value+"";
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}

			}
			rowIndex++;
		}
		//toReturn = new JTable(data,column);
		//toReturn.setBounds(100,200,400,500);   
		
		//return toReturn;
		tableModel=new DefaultTableModel(data,column);
		//tableModel.fireTableDataChanged();
		return tableModel;
	}
}
