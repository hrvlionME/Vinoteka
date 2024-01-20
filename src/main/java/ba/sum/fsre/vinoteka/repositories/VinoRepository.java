package ba.sum.fsre.vinoteka.repositories;
import ba.sum.fsre.vinoteka.models.Vino;
import org.springframework.data.jpa.repository.JpaRepository;
public interface VinoRepository extends JpaRepository<Vino, Long>  {
}
