package cat.itacademy.s04.t02.n03.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record OrderItemDTO (
		@NotBlank(message = "Fruit's Name is mandatory")
		String fruitName,
		@Positive(message = "Quantity must be a positive number")
		int quantityInKilos
){
}
