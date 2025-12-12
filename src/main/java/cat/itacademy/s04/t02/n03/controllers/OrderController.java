package cat.itacademy.s04.t02.n03.controllers;

import cat.itacademy.s04.t02.n03.dto.OrderDTO;
import cat.itacademy.s04.t02.n03.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@RestController
 @RequestMapping("/orders")
public class OrderController{
	private final OrderService orderService;
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}
	@PostMapping
	public ResponseEntity<OrderDTO> addOrder(@Valid @RequestBody OrderDTO newOrderDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(orderService.addOrder(newOrderDTO));
	}
	@GetMapping
	public ResponseEntity<List<OrderDTO>> listOrders() {
		return ResponseEntity.status(HttpStatus.OK).body(orderService.listOrders());
	}
	@GetMapping("/{id}")
	public ResponseEntity<OrderDTO> getOrderById(@PathVariable String id) {
		return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderById(id));
	}
	@PutMapping("/{id}")
	public ResponseEntity<OrderDTO> updateOrderById(@PathVariable String id, @Valid @RequestBody OrderDTO newOrderDTO) {
		return ResponseEntity.status(HttpStatus.OK).body(orderService.updateOrderById(id, newOrderDTO));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteOrderById(@PathVariable String id) {
		orderService.deleteOrderById(id);
		return ResponseEntity.noContent().build();
	}
}
