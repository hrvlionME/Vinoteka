package ba.sum.fsre.vinoteka.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Size(min=2, max=20, message = "Ime mora biti 2-10 slova(znakova).")
    @NotBlank(message="Ime je obvezno!")
    String ime;

    @Size(min=3, max=20, message = "Prezime mora biti 3-20 slova(znakova).")
    @NotBlank(message="Prezime je obvezno!")
    String prezime;

    @NotBlank(message = "Adresa je obavezna")
    String adresa;

    @NotBlank(message="E-mail adresa je obvezna!")
    @Email(message = "E-mail adresa mora biti ispravnog formata.")
    String email;


    @NotBlank(message = "Unesite lozinku")
    String lozinka;

    String datum;


    @OneToMany(mappedBy="user")
    private List<Korpa> korpe;

    @NotBlank(message = "Potvrdite lozinku")
    @Transient
    String potvrdaLozinke;
public User(){}


    public User(Long id, String ime, String prezime, String adresa, String email, String datum, String lozinka , String potvrdaLozinke) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.adresa=adresa;
        this.email = email;
        this.lozinka =lozinka ;
        this.potvrdaLozinke = potvrdaLozinke;
        this.datum=datum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getPotvrdaLozinke() {
        return potvrdaLozinke;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }
    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public void setPotvrdaLozinke(String potvrdaLozinke) {
        this.potvrdaLozinke = potvrdaLozinke;
    }

    @AssertTrue(message = "Lozinke se moraju podudarati")
    public boolean isPasswordsEqual(){
        try {
            System.out.println(this.lozinka);
            System.out.println(this.potvrdaLozinke);
            return this.lozinka.equals(this.potvrdaLozinke);
        } catch (Exception e){
            return false;
        }
    }
}