package cat.itacademy.s04.t02.n03.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

	public Order(String id, String clientName, LocalDate deliveryDate, List<OrderItem> items) {
		this.id = id;
		this.clientName = clientName;
		this.deliveryDate = deliveryDate;
		this.items = items;
	}
}
