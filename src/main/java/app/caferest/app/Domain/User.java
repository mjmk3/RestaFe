package app.caferest.app.Domain;

import jakarta.persistence.*;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.*;
import java.io.Serializable;

@NamedQuery(name = "User.findByEmail", query = "select u from User u where u.email=:email")

@NoArgsConstructor
@AllArgsConstructor
@Data
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phone;
    private String email;
    private String password;
    private String status;
    private String role;
}
