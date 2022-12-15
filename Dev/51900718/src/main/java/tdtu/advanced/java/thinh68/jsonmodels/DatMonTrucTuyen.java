package tdtu.advanced.java.thinh68.jsonmodels;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import tdtu.advanced.java.thinh68.models.MonAn;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DatMonTrucTuyen {
	private String name;
	private String memberID;
	private String phone;
	private String email;
	private String address;
	private List<MonAnTrucTuyen> orderFood;
	private String message;
	private Boolean paymentComNhaValid;
    
	private double tempPrice;
	private double discountFee;
	private double shippingFee;
	private double totalPrice;
}
