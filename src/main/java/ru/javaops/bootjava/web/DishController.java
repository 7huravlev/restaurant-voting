package ru.javaops.bootjava.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.bootjava.AuthUser;
import ru.javaops.bootjava.model.Dish;
import ru.javaops.bootjava.repository.DishRepository;
import ru.javaops.bootjava.repository.UserRepository;
import ru.javaops.bootjava.util.ValidationUtil;

import javax.validation.Valid;
import java.net.URI;

import static ru.javaops.bootjava.util.ValidationUtil.assureIdConsistent;
import static ru.javaops.bootjava.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = "/api/dish")
@AllArgsConstructor
@Slf4j
@Tag(name = "Dish Controller")
public class DishController {
    static final String REST_URL = "/api/dish";

    private final DishRepository dishRepository;
    private final UserRepository userRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> create(@AuthenticationPrincipal AuthUser authUser, @Valid @RequestBody Dish dish) {
        int userId = authUser.id();
        log.info("create {} for user {}", dish, userId);
        checkNew(dish);
        dish.setUser(userRepository.getOne(userId));
        Dish created = dishRepository.save(dish);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@AuthenticationPrincipal AuthUser authUser, @Valid @RequestBody Dish dish, @PathVariable int id) {
        int userId = authUser.id();
        log.info("update {} for user {}", dish, userId);
        assureIdConsistent(dish, id);
        ValidationUtil.checkNotFoundWithId(dishRepository.get(id, authUser.id()), "Dish id=" + id + " doesn't belong to user id=" + userId);
        dish.setUser(userRepository.getOne(userId));
        dishRepository.save(dish);
    }

}
