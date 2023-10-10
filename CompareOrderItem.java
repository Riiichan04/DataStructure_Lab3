package lab3;

import java.util.Comparator;

//Remake
public class CompareOrderItem implements Comparator<OrderItem> {

	@Override
	public int compare(OrderItem o1, OrderItem o2) {
		return o1.getProduct().getId().compareTo(o2.getProduct().getId()); 
	}
	
}
