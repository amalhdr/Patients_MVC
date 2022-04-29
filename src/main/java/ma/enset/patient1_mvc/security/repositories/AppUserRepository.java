package ma.enset.patient1_mvc.security.repositories;

import ma.enset.patient1_mvc.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,String> {
    AppUser findByUsername(String username);


}
