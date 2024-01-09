package org.workintech.entity.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "role", schema = "ecommerce")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Name must not be null, empty or blank")
    @Size(min = 1,max = 25,message = "Name must not be less than 3 and greater than 25 characters.")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Code must not be null, empty or blank")
    @Size(min = 2,max = 25,message = "Code must not be less than 2 and greater than 25 characters.")
    @Column(name = "code")
    private String code;

    @Override
    public String getAuthority() {
        return code;
    }


}
