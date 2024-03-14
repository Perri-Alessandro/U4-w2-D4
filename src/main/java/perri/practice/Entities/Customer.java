package perri.practice.Entities;

import java.util.Random;

public class Customer {
    private long id;
    private String name;

    private int tier;

    public Customer(String name) {
        Random idd = new Random();
        this.id = idd.nextInt(10000, 99000);
        this.name = name;
        Random tierr = new Random();
        this.tier = tierr.nextInt(1, 5);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getTier() {
        return tier;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tier=" + tier +
                '}';
    }
}
