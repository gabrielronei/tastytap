package br.com.fiap.tastytap.domain.product;

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
}