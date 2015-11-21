package navata.dto;

import java.sql.Date;

import navata.build.Constant;

public class DtoProvider {
	int id;
	String provider_id;
	String provider_name;
	String provider_phone;
	String provider_email;
	String provider_address;

	String provider_note;

	public DtoProvider(int id, String provider_id, String provider_name, String provider_phone, String provider_email,
			String provider_address, String provider_note) {
		super();
		this.id = id;
		this.provider_id = provider_id;
		this.provider_name = provider_name;
		this.provider_phone = provider_phone;
		this.provider_email = provider_email;
		this.provider_address = provider_address;
		this.provider_note = provider_note;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getprovider_id() {
		return provider_id;
	}

	public void setprovider_id(String provider_id) {
		this.provider_id = provider_id;
	}

	public String getprovider_name() {
		return provider_name;
	}

	public void setprovider_name(String provider_name) {
		this.provider_name = provider_name;
	}

	public String getprovider_phone() {
		return provider_phone;
	}

	public void setprovider_phone(String provider_phone) {
		this.provider_phone = provider_phone;
	}

	public String getprovider_email() {
		return provider_email;
	}

	public void setprovider_email(String provider_email) {
		this.provider_email = provider_email;
	}

	public String getprovider_address() {
		return provider_address;
	}

	public void setprovider_address(String provider_address) {
		this.provider_address = provider_address;
	}

	public String getprovider_note() {
		return provider_note;
	}

	public void setprovider_note(String provider_note) {
		this.provider_note = provider_note;
	}

	public String[] toArray() {

		return new String[] { String.valueOf(getId()), getprovider_id(), getprovider_name(), getprovider_phone(),
				getprovider_email(), getprovider_address(), getprovider_note() };

	}

}
