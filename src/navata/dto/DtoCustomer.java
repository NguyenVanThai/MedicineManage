package navata.dto;

import java.sql.Date;

import navata.build.Constant;

public class DtoCustomer {
	int id;
	String customer_id;
	String customer_name;
	String customer_phone;
	String customer_email;
	String customer_address;
	Date customer_birthday;
	String customer_note;

	public DtoCustomer(int id, String customer_id, String customer_name, String customer_phone, String customer_email,
			String customer_address, Date customer_birthday, String customer_note) {
		super();
		this.id = id;
		this.customer_id = customer_id;
		this.customer_name = customer_name;
		this.customer_phone = customer_phone;
		this.customer_email = customer_email;
		this.customer_address = customer_address;
		this.customer_birthday = customer_birthday;
		this.customer_note = customer_note;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getCustomer_phone() {
		return customer_phone;
	}

	public void setCustomer_phone(String customer_phone) {
		this.customer_phone = customer_phone;
	}

	public String getCustomer_email() {
		return customer_email;
	}

	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}

	public String getCustomer_address() {
		return customer_address;
	}

	public void setCustomer_address(String customer_address) {
		this.customer_address = customer_address;
	}

	public Date getCustomer_birthday() {
		return customer_birthday;
	}

	public void setCustomer_birthday(Date customer_birthday) {
		this.customer_birthday = customer_birthday;
	}

	public String getCustomer_note() {
		return customer_note;
	}

	public void setCustomer_note(String customer_note) {
		this.customer_note = customer_note;
	}

	public String[] toArray() {

		String birthday;
		// if (getBirthday().equals(null)) {
		// birthday = "";
		// } else {
		// birthday = getBirthday().toString();
		// }
		try {
			birthday = getCustomer_birthday().toString();
		} catch (Exception e) {
			birthday = "";
		}
		// System.out.println(getBirthday() + "");
		return new String[] { String.valueOf(getId()), getCustomer_id(), getCustomer_name(), getCustomer_phone(),
				getCustomer_email(), getCustomer_address(), birthday, getCustomer_note() };

	}

}
