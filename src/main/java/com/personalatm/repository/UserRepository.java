package com.personalatm.repository;

import com.personalatm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}