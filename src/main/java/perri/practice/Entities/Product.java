package perri.practice.Entities;

import java.util.Random;

public class Product {
    public long id;

    public String name;

    public String category;

    public double price;

    public Product(String name, String category, double price) {
        Random idd = new Random();
        this.id = idd.nextInt(10000, 99000);
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public double getPrice() {
        return this.price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                '}';
    }
}
