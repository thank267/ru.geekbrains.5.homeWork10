package ru.geekbrains.homework10.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.geekbrains.homework10.entities.Product;
import ru.geekbrains.homework10.exceptions.ResourceNotFoundException;
import ru.geekbrains.homework10.repositories.ProductRepository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Scope("prototype")
@Slf4j
public class Cart {

	private final Map<Product, Integer> cart = new HashMap<>();

	private final ProductRepository productRepository;

	public Cart(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public Map<Product, Integer> add(Long id) {

		Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Unable add to Cart Product by id: "+ id ));
		Integer count = Optional.ofNullable(cart.computeIfPresent(product,(key, value) -> ++value)).orElse(1);
		cart.putIfAbsent(product,count);

		return getGart();

	}

	public Map<Product, Integer> remove(Long id) {

		Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Unable delete from Cart Product by id: "+ id ));
		Integer count = Optional.ofNullable(cart.computeIfPresent(product,(key, value) -> --value)).orElse(0);
		if (count<=0) {
			cart.remove(product,count);
		}

		return getGart();
	}

	public Map<Product, Integer> getGart() {

		return Collections.unmodifiableMap(cart);

	}

	public Map<Product, Integer> clear() {

		cart.clear();
		return getGart();

	}

	@Override
	public String toString() {
		return  cart.keySet().stream()
				.map(key -> key + " count: " + cart.get(key))
				.collect(Collectors.joining(", ", "Cart"+this.hashCode()+" {", "}"));

	}

}
