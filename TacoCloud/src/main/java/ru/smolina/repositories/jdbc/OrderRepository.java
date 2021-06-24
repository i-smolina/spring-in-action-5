package ru.smolina.repositories.jdbc;

import ru.smolina.domains.Order;

public interface OrderRepository {
	Order save(Order order);
}
