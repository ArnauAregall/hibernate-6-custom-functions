package tech.aaregall.lab.hibernate6.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String sku;
    private String name;
    private Float price;
    private String[] tags;

    protected Product() {
    }

    public Product(String sku, String name, Float price, String[] tags) {
        this.sku = sku;
        this.name = name;
        this.price = price;
        this.tags = tags;
    }

    public UUID getId() {
        return id;
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public Float getPrice() {
        return price;
    }

    public String[] getTags() {
        return tags;
    }
}