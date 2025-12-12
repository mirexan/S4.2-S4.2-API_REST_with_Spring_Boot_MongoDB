package cat.itacademy.s04.t02.n03.model;

import cat.itacademy.s04.t02.n03.dto.OrderItemDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "orders")
public class Order {
	@Id
	private String id;
	private String clientName;
	private LocalDate deliveryDate;
	private List<OrderItem> items;

	public Order() {
	}

	public Order(String clientName, LocalDate deliveryDate, List<OrderItem> items) {
		this.clientName = clientName;
		this.deliveryDate = deliveryDate;
		this.items = items;
	}

	public Order(String id, String clientName, LocalDate deliveryDate, List<OrderItem> items) {
		this.id = id;
		this.clientName = clientName;
		this.deliveryDate = deliveryDate;
		this.items = items;
	}

	public String getId() {
		return id;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public List<OrderItem> getItems() {
		return this.items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
}
