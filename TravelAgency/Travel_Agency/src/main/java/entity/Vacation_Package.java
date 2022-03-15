package entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
@Table(name="vacation_package")
public class Vacation_Package {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String packageName;

    @Column
    private Integer price;

    @Column
    private Integer period;

    @Column
    private String extraDetails;

    @Column
    private Integer availablePlaces;

    @Column
    private Status status;

    @ManyToOne
    @JoinColumn(name="dest_id")
    private Vacation_Destination vacation_destination;

    @ManyToMany(mappedBy="vacation_package")
    private List<User> user;

    public Vacation_Package() { }

    public Vacation_Package(String s1, Integer i1, Integer i2, Integer i3, String s2, Vacation_Destination vc){

        this.packageName=s1;
        this.availablePlaces=i1;
        this.period=i2;
        this.price=i3;
        this.extraDetails=s2;
        this.vacation_destination=vc;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getPackageName() {
        return packageName;
    }

    public Integer getId() {
        return id;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getPeriod() {
        return period;
    }

    public String getExtraDetails() {
        return extraDetails;
    }

    public Integer getAvailablePlaces() {
        return availablePlaces;
    }

    public void setAvailablePlaces(Integer availablePlaces) {
        this.availablePlaces = availablePlaces;
    }

    public Vacation_Destination getVacation_destination() {
        return vacation_destination;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }


}
