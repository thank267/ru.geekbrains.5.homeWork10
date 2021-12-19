package ru.geekbrains.homework10.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.homework10.dto.ProductDto;
import ru.geekbrains.homework10.services.CartService;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cart/v1")
@AllArgsConstructor
@Slf4j
@Validated
public class CartController {

	private final CartService service;

	@GetMapping
	public Map<ProductDto, Integer> index() {
		return service.getCart().entrySet()
				.stream()
				.collect(Collectors.toMap(
						e -> new ProductDto(e.getKey()),
						e-> e.getValue()
				));
	}

	@GetMapping("/add/{id}")
	public Map<ProductDto, Integer> add(@PathVariable("id") Long id) {
		return service.add(id).entrySet().stream().collect(Collectors.toMap(e -> new ProductDto(e.getKey()), e -> e.getValue()));
	}

	@GetMapping("/remove/{id}")
	public Map<ProductDto, Integer> remove(@PathVariable("id") Long id) {
		return service.remove(id).entrySet()
					.stream()
					.collect(Collectors.toMap(
							e -> new ProductDto(e.getKey()),
							e-> e.getValue()
					));
		}

	@GetMapping("/clear")
	public Map<ProductDto, Integer> clear() {
		return service.clear().entrySet()
				.stream()
				.collect(Collectors.toMap(
						e -> new ProductDto(e.getKey()),
						e-> e.getValue()
				));

	}
}
