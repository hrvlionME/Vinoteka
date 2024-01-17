package ba.sum.fsre.vinoteka.models;
import jakarta.persistence.Transient;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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

    //String godinaBerbe;
    public Vino(){}

     public Vino(String naziv,String proizvodjac,String podrucje,String sorta){
         this.naziv=naziv;
         this.podrucje=podrucje;
         this.proizvodjac=proizvodjac;
         this.sorta=sorta;
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
}


