package com.navas.orderservice.service;

import com.navas.orderservice.dto.OrderRequest;
import com.navas.orderservice.model.Order;
import com.navas.orderservice.model.OrderLineItem;
import com.navas.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest) {

        List<OrderLineItem> orderLineItems = orderRequest
                .getOrderLineItemsDTOList()
                .stream()
                .map(orderLineItemDTO -> OrderLineItem.builder()
                        .id(orderLineItemDTO.getId())
                        .price(orderLineItemDTO.getPrice())
                        .skuCode(orderLineItemDTO.getSkuCode())
                        .quantity(orderLineItemDTO.getQuantity())
                        .build()).toList();

        // TODO: Implement mapper
        Order order = Order
                .builder()
                .orderNumber(UUID.randomUUID().toString())
                .orderLineItemList(orderLineItems)
                .build();

        orderRepository.save(order);


    }
}
