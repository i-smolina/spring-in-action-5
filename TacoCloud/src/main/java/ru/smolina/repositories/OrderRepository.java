package ru.smolina.repositories;

import ru.smolina.domains.Order;

public interface OrderRepository {
	Order save(Order order);
}
