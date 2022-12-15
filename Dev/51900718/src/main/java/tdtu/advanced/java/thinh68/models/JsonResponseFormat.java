package tdtu.advanced.java.thinh68.models;

public class JsonResponseFormat {

	private Boolean status;
    private String message;

    public JsonResponseFormat(Boolean status, String message) { 
       this.status = status;
       this.message = message;
    }

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

    
}