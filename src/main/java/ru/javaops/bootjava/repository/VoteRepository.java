package ru.javaops.bootjava.repository;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.bootjava.model.Vote;

import java.time.LocalDateTime;
import java.util.Optional;

@Transactional(readOnly = true)
@Tag(name = "Vote Controller")
public interface VoteRepository  extends JpaRepository<Vote, Integer> {

    @Query("SELECT v FROM Vote v WHERE v.date_time >= :startDate and v.date_time < :endDate and v.user.id = :userId")
    Optional<Vote> get(LocalDateTime startDate, LocalDateTime endDate, int userId);

}
