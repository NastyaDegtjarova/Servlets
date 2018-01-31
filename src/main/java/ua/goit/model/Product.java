package ua.goit.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "PRODUCT")
public class Product {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;

    @Column(name = "NAME")
    private String nameProduct;

    @Column(name = "PRICE")
    private BigDecimal priceProduct = BigDecimal.ZERO;

    @ManyToOne
    @JoinColumn(nullable=false, name="ID_MANUFACTURER")
    private Manufacturer manufacturer;

    public Product() {
    }

    public Product(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public Product(Long idProduct, String nameProduct) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
    }

    public Product(String nameProduct, BigDecimal priceProduct, Manufacturer manufacturer) {
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
        this.manufacturer = manufacturer;
    }

    public Product(Long idProduct) {
        this.idProduct = idProduct;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public BigDecimal getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(BigDecimal priceProduct) {
        this.priceProduct = priceProduct;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Product withIdProduct(Long idProduct){
        this.idProduct = idProduct;
        return this;
    }
    public Product withNameProduct(String nameProduct){
        this.nameProduct = nameProduct;
        return this;
    }

    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + idProduct +
                ", manufacturer='" + manufacturer +
                ", nameComp ='" + nameProduct + '\'' +
                '}';
    }

}
