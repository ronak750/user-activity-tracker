package com.example.yesmadam.demo.useractivity.repo;

import com.example.yesmadam.demo.useractivity.model.UserAction;
import com.example.yesmadam.demo.useractivity.model.UserDetails;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
    List<UserDetails> findByUserUserId(Long userId, Pageable pageable);
    List<UserDetails> findByUserAction(UserAction userAction, Pageable pageable);

    List<UserDetails> findByUserUserIdAndUserAction(Long userId, UserAction userAction, Pageable pageable);
}