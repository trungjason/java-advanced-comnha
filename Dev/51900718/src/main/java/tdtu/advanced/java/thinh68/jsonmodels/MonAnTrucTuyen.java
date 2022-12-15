package tdtu.advanced.java.thinh68.jsonmodels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MonAnTrucTuyen {
	private int foodID;
	private int quantity;
	private double price;
	
}
