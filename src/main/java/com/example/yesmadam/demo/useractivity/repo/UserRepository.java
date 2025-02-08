package com.example.yesmadam.demo.useractivity.repo;

import com.example.yesmadam.demo.useractivity.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}