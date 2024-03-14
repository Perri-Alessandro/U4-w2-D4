package perri.practice.Entities;

import java.time.LocalDate;
import java.util.Random;

public class Order {
    private long id;

    private String status;

    private Product product;

    private Customer customer;

    private LocalDate orderDate;

    private LocalDate deliveryDate;

    public Order(String status, Customer customer, Product product, LocalDate orderDate, LocalDate deliveryDate) {
        Random idd = new Random();
        this.id = idd.nextInt(10000, 99000);
        this.product = product;
        this.status = status;
        this.customer = customer;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
    }

    public long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public Product getProduct() {
        return product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", product=" + product +
                ", customer=" + customer +
                ", orderDate=" + orderDate +
                ", deliveryDate=" + deliveryDate +
                '}';
    }
}
