package navata.build;

public class Constant {
	public static enum ERROR {
		NAME("Tên khách hàng không được bỏ trống"), PHONE("Số điện thoại không hợp lệ"), EMAIL("Email không hợp lệ");

		private String content;

		ERROR(String content) {
			setContent(content);
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}
	}

	public static enum CONTENT {
		NAME("Nhập tên khách hàng (bắt buộc)"), BIRTHDAY("dd/mm/yy"), EMAIL(
				"Nhập email (Ví dụ: thaivannguyen.it@gmail.com)"), KH("KH"), EDIT("edit"), ADD("add");
		private String content;

		CONTENT(String content) {
			setContent(content);
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}
	}

	public static enum ARRAY {
		CUSTOMER(new String[] { "ID", "Mã KH", "Tên KH", "Số điện thoại", "Email", "Địa chỉ", "Ngày sinh",
				"Ghi chú" }), PROVIDER(
						new String[] { "ID", "Mã KH", "Tên KH", "Số điện thoại", "Email", "Địa chỉ", "Ghi chú" });

		private String[] array;

		private ARRAY(String[] array) {
			this.setArray(array);
		}

		public String[] getArray() {
			return array;
		}

		public void setArray(String[] array) {
			this.array = array;
		}

	}

}
