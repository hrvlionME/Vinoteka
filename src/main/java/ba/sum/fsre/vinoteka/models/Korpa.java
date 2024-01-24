package ba.sum.fsre.vinoteka.models;

import jakarta.persistence.*;


@Entity
public class Korpa {
    @Id
    @GeneratedValue
    private long id;


    @ManyToOne
    @JoinColumn(name = "vino_id")
    private Vino vino;

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

    public Vino getVino() {
        return vino;
    }

    public void setVino(Vino vino) {
        this.vino = vino;
    }
}
