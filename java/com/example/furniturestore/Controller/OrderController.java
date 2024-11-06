package com.example.furniturestore.Controller;

import com.example.furniturestore.Entity.Order;
import com.example.furniturestore.Entity.OrderRequest;
import com.example.furniturestore.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    //xác nhận thanh toán
    @PostMapping("/user/orders")
    public Order createOrder(@RequestBody OrderRequest request) {
        // Tạo đơn hàng từ request
        Order order = new Order();
        order.setName(request.getName());
        order.setPhone(request.getPhone());
        order.setEmail(request.getEmail());
        order.setAddress(request.getAddress());
        order.setNotes(request.getNotes());
        order.setTotalPrice(request.getTotalPrice());
        order.setStatus(request.getStatus());

        // Lưu và trả về đối tượng Order đã tạo
        return orderService.createOrder(order, request.getOrderItems());
    }

    //lấy danh sach hóa dớn
    @GetMapping("/admin/orders/list")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    //  lấy chi tiết hóa đơn theo ID
    @GetMapping("/admin/orders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Optional<Order> order = orderService.getOrderById(id);
        if (order.isPresent()) {
            return ResponseEntity.ok(order.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    //  lấy các sản phẩm trong đơn hàng theo ID
//    @GetMapping("/{id}/items")
//    public List<OrderItem> getOrderItemsByOrderId(@PathVariable Long id) {
//        return orderService.getOrderItemsByOrderId(id);
//    }
}
