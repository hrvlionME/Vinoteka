package ba.sum.fsre.vinoteka.models;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

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

    String lokacijaSlike;

    @OneToOne(mappedBy = "vino")
    private Korpa korpa;

    //String godinaBerbe;
    public Vino(){}

     public Vino(String naziv,String proizvodjac,String podrucje,String sorta, String lokacijaSlike){
         this.naziv=naziv;
         this.podrucje=podrucje;
         this.proizvodjac=proizvodjac;
         this.sorta=sorta;
         this.lokacijaSlike = lokacijaSlike;
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

    public Korpa getKorpa() {
        return korpa;
    }

    public void setKorpa(Korpa korpa) {
        this.korpa = korpa;
    }

    public String getLokacijaSlike() {
        return lokacijaSlike;
    }

    public void setLokacijaSlike(String lokacijaSlike) {
        this.lokacijaSlike = lokacijaSlike;
    }
}


