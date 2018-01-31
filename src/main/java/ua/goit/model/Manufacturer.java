package ua.goit.model;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "MANUFACTURER")
public class Manufacturer {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idManufact;

    @Column(name = "NAME")
    private String nameManufact;

    @OneToMany(mappedBy = "manufacturer")
    private Set<Product> products;

    public Manufacturer() {
    }

    public Manufacturer(String nameManufact) {
        this.nameManufact = nameManufact;
    }

    public Manufacturer(Long idManufact, String nameManufact) {
        this.idManufact = idManufact;
        this.nameManufact = nameManufact;


    }

    public Manufacturer(Long idManufact) {
        this.idManufact = idManufact;
    }

    public Long getIdManufact() {
        return idManufact;
    }

    public void setIdManufact(Long idManufact) {
        this.idManufact = idManufact;
    }

    public String getNameManufact() {
        return nameManufact;
    }

    public void setNameManufact(String nameManufact) {
        this.nameManufact = nameManufact;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public Manufacturer withIdManufact(Long idManufact){
        this.idManufact = idManufact;
        return this;
    }

    public Manufacturer withNameManufact(String nameManufact){
        this.nameManufact = nameManufact;
        return this;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "idManufact =" + idManufact +
                ", nameManufact ='" + nameManufact + '\'' +

                ", projects='" +  (
                products == null
                        ? "[]"
                        : products.stream().map(Product::getNameProduct).collect(Collectors.toList())) + '\'' +
                '}';
    }
}
