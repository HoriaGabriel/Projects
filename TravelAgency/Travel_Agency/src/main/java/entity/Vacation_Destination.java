package entity;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="vacation_destination")
public class Vacation_Destination {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String destinationName;

    @OneToMany(mappedBy="vacation_destination",cascade = CascadeType.ALL)
    private List<Vacation_Package> vacation_package;

    public Vacation_Destination(){}

    public Vacation_Destination(String name){
        this.destinationName=name;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public List<Vacation_Package> getVacation_package() {
        return vacation_package;
    }
}
