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
import ru.javaops.bootjava.error.IllegalRequestDataException;
import ru.javaops.bootjava.model.Vote;
import ru.javaops.bootjava.repository.UserRepository;
import ru.javaops.bootjava.repository.VoteRepository;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RestController
@RequestMapping(value = "/api/vote")
@AllArgsConstructor
@Slf4j
@Tag(name = "Vote Controller")
public class VoteController {
    static final String REST_URL = "/api/vote";

    private final VoteRepository voteRepository;
    private final UserRepository userRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> createWithLocation(@AuthenticationPrincipal AuthUser authUser, @Valid @RequestBody Vote vote) {
        int userId = authUser.id();
        log.info("vote {} for user {}", vote, userId);
        vote.setUser(userRepository.getOne(userId));
        LocalDate startDate0 = vote.getDate_time().toLocalDate();
        LocalDateTime startDate = LocalDateTime.of(startDate0, LocalTime.of( 0, 0));
        LocalDate endDate0 = vote.getDate_time().toLocalDate();
        LocalDateTime endDate = LocalDateTime.of(endDate0.plusDays(1), LocalTime.of( 0, 0));
        if (voteRepository.get(startDate, endDate, authUser.id()).stream().count() > 0) {
            if (vote.getDate_time().getHour() >= 11) {
                log.info("vote {} can't be changed", vote);
                throw new IllegalRequestDataException("vote can't be changed");
            }
            else {
                vote.setId(voteRepository.get(startDate, endDate, authUser.id()).get().getId());
            }
        }
        Vote created = voteRepository.save(vote);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

}
