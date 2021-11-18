package entity;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int employeeID;

    @Column
    private String name;

    @Column
    private String role;


}
