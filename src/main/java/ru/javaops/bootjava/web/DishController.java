package ru.javaops.bootjava.web;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.bootjava.AuthUser;
import ru.javaops.bootjava.model.Dish;
import ru.javaops.bootjava.model.User;
import ru.javaops.bootjava.repository.DishRepository;
import ru.javaops.bootjava.repository.UserRepository;

import javax.validation.Valid;
import java.net.URI;

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
}
