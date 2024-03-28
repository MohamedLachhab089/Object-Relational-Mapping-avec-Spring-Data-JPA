package ma.emsi.orm_jpa;

import ma.emsi.orm_jpa.entities.Patient;
import ma.emsi.orm_jpa.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class OrmJpaApplication implements CommandLineRunner {

    @Autowired  // pour faire l'injection de dependence     using ( Spring Data )
    private PatientRepository patientRepository;

    public static void main(String[] args) {
        SpringApplication.run(OrmJpaApplication.class, args);
    }

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final Random random = new Random();

    public static String generateRandomName(int length) {
        StringBuilder nameBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char randomChar = CHARACTERS.charAt(random.nextInt(CHARACTERS.length()));
            nameBuilder.append(randomChar);
        }
        return nameBuilder.toString();
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 100; i++) {
            patientRepository.save(new Patient(null, generateRandomName(5), new Date(), Math.random() > 0.5 ? true : false, (int) (Math.random() * 2)));
        }
        patientRepository.save(new Patient(null, "Mohamed", new Date(), false, 10));
        //List<Patient> patients = patientRepository.findAll();
        Page<Patient> patients = patientRepository.findAll(PageRequest.of(0, 5));
        System.out.println("Total pages : " + patients.getTotalPages());
        System.out.println("Number of elements : " + patients.getTotalElements());
        System.out.println("Num of page : " + patients.getNumber());
        List<Patient> content = patients.getContent();
        //List<Patient> byMalade = patientRepository.findByMalade(true);
        Page<Patient> byMalade = patientRepository.findByMalade(true, PageRequest.of(0, 4));
        List<Patient> byMaladeAndScoreLessThan = patientRepository.findByMaladeAndScoreLessThan(true, 1);
        List<Patient> el = patientRepository.chercherPatients("%e%", 45);
        el.forEach(p -> {
            System.out.println("====================================");
            System.out.println(p.getId());
            System.out.println(p.getNom());
            System.out.println(p.getDate());
            System.out.println(p.isMalade());
            System.out.println(p.getScore());
        });
        System.out.println("**************************************");
        Patient patient1 = patientRepository.findById(1L).orElse(null); // ou findById(1L).get()
        Patient patient2 = patientRepository.findById(1L).orElseThrow(() -> new RuntimeException("Patient not found"));
        if (patient1 != null) {
            System.out.println(patient1.getNom());
            System.out.println(patient1.isMalade());
        }
        patient1.setScore(89);
        patientRepository.save(patient1);

        patientRepository.deleteById(2L);
    }
}
