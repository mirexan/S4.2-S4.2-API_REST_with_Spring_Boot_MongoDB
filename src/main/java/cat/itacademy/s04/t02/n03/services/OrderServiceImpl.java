package cat.itacademy.s04.t02.n03.services;

import cat.itacademy.s04.t02.n03.dto.OrderDTO;
import cat.itacademy.s04.t02.n03.dto.OrderItemDTO;
import cat.itacademy.s04.t02.n03.exceptions.OrderNotFoundException;
import cat.itacademy.s04.t02.n03.model.Order;
import cat.itacademy.s04.t02.n03.model.OrderItem;
import cat.itacademy.s04.t02.n03.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
	private OrderRepository orderRepository;

	public OrderServiceImpl(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Override
	public OrderDTO addOrder(OrderDTO newOrderDTO) {
		Order newOrder = new Order(
				null,
				newOrderDTO.clientName(),
				newOrderDTO.deliveryDate(),
				mapOrderItem(newOrderDTO));
		return OrderToOrderDTO(orderRepository.save(newOrder));
	}
	@Override
	public List<OrderDTO> listOrders() {
		return orderRepository.findAll()
				.stream()
				.map(this::OrderToOrderDTO)
				.toList();
	}
	@Override
	public OrderDTO getOrderById(String id) {
		return orderRepository
				.findById(id)
				.map(this::OrderToOrderDTO)
				.orElseThrow(()->new OrderNotFoundException("Order not found"));
	}
	@Override
	public OrderDTO updateOrderById(String id, OrderDTO newOrderDTO) {
		Order foundOrder = orderRepository.findById(id)
				.orElseThrow(()->new OrderNotFoundException("Order not found"));
		foundOrder.setClientName(newOrderDTO.clientName());
		foundOrder.setDeliveryDate(newOrderDTO.deliveryDate());
		foundOrder.setItems(mapOrderItem(newOrderDTO));
		return OrderToOrderDTO(orderRepository.save(foundOrder));
	}
	@Override
	public void deleteOrderById(String id) {
		Order foundOrder = orderRepository.findById(id)
				.orElseThrow(()->new OrderNotFoundException("Order not found"));
		orderRepository.delete(foundOrder);
	}
	public List<OrderItemDTO> mapOrderItemDTO(Order order) {
		return order.getItems()
				.stream()
				.map(item-> new OrderItemDTO(item.getFruitName(), item.getQuantityInKilos()))
				.toList();
	}
	public List<OrderItem> mapOrderItem(OrderDTO newOrderDTO) {
		return newOrderDTO.items()
				.stream()
				.map(dto-> new OrderItem(dto.fruitName(),dto.quantityInKilos()))
				.toList();
	}
	public OrderDTO OrderToOrderDTO(Order order){
		return new OrderDTO(
				order.getId(),
				order.getClientName(),
				order.getDeliveryDate(),
				mapOrderItemDTO(order)
		);
	}
}
