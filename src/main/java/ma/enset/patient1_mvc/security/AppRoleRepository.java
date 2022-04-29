 package ma.enset.patient1_mvc.security;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,String> {
    AppRole findByRolename(String roleName);

    
}
