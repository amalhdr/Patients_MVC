package ma.enset.patient1_mvc.security.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class AppRole {
    private Long roleId;
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String role;
    @Column(unique = true)
    private String roleName;
    private String description;


}
