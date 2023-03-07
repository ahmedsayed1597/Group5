package com.example.demo.repository.entities;
// Generated Mar 7, 2023, 7:21:08 PM by Hibernate Tools 6.2.0.CR1


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * OrderProducts generated by hbm2java
 */
@Entity
@Table(name="order_products"
    ,catalog="flamingoo"
)
public class OrderProducts  implements java.io.Serializable {


     private int orderDetailsId;
     private OrderDetails orderDetails;
     private Product product;
     private String quantityOrdered;

    public OrderProducts() {
    }

	
    public OrderProducts(OrderDetails orderDetails, Product product) {
        this.orderDetails = orderDetails;
        this.product = product;
    }
    public OrderProducts(OrderDetails orderDetails, Product product, String quantityOrdered) {
       this.orderDetails = orderDetails;
       this.product = product;
       this.quantityOrdered = quantityOrdered;
    }
   
     @GenericGenerator(name="OrderProductsIdGenerator", strategy="foreign")@Id @GeneratedValue(generator="OrderProductsIdGenerator")

    
    @Column(name="order_details_id", unique=true, nullable=false)
    public int getOrderDetailsId() {
        return this.orderDetailsId;
    }
    
    public void setOrderDetailsId(int orderDetailsId) {
        this.orderDetailsId = orderDetailsId;
    }

@OneToOne(fetch=FetchType.LAZY)@PrimaryKeyJoinColumn
    public OrderDetails getOrderDetails() {
        return this.orderDetails;
    }
    
    public void setOrderDetails(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="product_id", nullable=false)
    public Product getProduct() {
        return this.product;
    }
    
    public void setProduct(Product product) {
        this.product = product;
    }

    
    @Column(name="quantity_ordered", length=45)
    public String getQuantityOrdered() {
        return this.quantityOrdered;
    }
    
    public void setQuantityOrdered(String quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
    }




}


