package com.fincore.fincore360.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fincore.fincore360.entity.User;

/*
 * UserRepository:
 * User table मधील database operations करण्यासाठी.
 *
 * JpaRepository मुळे आपल्याला save(), findById(),
 * findAll(), deleteById() सारख्या methods आपोआप मिळतात.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    /*
     * Email वापरून User शोधतो.
     *
     * Optional:
     * User सापडला तर User मिळतो,
     * नाही सापडला तर empty Optional मिळतो.
     */
    Optional<User> findByEmail(String email);
}
