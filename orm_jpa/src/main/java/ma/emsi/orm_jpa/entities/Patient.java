package ma.emsi.orm_jpa.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity // for it to be an entity
@Data // using the library lombok it will automatically generate getters and setters
@NoArgsConstructor // un constructeur sans parametre
@AllArgsConstructor // un constructeur avec tt les parametres
public class Patient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // we're created an entity JPA
    private Long id;
    @Column(length = 50)
    private String nom;
    @Temporal(TemporalType.DATE)
    private Date date;
    private boolean malade;
    private int score;
}
