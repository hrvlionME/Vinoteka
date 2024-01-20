package ba.sum.fsre.vinoteka.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Korpa {
    @Id
    @GeneratedValue
    private long id;

    @OneToMany(mappedBy = "korpa")
    private List<Vino> vina;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Vino> getVina() {
        return vina;
    }

    public void setVina(List<Vino> vina) {
        this.vina = vina;
    }
}
