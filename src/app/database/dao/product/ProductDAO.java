package app.database.dao.product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import app.database.connection.DBConnection;
import app.database.dao.base.BaseDAO;
import app.models.product.Product;

public class ProductDAO extends BaseDAO {
	private ArrayList<Product> products = new ArrayList<Product>();

	public ProductDAO(DBConnection connection) {
		super(connection);
		this.createTable();
	}

	@Override
	public boolean save() {
		String sql = "INSERT INTO Products VALUES(?, ?, ?, ?);";
		DBConnection conn = this.getConnection();
		PreparedStatement stmt = conn.genPreparedStatement(sql);
		try {
			for(Product product : this.products) {
				if(product.getId() == 0) {
					stmt.setLong(1, product.getId());
					stmt.setString(2, product.getName());
					stmt.setFloat(3, product.getPrice());
					stmt.setString(4, product.getBarcode());
					stmt.execute();
					product.setId(-1);
				}
			}
			stmt.close();
			this.products.removeIf(product -> product.getId() == -1);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update() {
		String sql = "UPDATE Products "
				+ "SET name = ?, price = ?, barcode = ? "
				+ "WHERE id = ?;";
		DBConnection conn = this.getConnection();
		PreparedStatement stmt = conn.genPreparedStatement(sql);
		try {
			for(Product product : this.products) {
				if(product.getId() > 0) {
					stmt.setString(1, product.getName());
					stmt.setFloat(2, product.getPrice());
					stmt.setString(3, product.getBarcode());
					stmt.setLong(4, product.getId());
					stmt.execute();
					product.setId(-1);
				}
			}
			stmt.close();
			this.products.removeIf(product -> product.getId() == -1);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(long ids[]) {
		String sql = "DELETE FROM Products WHERE id = ?;";
		DBConnection conn = this.getConnection();
		PreparedStatement stmt = conn.genPreparedStatement(sql);
		try {
			for(long id : ids) {
				if(id > 0) {
					stmt.setLong(1, id);
					stmt.execute();
					this.products.removeIf(product -> id == product.getId());
				}
			}
			stmt.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void search() {
		String sql = "SELECT * FROM Products;";
		DBConnection conn = this.getConnection();
		PreparedStatement stmt = conn.genPreparedStatement(sql);
		Product product;
		this.products.clear();
		try {
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				product = new Product(rs.getLong("id"), rs.getString("name"), rs.getFloat("price"), rs.getString("barcode"));
				this.products.add(product);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void createTable() {
		String sql = "CREATE TABLE IF NOT EXISTS Products("
				+ "id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,"
				+ "name VARCHAR(255) NOT NULL UNIQUE,"
				+ "price FLOAT NOT NULL,"
				+ "barcode VARCHAR(255) NOT NULL UNIQUE);";
		DBConnection conn = this.getConnection();
		PreparedStatement stmt = conn.genPreparedStatement(sql);
		try {
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void add(Product product) {
		this.products.add(product);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Product> getProducts() {
		return (ArrayList<Product>) products.clone();
	}
}
