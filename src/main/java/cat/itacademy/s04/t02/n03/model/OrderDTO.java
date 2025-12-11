package cat.itacademy.s04.t02.n03.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class OrderDTO {
	@NotBlank(message = "The client's name is mandatory")
	private String clientName;
	@NotNull(message = "Date is mandatory")
	@Future(message = "The date must be later than today")
	private LocalDate deliveryDate;
	@NotEmpty(message = "The order must have at least one fruit")
	@Valid
	private List<OrderItem> items;

	public OrderDTO() {
	}
	public OrderDTO(String clientName, LocalDate deliveryDate, List<OrderItem> items) {
		this.clientName = clientName;
		this.deliveryDate = deliveryDate;
		this.items = items;
	}

	public String getClientName() {
		return this.clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public  LocalDate getDeliveryDate() {
		return this.deliveryDate;
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
