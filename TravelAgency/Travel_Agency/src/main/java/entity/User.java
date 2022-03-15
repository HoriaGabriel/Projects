package entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
@Table(name="user")
public class User {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String password;

    @Column(unique=true, nullable=false)
    private String username;

    @ManyToMany
    @JoinTable(name="bookings",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="package_id"))
    private List<Vacation_Package> vacation_package;

    public User(){}

    public User(String firstName, String lastName, String username, String password)
    {
        this.firstName=firstName;
        this.lastName=lastName;
        this.username=username;
        this.password=password;
        this.vacation_package=null;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Vacation_Package> getVacation_package() {
        return vacation_package;
    }

    public Integer getId() {
        return id;
    }

    public void setVacation_package(List<Vacation_Package> vacation_package) {
        this.vacation_package = vacation_package;
    }
}
