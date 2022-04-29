package ma.enset.patient1_mvc;

import ma.enset.patient1_mvc.entities.Patient;
import ma.enset.patient1_mvc.repositories.PatientRepository;
import ma.enset.patient1_mvc.security.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class Patient1MvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(Patient1MvcApplication.class, args);
    }

    //traitement au demarage
   // @Bean
    // va executer automatiquement au demarage
    CommandLineRunner commandLineRunner(PatientRepository patientRepository) {
        return args ->
        {
            patientRepository.save(new Patient(null, "abdo", new Date(), false, 150));
            patientRepository.save(new Patient(null, "amal", new Date(), true, 100));
            patientRepository.save(new Patient(null, "mehdi", new Date(), false, 120));
            patientRepository.save(new Patient(null, "rajaa", new Date(), true, 210));
            patientRepository.findAll().forEach(p -> {
                System.out.println(p.getNom());
            });


        };
    }

   @Bean
    CommandLineRunner saveUsers(SecurityService securityService)
    { return  args -> {
        securityService.saveNewUser("amal","1234","1234");
        securityService.saveNewUser("badia","1234","1234");
        securityService.saveNewUser("rajaa","1234","1234");


        securityService.saveNewRole("USER","");
        securityService.saveNewRole("ADMIN","");

        securityService.addRoleToUser("badia","ADMIN");
        securityService.addRoleToUser("amal","USER");
        securityService.addRoleToUser("rajaa","USER");
    };

    }

}
