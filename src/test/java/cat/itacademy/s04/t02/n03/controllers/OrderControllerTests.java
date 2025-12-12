package cat.itacademy.s04.t02.n03.controllers;

import cat.itacademy.s04.t02.n03.dto.OrderDTO;
import cat.itacademy.s04.t02.n03.dto.OrderItemDTO;
import cat.itacademy.s04.t02.n03.exceptions.OrderNotFoundException;
import cat.itacademy.s04.t02.n03.services.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private OrderService orderService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void addOrder_ShouldReturnCreated_WhenDataIsValid() throws Exception {
		OrderItemDTO item = new OrderItemDTO("Manzana", 5);
		OrderDTO inputDTO = new OrderDTO(null, "Joan", LocalDate.now().plusDays(1), List.of(item));
		OrderDTO savedDTO = new OrderDTO("mongo-id-123", "Joan", LocalDate.now().plusDays(1), List.of(item));
		Mockito.when(orderService.addOrder(Mockito.any(OrderDTO.class)))
				.thenReturn(savedDTO);
		mockMvc.perform(post("/orders")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(inputDTO)))
				.andExpect(status().isCreated()) // Espera 201
				.andExpect(jsonPath("$.id").value("mongo-id-123"))
				.andExpect(jsonPath("$.clientName").value("Joan"));
	}

	@Test
	void listOrders_ShouldReturnList() throws Exception {
		List<OrderDTO> orders = List.of(new OrderDTO("id1", "Joan", LocalDate.now(), List.of()));
		Mockito.when(orderService.listOrders()).thenReturn(orders);
		mockMvc.perform(get("/orders"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.size()").value(1));
	}

	@Test
	void getOrderById_ShouldReturnOrder_WhenIdExists() throws Exception {
		String id = "id1";
		OrderDTO order = new OrderDTO(id, "Joan", LocalDate.now(), List.of());

		Mockito.when(orderService.getOrderById(id)).thenReturn(order);

		mockMvc.perform(get("/orders/{id}", id))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(id));
	}

	@Test
	void getOrderById_ShouldReturnNotFound_WhenIdMissing() throws Exception {
		String id = "999";
		Mockito.when(orderService.getOrderById(id))
				.thenThrow(new OrderNotFoundException("Order not found"));

		mockMvc.perform(get("/orders/{id}", id))
				.andExpect(status().isNotFound());
	}

	@Test
	void updateOrderById_ShouldReturnOk_WhenValid() throws Exception {
		String id = "123";

		OrderItemDTO item = new OrderItemDTO("Orange", 5);

		OrderDTO updateInfo = new OrderDTO(null, "Joan Updated", LocalDate.now().plusDays(2), List.of(item));
		OrderDTO result = new OrderDTO(id, "Joan Updated", LocalDate.now().plusDays(2), List.of(item));

		Mockito.when(orderService.updateOrderById(Mockito.eq(id), Mockito.any(OrderDTO.class)))
				.thenReturn(result);

		mockMvc.perform(put("/orders/{id}", id)
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(updateInfo)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.clientName").value("Joan Updated"));
	}

	@Test
	void deleteOrderById_ShouldReturnNoContent() throws Exception {
		String id = "123";

		Mockito.doNothing().when(orderService).deleteOrderById(id);

		mockMvc.perform(delete("/orders/{id}", id))
				.andExpect(status().isNoContent()); // Espera 204
	}
}