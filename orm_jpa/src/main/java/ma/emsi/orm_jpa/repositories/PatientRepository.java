package ma.emsi.orm_jpa.repositories;

import ma.emsi.orm_jpa.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

// <entity qu'on souhaite gerer , le type de l'Id> , " on a terminer ORM : object-relational mapping "
public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByMalade(boolean m);

    Page<Patient> findByMalade(boolean m, Pageable pageable);

    List<Patient> findByMaladeAndScoreLessThan(boolean m, int s);

    List<Patient> findByMaladeIsTrueAndScoreLessThan(int s);

    List<Patient> findByDateBetweenAndMaladeIsTrueOrNomIsLike(Date d1, Date d2, String mc);

    @Query("select p from Patient p where p.nom like :x and p.score < :y")
    List<Patient> chercherPatients(@Param("x") String nom, @Param("y") int score);
}
