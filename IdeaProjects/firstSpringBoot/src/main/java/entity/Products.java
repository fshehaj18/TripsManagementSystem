package entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productID;

    @Column
    private double sellPrice;

    @Column
    private double boughtPrice;

    @Column
    private String name;

    @Column
    private int quantity;

}
