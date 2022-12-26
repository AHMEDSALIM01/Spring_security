package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "product")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productId")
    private Long id;
    @Column(name = "refProduct",nullable = false,unique = true)
    private String ref;
    @Column(name = "nom")
    private String nom;
    @Column(name = "category")
    private String category;
    @Column(name = "descreption")
    private String descreption;

    @Column(name = "price")
    private Double price;
    @Column(name = "quantity")
    private int quantity;

    @OneToMany(mappedBy = "refProduct",fetch = FetchType.EAGER)
    private List<OrderItem> orderItems;


    public Product(String ref, String nom, String category, String descreption, Double price, int quantity, List<OrderItem> orderItems) {

        this.ref = ref;
        this.nom = nom;
        this.category = category;
        this.descreption = descreption;
        this.price = price;
        this.quantity = quantity;
        this.orderItems = orderItems;
    }

    public Product() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescreption() {
        return descreption;
    }

    public void setDescreption(String descreption) {
        this.descreption = descreption;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return quantity == product.quantity && Objects.equals(ref, product.ref) && Objects.equals(nom, product.nom) && Objects.equals(category, product.category) && Objects.equals(descreption, product.descreption) && Objects.equals(price, product.price) && Objects.equals(orderItems, product.orderItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ref, nom, category, descreption, price, quantity, orderItems);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", ref='" + ref + '\'' +
                ", nom='" + nom + '\'' +
                ", category='" + category + '\'' +
                ", descreption='" + descreption + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", orderItems=" + orderItems +
                '}';
    }
}
