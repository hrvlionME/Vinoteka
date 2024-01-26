package ba.sum.fsre.vinoteka.repositories;

import ba.sum.fsre.vinoteka.models.Korpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface KorpaRepository extends JpaRepository<Korpa, Long> {
    @Modifying
    @Query("DELETE FROM Korpa k WHERE k.vino.id = :vinoId")
    void deleteByVinoId(@Param("vinoId") Long vinoId);

}