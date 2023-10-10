package lab3;

import java.util.*;

public class Order {
	private OrderItem[] items;
	
	public Order(OrderItem[] items) {
		this.items = items;
	}

	public double cost() {
		double sum = 0;
		for (OrderItem element: items) {
			sum += element.getProduct().getPrice();
		}
		return sum;
	}

	public boolean contains(Product p) {
		Arrays.sort(items, new Comparator<OrderItem>() {
			@Override
			public int compare(OrderItem o1, OrderItem o2) {
				return o1.getProduct().getId().compareTo(o2.getProduct().getId()); 
			}
		});
		return sreachProduct(p, 0, items.length);
	}

	public boolean sreachProduct(Product p, int low, int high) {
		if (low > high) return false;
		else {
			int mid = (low + high)/2;
			int compare = items[mid].compareTo(p);
			if (compare == 0) {
				return true;
			}
//			Target > mid
			else if (compare > 0) {
				return sreachProduct(p, mid+1, high);
			}
//			Target < mid
			else return sreachProduct(p, low, mid-1);
		}
	}
	
	public Product[] filter(String type) {
		boolean[] listIndex = new boolean[items.length];
		int cursor = 0;
		for (int i = 0; i < items.length; i++) {
			if (items[i].getProduct().getType().equals(type)) {
				listIndex[i] = true;
				cursor++;
			}
			else listIndex[i] = false;
		}
		Product[] result = new Product[cursor];
		cursor = 0;
		for (int i = 0; i < items.length; i++) {
			if (listIndex[i] == true) {
				result[cursor++] = items[i].getProduct();
			}
		}
		
		return result;
	}
	
	
	public static void main(String[] args) {
		Product[] list = { new Product("AAA", "A", 0.2, "B"), new Product("BBB", "BB", 1.2, "B"), new Product("101", "CC", 2.3, "C")};
		
		int[] listQuantity = {12, 13, 4};
		
		OrderItem[] listItem = new OrderItem[list.length];
		
		for (int i = 0; i < list.length; i++) {
			listItem[i] = new OrderItem(list[i], listQuantity[i]);
		}
 		
		Order order = new Order(listItem);
		
		System.out.println(order.contains(new Product("BBB", "BB", 1.2, "B")));
		System.out.println(order.filter("B").length);
	}
}
