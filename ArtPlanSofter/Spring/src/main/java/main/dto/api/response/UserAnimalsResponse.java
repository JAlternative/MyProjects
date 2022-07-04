package main.dto.api.response;

import lombok.Data;
import main.dto.AnimalDto;

import java.util.List;

@Data
public class UserAnimalsResponse {

  private Boolean result;
  private List<AnimalDto> animalListResult;
}
