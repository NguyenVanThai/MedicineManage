package navata.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import navata.dto.DtoCustomer;
import navata.dto.DtoProvider;

import javax.sql.rowset.CachedRowSet;

public class DaoDatabase {
	private PgConnection pgCon;

	public DaoDatabase(String server, String port, String database, String schema, String userName, String password) {
		pgCon = new PgConnection();
		pgCon.loadConnectionFromParameters(server, port, database, schema, userName, password);
	}

	public DaoDatabase() {
		pgCon = new PgConnection();
		pgCon.loadConnectionFromProperties("conf/db.properties");

	}

	public int addCustomer(String idCustomer, String name, String phone, String email, String address, Date birthday,
			String note) {

		int result = -1;
		String query = "INSERT INTO tbl_customer(customer_id,customer_name, customer_phone, customer_email, customer_address, customer_birthday, customer_note) VALUES (?,?, ?, ?, ?,?, ?)";
		Object[] params = { idCustomer, name, phone, email, address, birthday, note };
		try {
			result = pgCon.execute(query, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteCustomer(String idCustomer) {

		int result = -1;
		String query = "DELETE FROM db_medicine_manage.tbl_customer  WHERE customer_id = ?";
		Object[] params = { idCustomer };
		try {
			result = pgCon.execute(query, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int editCustomer(String customer_id, String customer_name, String customer_phone, String customer_email,
			String customer_address, Date customer_birthday, String customer_note) {

		int result = -1;
		String query = "UPDATE db_medicine_manage.tbl_customer SET  customer_name=?, customer_phone=?, customer_email=?, customer_address=?, customer_birthday=?, customer_note=? WHERE customer_id=?";

		// String query = "UPDATE db_medicine_manage.tbl_customer SET
		// customer_name='"+customer_name+"',
		// customer_phone='"+customer_phone+"',
		// customer_email='"+customer_email+"',
		// customer_address='"+customer_address+"',
		// customer_birthday='"+customer_birthday+"',
		// customer_note='"+customer_note+"' WHERE
		// customer_id='"+customer_id+"'";
		Object[] params = { customer_name, customer_phone, customer_email, customer_address, customer_birthday,
				customer_note, customer_id };

		// System.out.println(query);
		// String query = "UPDATE db_medicine_manage.tbl_customer SET
		// customer_name=? WHERE customer_id=?";
		// Object[] params = { customer_name,customer_id};
		try {
			result = pgCon.execute(query, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int customerCode() {
		int result = -1;
		String query = "select MAX(id) as code from tbl_customer";
		try {
			Object rs = pgCon.retrieveSingleField(query, null);
			result = (int) rs + 1;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	public List<DtoCustomer> listCustomer() {
		List<DtoCustomer> result = new ArrayList<>();
		String query = "select *from tbl_customer order by (customer_id)";

		try (CachedRowSet rs = pgCon.retrieve(query, null)) {
			while (rs.next()) {
				// System.out.println(rs.getDate("customer_birthday") + "");
				result.add(new DtoCustomer(rs.getInt("id"), rs.getString("customer_id"), rs.getString("customer_name"),
						rs.getString("customer_phone"), rs.getString("customer_email"),
						rs.getString("customer_address"), rs.getDate("customer_birthday"),
						rs.getString("customer_note")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<DtoCustomer> searchCustomer(String input) {
		List<DtoCustomer> result = new ArrayList<>();
		String query = "select *from  tbl_customer where customer_id like '%" + input + "%' or customer_name like '%"
				+ input + "%' or customer_phone like '%" + input + "%' order by (customer_id)";
		// Object[] params = { input, input, input };
		try (CachedRowSet rs = pgCon.retrieve(query, null)) {
			while (rs.next()) {
				// System.out.println(rs.getDate("customer_birthday") + "");
				result.add(new DtoCustomer(rs.getInt("id"), rs.getString("customer_id"), rs.getString("customer_name"),
						rs.getString("customer_phone"), rs.getString("customer_email"),
						rs.getString("customer_address"), rs.getDate("customer_birthday"),
						rs.getString("customer_note")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<DtoProvider> listProvider() {
		List<DtoProvider> result = new ArrayList<>();
		String query = "select *from tbl_provider order by (provider_id)";

		try (CachedRowSet rs = pgCon.retrieve(query, null)) {
			while (rs.next()) {
				// System.out.println(rs.getDate("provider_birthday") + "");
				result.add(new DtoProvider(rs.getInt("id"), rs.getString("provider_id"), rs.getString("provider_name"),
						rs.getString("provider_phone"), rs.getString("provider_email"),
						rs.getString("provider_address"), rs.getString("provider_note")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<DtoProvider> searchProvider(String input) {
		List<DtoProvider> result = new ArrayList<>();
		String query = "select *from  tbl_provider where provider_id like '%" + input + "%' or provider_name like '%"
				+ input + "%' or provider_phone like '%" + input + "%' order by (provider_id)";
		// Object[] params = { input, input, input };
		try (CachedRowSet rs = pgCon.retrieve(query, null)) {
			while (rs.next()) {
				// System.out.println(rs.getDate("customer_birthday") + "");
				result.add(new DtoProvider(rs.getInt("id"), rs.getString("provider_id"), rs.getString("provider_name"),
						rs.getString("provider_phone"), rs.getString("provider_email"),
						rs.getString("provider_address"), rs.getString("provider_note")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int addProvider(String provider_id, String name, String phone, String email, String address,
			String note) {

		int result = -1;
		String query = "INSERT INTO tbl_provider(provider_id,provider_name, provider_phone, provider_email, provider_address, provider_note)VALUES (?, ?, ?, ?, ?, ?)";
		Object[] params = { provider_id, name, phone, email, address, note };
		try {
			result = pgCon.execute(query, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	
	
	public int editProvider(String provider_id, String provider_name, String provider_phone, String provider_email,
			String provider_address, String provider_note) {

		int result = -1;
		String query = "UPDATE db_medicine_manage.tbl_provider SET  provider_name=?, provider_phone=?, provider_email=?, provider_address=?, provider_note=? WHERE provider_id=?";

		Object[] params = { provider_name, provider_phone, provider_email, provider_address, provider_note, provider_id };
		try {
			result = pgCon.execute(query, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public int providerCode() {
		int result = -1;
		String query = "select MAX(id) as code from tbl_provider";
		try {
			Object rs = pgCon.retrieveSingleField(query, null);
			result = (int) rs + 1;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}
	
	public int deleteProvider(String idProvider) {

		int result = -1;
		String query = "DELETE FROM db_medicine_manage.tbl_provider  WHERE provider_id = ?";
		Object[] params = { idProvider };
		try {
			result = pgCon.execute(query, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
