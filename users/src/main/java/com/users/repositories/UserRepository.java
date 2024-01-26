package com.users.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

import com.users.models.UserModel;

public interface UserRepository extends JpaRepository<UserModel, UUID> {

}
