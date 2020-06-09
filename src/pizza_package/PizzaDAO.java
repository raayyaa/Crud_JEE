package pizza_package;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PizzaDAO {

	static public List<Pizza> listAllPizzas() throws SQLException, ClassNotFoundException {
		List<Pizza> listPizza = new ArrayList<Pizza>();
		String sql = "SELECT * FROM t_pizza";

		Connection conn = DatabaseConnection.initializeDatabase();

		Statement statement = conn.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String designPizz = resultSet.getString("DesignPizz");
			Double price = resultSet.getDouble("TarifPizz");
			String image = resultSet.getString("Image");

			Pizza itemPizza = new Pizza(id, designPizz, price, image);
			listPizza.add(itemPizza);
		}

		resultSet.close();
		statement.close();

		// itemPizza disconnect();

		return listPizza;
	}

	static public Pizza getPizzaById(int pizzaid) throws SQLException, ClassNotFoundException {

		Connection conn = DatabaseConnection.initializeDatabase();

		String query = "select * from t_pizza where id = " + pizzaid;
		Statement stmt = conn.createStatement();
		ResultSet res = stmt.executeQuery(query);
		res.next();

		Pizza pizza = new Pizza(res.getInt("id"), res.getString("DesignPizz"), res.getDouble("TarifPizz"),
				res.getString("Image"));
		stmt.close();
		res.close();
		return pizza;

	}

	// AJOUTER
	static public void ajouterPizza(Pizza pizza) throws SQLException, ClassNotFoundException {

		Connection conn = DatabaseConnection.initializeDatabase();
		PreparedStatement ps = conn.prepareStatement("insert into t_pizza(DesignPizz,TarifPizz) values (?,?)");

		ps.setString(1, pizza.getDesignPizz());
		ps.setDouble(2, pizza.getPrice());
		ps.executeUpdate();

	}

	// SUPPRESSION
	static public Pizza deletePizzaById(int pizzaid) throws SQLException, ClassNotFoundException {

		Pizza delPizza = getPizzaById(pizzaid);
		Connection conn = DatabaseConnection.initializeDatabase();
		PreparedStatement ps = conn.prepareStatement("delete from t_pizza where id=?");
		ps.setInt(1, pizzaid);
		ps.executeUpdate();

//	           String query = "delete from t_pizza where id = " + pizzaid;
//	           Statement stmt = conn.createStatement();
//	           ResultSet res = stmt.executeQuery(query);
//	           res.next();
//	           
		return delPizza;

	}

	// MODIFIER
	public static void saveOrUpdate(Pizza pizza) throws Exception {
		int id = pizza.getId();
		
		Connection conn = DatabaseConnection.initializeDatabase();
		PreparedStatement ps;
		if(id!=0) {
			ps = conn.prepareStatement("update t_pizza set DesignPizz=?,TarifPizz=?,Image=? where id=?");	
			ps.setInt(4, pizza.getId());

		}else {
			ps = conn.prepareStatement("insert into t_pizza(DesignPizz,TarifPizz,Image) values (?,?,?)");

		}

		ps.setString(1, pizza.getDesignPizz());
		ps.setDouble(2, pizza.getPrice());
		ps.setString(3, pizza.getImage());

		ps.executeUpdate();
	}

}
