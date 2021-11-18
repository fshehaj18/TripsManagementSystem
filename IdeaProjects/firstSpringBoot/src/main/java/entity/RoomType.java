package entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class RoomType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int typeID;

    @Column
    private int price;

    @Column
    private String name;

    @OneToMany(mappedBy = "RoomType")
    private List<Room> rooms;
}
