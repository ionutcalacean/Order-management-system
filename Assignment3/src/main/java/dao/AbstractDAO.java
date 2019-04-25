package dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;//list of objects and generates the header of the table by extracting through reflection the object properties and then populates the table with the values of the elements from the list
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;

public class AbstractDAO<T> {
	protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

	private final Class<T> type;

	@SuppressWarnings("unchecked")
	public AbstractDAO() {

		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		System.out.println(this.type.getSimpleName());
	}
   /**
    * creare query-ul pentru findById
    * @param field numele tabelului
    * @return Stringul final(query-ul necesar)
    */
	private String createSelectQuery(String field) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append(" * ");
		sb.append(" FROM ");
		sb.append(type.getSimpleName());
		sb.append(" WHERE " + field + " =?");
		return sb.toString();
	}
    /**
     * gaseste toate randurile dintr-un tabel
     * @return Lista cu toate randurile din tabel
     */
	public List<T> findAll() {
	
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		String query = "SELECT * FROM " + type.getSimpleName() + "";
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();

			return createObjects(resultSet);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}
   /**
    * executa query-ul de cautare a unei tuple dupa un camp(id)
    * @param id id-ul cautat
    * @return elementul gasit
    */
	public T findById(int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createSelectQuery("id");
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
            if(resultSet.next()==false)
            	return null;
            resultSet.beforeFirst();
			return createObjects(resultSet).get(0);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}
		return null;
	}
   /**
    * metoda de creare a unei liste de obiecte din model, in functie de tipul dat
    * @param resultSet-ul rezultat in utma aplicarii query-ului find pe baza de date
    * @return lista de obiecte
    */
	private List<T> createObjects(ResultSet resultSet) {
		List<T> list = new ArrayList<T>();

		try {
			while (resultSet.next()) {
				T instance = type.newInstance();
				for (Field field : type.getDeclaredFields()) {
					Object value = resultSet.getObject(field.getName());
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
					Method method = propertyDescriptor.getWriteMethod();
					method.invoke(instance, value);
				}
				list.add(instance);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return list;
	}
   /**
    * insereaza in baza de date si creeaza inainte query-ul
    * @param t elementul de inserat
    * @return acel element dupa inserare
    */
	public T insert(T t) {
		// TODO:
		Connection connection = null;
		PreparedStatement statement = null;

		String query = "INSERT INTO " + type.getSimpleName() + "(";
        
		int nbFields = 0;
		for (Field field : t.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			nbFields++;
		}
		int currIt = 0;
		for (Field field : t.getClass().getDeclaredFields())
		{
			currIt++;
			field.setAccessible(true);
			if (currIt != nbFields) {
				query += field.getName()+",";
			} else {
				query += field.getName();
			}
		}
		query+=")";
		
		query+=" VALUES(";
		
		
		currIt = 0;
		for (Field field : t.getClass().getDeclaredFields()) {
			currIt++;
			field.setAccessible(true);

			if (currIt != nbFields) {
				query += "?,";
			} else {
				query += "?";
			}
		}
		query += ")";
		System.out.println(query);
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			int index = 1;
			for (Field field : t.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				Object value;
				try {
					value = field.get(t);
					if (value instanceof String)
						statement.setString(index++, (String) value);
					else if (value instanceof Integer)
						statement.setInt(index++, (Integer) value);

				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			statement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}

		return t;
	}
   /**
    * modificare element existent in tabela
    * @param t elementul de modificat
    * @return acel element dupa modificare
    */
	public T update(T t) {
		// TODO:
		Connection connection = null;
		PreparedStatement statement = null;

		String query = "UPDATE " + type.getSimpleName() + " SET ";

		int nbFields = 0;
		for (Field field : t.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			nbFields++;
		}

		int currIt = 0;
		String idCurrClass = null;
		for (Field field : t.getClass().getDeclaredFields()) {
			currIt++;
			field.setAccessible(true);
			if (currIt != nbFields) {
				if (currIt == 1) // primul camp e mereu idul
					idCurrClass = field.getName();
				else
					query += field.getName() + "= ? ,";
			} else {
				query += field.getName() + "= ? ";
			}

		}
		query += "WHERE " + idCurrClass + "= ?";
		System.out.println(query);

		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);
			int index = 0;
			for (Field field : t.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				Object value;
				try {
					value = field.get(t);
					if (index == 0) {
						statement.setInt(nbFields, (Integer) value);
						index++;
					} else {
						if (value instanceof String)
							statement.setString(index++, (String) value);
						else if (value instanceof Integer)
							statement.setInt(index++, (Integer) value);
					}

				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			statement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
		} finally {
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}

		return t;
	}

   /**
    * sterge un element din tabela
    * @param t elementul de sters
    * @return acel element dupa stergere
    */
	public T delete(T t) {
		Connection connection = null;
		PreparedStatement statement = null;

		String query = "DELETE FROM " + type.getSimpleName() + " WHERE ";
		
		String myField=null;
		for(Field field:t.getClass().getDeclaredFields())
		{
			field.setAccessible(true);
			myField=field.getName();
			query+=myField+"= ?";
			break;
		}
		
		System.out.println(query);
		
		try {
			connection=ConnectionFactory.getConnection();
			statement=connection.prepareStatement(query);
			try {
				Field field=t.getClass().getDeclaredField(myField);
				field.setAccessible(true);
				Object value=field.get(t);
				statement.setInt(1, (Integer)value);
				
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
			statement.executeUpdate();
		}catch(SQLException e)
		{
			LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
		}finally{
			ConnectionFactory.close(connection);
			ConnectionFactory.close(statement);
		}
		
		return t;
	}
}
