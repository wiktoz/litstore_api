package com.example.litstore_api.repositories;

import com.example.litstore_api.models.Address;
import com.example.litstore_api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.user_id = :id")
    Optional<User> findById(@Param("id") String id);

    @Query("SELECT a FROM Address a WHERE a.user_id = :id")
    List<Address> getUserAddresses(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.email = :email WHERE u.user_id = :id")
    Optional<User> updateUserEmail(@Param("id") Integer id, @Param("email") String email);
}
