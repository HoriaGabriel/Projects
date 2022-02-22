package library.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "member")
public class Person extends AbstractEntity {

    @Column(name = "Name")
    private String Name;
    @Column(name = "Email")
    private String Email;
    @Column(name = "Section")
    private String Section;

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSection() {
        return Section;
    }

    public void setSection(String section) {
        Section = section;
    }
}
