package br.com.fiap.tastytap.domain.product;

import java.util.Optional;
import java.util.stream.Stream;

public enum Category {
    SANDWICH("Sanduiche"),
    SIDE_DISH("Acompanhamento"),
    DRINK("Bebida"),
    DESERT("Sobremesa");

    private final String name;

    Category(String description) {
        this.name = description;
    }

    public String getDescription() {
        return this.name;
    }


    public static Optional<Category> getByName(String name) {
        return Stream.of(Category.values()).filter(x -> x.name.equalsIgnoreCase(name) || x.name().equalsIgnoreCase(name))
                .findFirst();

    }
}