package main.controller;

import main.dto.api.request.AnimalRequest;
import main.dto.api.response.AnimalResponse;
import main.dto.api.response.UserAnimalsResponse;
import main.service.AnimalService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/animal")
public class ApiAnimalController {

  private final AnimalService animalService;

  public ApiAnimalController(AnimalService animalService) {
    this.animalService = animalService;
  }

  @PostMapping("/create")
  @PreAuthorize("hasAuthority('user:write')")
  public AnimalResponse createAnimal(
      @RequestBody AnimalRequest createPostRequest, Principal principal) {
    return animalService.createNewAnimal(createPostRequest, principal);
  }

  @PutMapping("/edit/{id}")
  @PreAuthorize("hasAuthority('user:write')")
  public AnimalResponse editAnimal(@PathVariable("id") Integer id, @RequestBody AnimalRequest animalRequest, Principal principal){
    return animalService.editAnimal(id, animalRequest, principal);
  }

  @DeleteMapping("/delete/{id}")
  @PreAuthorize("hasAuthority('user:write')")
  public AnimalResponse deleteAnimal(@PathVariable("id") Integer id, Principal principal){
    return animalService.deleteAnimal(id, principal);
  }

  @GetMapping("/my")
  @PreAuthorize("hasAuthority('user:write')")
  public UserAnimalsResponse getListAnimal(Principal principal){
    System.out.println("Контроллер животных");
    return animalService.getUserAnimals(principal);
  }

  @GetMapping("/{id}")
  public AnimalResponse getAnimal(@PathVariable("id") Integer id){
    System.out.println("Контроллер одного животного");
    return animalService.getAnimalId(id);
  }
}
