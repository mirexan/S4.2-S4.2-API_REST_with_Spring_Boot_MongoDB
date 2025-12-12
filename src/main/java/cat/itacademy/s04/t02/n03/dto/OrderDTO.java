package cat.itacademy.s04.t02.n03.dto;

import cat.itacademy.s04.t02.n03.model.OrderItem;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record OrderDTO (
		String id,
	@NotBlank(message = "The client's name is mandatory")
	String clientName,
	@NotNull(message = "Date is mandatory")
	@Future(message = "The date must be later than today")
	LocalDate deliveryDate,
	@NotEmpty(message = "The order must have at least one fruit")
	@Valid
	List<OrderItemDTO> items
){
}
