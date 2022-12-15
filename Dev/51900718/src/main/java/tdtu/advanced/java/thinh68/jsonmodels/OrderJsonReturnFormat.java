package tdtu.advanced.java.thinh68.jsonmodels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import tdtu.advanced.java.thinh68.models.JsonResponseFormat;


public class OrderJsonReturnFormat extends JsonResponseFormat {
	private int code;
	private Long billID;
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Long getBillID() {
		return billID;
	}

	public void setBillID(Long billID) {
		this.billID = billID;
	}

	public OrderJsonReturnFormat(Boolean status, String message, int code, long billID) {
		super(status, message);
		this.code = code;
		this.billID = billID;
	}
	
	public OrderJsonReturnFormat(Boolean status, String message, int code) {
		super(status, message);
		this.code = code;
	}

	@Override
	public String toString() {
		return "OrderJsonReturnFormat [code=" + code + ", billID=" + billID + ", getStatus()=" + getStatus()
				+ ", getMessage()=" + getMessage() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
	
}
