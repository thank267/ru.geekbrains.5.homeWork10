package ru.geekbrains.homework10.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.geekbrains.homework10.component.Cart;
import ru.geekbrains.homework10.entities.Product;

import java.util.Map;

@Service
@Getter
@Setter
@Slf4j
@AllArgsConstructor
public class CartService {

	private final Cart cart;

	public Map<Product, Integer> add(Long id) {

		return cart.add(id);

	}

	public Map<Product, Integer> remove(Long id) {

		return cart.remove(id);
	}

	public Map<Product, Integer> getCart() {

		return cart.getGart();

	}

	public Map<Product, Integer> clear() {

		return cart.clear();

	}

}
