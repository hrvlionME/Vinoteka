package ba.sum.fsre.vinoteka.models;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;

@Entity
public class Vino {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message="Naziv je obvezan!")
    String naziv;

    @NotBlank(message="Ime proizvođača je obvezno!")
    String proizvodjac;

    @NotBlank(message="Polje sorta je obvezno!")
    String sorta;

    @NotBlank(message="Polje područje je obvezno!")
    String podrucje;

    double cijena;

    String lokacijaSlike;

    @OneToMany(mappedBy = "vino")
    @Transient
    private List<Korpa> korpe;


    public Vino(){}

     public Vino(String naziv,String proizvodjac,String podrucje,String sorta, String lokacijaSlike, double cijena){
         this.naziv=naziv;
         this.podrucje=podrucje;
         this.proizvodjac=proizvodjac;
         this.sorta=sorta;
         this.lokacijaSlike = lokacijaSlike;
         this.cijena = cijena;
     }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getProizvodjac() {
        return proizvodjac;
    }

    public void setProizvodjac(String proizvodjac) {
        this.proizvodjac = proizvodjac;
    }

    public String getSorta() {
        return sorta;
    }

    public void setSorta(String sorta) {
        this.sorta = sorta;
    }

    public String getPodrucje() {
        return podrucje;
    }

    public void setPodrucje(String podrucje) {
        this.podrucje = podrucje;
    }

    public List<Korpa> getKorpe() {
        return korpe;
    }

    public void setKorpe(List<Korpa> korpe) {
        this.korpe = korpe;
    }

    public String getLokacijaSlike() {
        return lokacijaSlike;
    }

    public void setLokacijaSlike(String lokacijaSlike) {
        this.lokacijaSlike = lokacijaSlike;
    }

    public double getCijena() {
        return cijena;
    }

    public void setCijena(double cijena) {
        this.cijena = cijena;
    }

}


