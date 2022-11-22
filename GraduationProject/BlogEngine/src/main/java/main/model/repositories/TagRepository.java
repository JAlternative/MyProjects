package main.model.repositories;

import main.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Integer> {

  @Override
  List<Tag> findAll();

  Tag findByName(String name);

  Optional<Tag> findTagByName(String name);
}