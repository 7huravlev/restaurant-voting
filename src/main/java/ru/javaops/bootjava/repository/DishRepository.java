package ru.javaops.bootjava.repository;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.bootjava.model.Dish;

import java.util.Optional;

@Transactional(readOnly = true)
@Tag(name = "Dish Controller")
public interface DishRepository extends JpaRepository<Dish, Integer> {

    @Query("SELECT d FROM Dish d WHERE d.id = :id and d.user.id = :userId")
    Optional<Dish> get(int id, int userId);

}
