package main.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "global_settings")
@NoArgsConstructor
public class GlobalSetting {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  private Integer id;

  @Column(nullable = false, columnDefinition = "VARCHAR(255)")
  private String code;

  @Column(nullable = false, columnDefinition = "VARCHAR(255)")
  private String name;

  @Column(columnDefinition = "VARCHAR(255)")
  private String value;
}
