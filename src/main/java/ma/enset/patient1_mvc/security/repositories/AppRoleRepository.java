 package ma.enset.patient1_mvc.security.repositories;

import ma.enset.patient1_mvc.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,String> {
    AppRole findByRoleName(String roleName);

    
}
