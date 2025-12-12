package cat.itacademy.s04.t02.n03.services;

import cat.itacademy.s04.t02.n03.dto.OrderDTO;

import java.util.List;

public interface OrderService {
	OrderDTO addOrder(OrderDTO newOrderDTO);
	List<OrderDTO> listOrders();
	OrderDTO getOrderById(String id);
	OrderDTO updateOrderById(String id, OrderDTO newOrderDTO);
	void deleteOrderById(String id);
}
