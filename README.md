# Projet Object Relational Mapping avec Spring Data JPA

Ce projet utilise Spring Data JPA pour interagir avec une base de données relationnelle. Nous avons une classe `Patient` qui représente une entité dans notre système. Voici pourquoi nous utilisons les annotations spécifiques :

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String nom;

    @Temporal(TemporalType.DATE)
    private Date date;

    private boolean malade;
    private int score;
}

- ## `@Entity`: Cette annotation indique que la classe `Patient` est une entité JPA, ce qui signifie qu'elle sera persistée dans la base de données.
- ## `@Data`: C'est une annotation de Lombok qui génère automatiquement les méthodes `toString`, `equals`, `hashCode`, ainsi que les getters et setters pour tous les champs de la classe.
- ## `@NoArgsConstructor` et `@AllArgsConstructor`: Ces annotations de Lombok génèrent des constructeurs sans argument et avec tous les arguments respectivement.

Ensuite, nous avons une interface `PatientRepository` qui étend `JpaRepository` :

## public interface PatientRepository extends JpaRepository<Patient, Long> {
    // Méthodes personnalisées si nécessaire
## }


Cette interface permet d'effectuer des opérations CRUD sur l'entité `Patient`.

Enfin, dans la classe principale `OrmJpaApplication`, nous avons une méthode `run` qui est exécutée au démarrage de l'application. Cette méthode utilise l'injection de dépendance pour obtenir une instance de `PatientRepository` et effectue différentes opérations telles que l'insertion de données aléatoires, la recherche de patients, la mise à jour et la suppression.

## Utilisation

Pour utiliser ce projet :

1. Assurez-vous que votre environnement de développement est configuré avec Spring Boot et les dépendances nécessaires.
2. Exécutez la classe principale `OrmJpaApplication`.
