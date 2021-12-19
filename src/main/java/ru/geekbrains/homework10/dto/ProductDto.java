package ru.geekbrains.homework10.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.geekbrains.homework10.entities.Product;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class ProductDto {

	private Long id;

	private String title;

	private int cost;

	public ProductDto(Product product) {
		id = product.getId();
		title = product.getTitle();
		cost = product.getCost();
	}

}
