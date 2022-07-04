package main.service;

import main.dto.AnimalDto;
import main.dto.api.request.AnimalRequest;
import main.dto.api.response.AnimalResponse;
import main.dto.api.response.UserAnimalsResponse;
import main.model.Animal;
import main.repository.AnimalRepository;
import main.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class AnimalService {

  private final UserRepository userRepository;
  private final AnimalRepository animalRepository;

  public AnimalService(UserRepository userRepository, AnimalRepository animalRepository) {
    this.userRepository = userRepository;
    this.animalRepository = animalRepository;
  }

  public AnimalResponse createNewAnimal(AnimalRequest animalRequest, Principal principal) {
    return validByAnimal(new Animal(), animalRequest, principal);
  }

  public AnimalResponse editAnimal(Integer id, AnimalRequest animalRequest, Principal principal) {
    Animal animal = animalRepository.findById(id).orElse(null);
    AnimalResponse animalResponse = new AnimalResponse();
    animalResponse.setResult(false);
    return Objects.isNull(animal)
        ? animalResponse
        : validByAnimal(animal, animalRequest, principal);
  }

  public AnimalResponse deleteAnimal(Integer id, Principal principal) {
    AnimalResponse animalResponse = new AnimalResponse();
    Animal animal = animalRepository.findById(id).orElse(null);
    if (Objects.isNull(animal) || !animal.getUser().getEmail().equals(principal.getName())) {
      animalResponse.setResult(false);
      return animalResponse;
    }
    animalRepository.deleteById(id);
    animalResponse.setResult(true);
    return animalResponse;
  }

  public UserAnimalsResponse getUserAnimals(Principal principal) {
    UserAnimalsResponse animalResponse = new UserAnimalsResponse();
    List<Animal> userAnimals = userRepository.findByEmail(principal.getName()).getUserPosts();
    if (userAnimals.isEmpty()) {
      animalResponse.setResult(false);
    } else {
      List<AnimalDto> dtoUserAnimals = new ArrayList<>();
      userAnimals.forEach(
          s -> {
            AnimalDto animal = new AnimalDto();
            animal.setName(s.getName());
            animal.setGender(s.getGender());
            animal.setKind(s.getKind());
            animal.setDate(s.getDateOfBirth().toString());
            dtoUserAnimals.add(animal);
          });
      animalResponse.setResult(true);
      animalResponse.setAnimalListResult(dtoUserAnimals);
    }
    return animalResponse;
  }

  public AnimalResponse getAnimalId(Integer id) {
    AnimalResponse animalResponse = new AnimalResponse();
    Animal animal = animalRepository.findById(id).orElse(null);
    if (Objects.isNull(animal)) {
      animalResponse.setResult(false);
    } else {
      AnimalDto animalDto = new AnimalDto();
      animalDto.setName(animal.getName());
      animalDto.setGender(animal.getGender());
      animalDto.setKind(animal.getKind());
      animalDto.setDate(animal.getDateOfBirth().toString());

      animalResponse.setResult(true);
      animalResponse.setAnimalResult(animalDto);
    }
    return animalResponse;
  }

  private AnimalResponse validByAnimal(
      Animal animal, AnimalRequest animalRequest, Principal principal) {
    AnimalResponse animalResponse = new AnimalResponse();
    AnimalDto animalDto = new AnimalDto();
    animalResponse.setResult(true);

    if (Objects.nonNull(animalRequest.getKind())) {
      animal.setKind(animalRequest.getKind());
    } else {
      animalDto.setName("Вид не может быть пустым");
      animalResponse.setResult(false);
    }

    if (Objects.nonNull(animalRequest.getGender())) {
      animal.setGender(animalRequest.getGender());
    } else {
      animalDto.setGender("Пол не может быть пустым");
      animalResponse.setResult(false);
    }

    try {
      LocalDateTime date = LocalDateTime.parse(animalRequest.getDateOfBirth());
      animal.setDateOfBirth(date);
    } catch (DateTimeParseException ex) {
      animalDto.setDate("Неверная дата, используйте формат \"2000-01-01T11:50:55\"");
      animalResponse.setResult(false);
    }

    if (Objects.nonNull(animalRequest.getName())) {
      AtomicBoolean result = new AtomicBoolean(false);
      animalRepository.findAll().stream()
          .forEach(
              s -> {
                if (s.getName().toUpperCase().equals(animalRequest.getName().toUpperCase())) {
                  result.set(true);
                }
              });

      if (result.get()) {
        animalDto.setName("Имя: " + animalRequest.getName() + " уже используется");
        animalResponse.setResult(false);
      } else {
        animal.setName(animalRequest.getName());
      }
    }

    if (Objects.isNull(animalRequest.getName())) {
      animalDto.setName("Имя не может быть пустым");
      animalResponse.setResult(false);
    }

    if (animalResponse.getResult()) {
      animal.setUser(userRepository.findByEmail(principal.getName()));
      animalRepository.save(animal);
    }

    animalResponse.setAnimalResult(animalDto);
    return animalResponse;
  }
}
