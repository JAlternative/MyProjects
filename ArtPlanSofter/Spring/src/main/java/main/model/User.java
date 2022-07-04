package main.model;

import lombok.Data;
import main.model.enums.Role;

import javax.persistence.*;
import java.util.List;

@Table(name = "users")
@Entity
@Data
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String name;

  private String email;

  private String password;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Animal> userPosts;

  private Integer authorizationAttempts;

  private Long authTime;

  public Role getRole() {
    return Role.USER;
  }
}
