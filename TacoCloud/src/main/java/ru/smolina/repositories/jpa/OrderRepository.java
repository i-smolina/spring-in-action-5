package ru.smolina.repositories.jpa;

import org.springframework.data.repository.CrudRepository;

import ru.smolina.domains.Order;

public interface OrderRepository extends CrudRepository<Order, Long>{

}
