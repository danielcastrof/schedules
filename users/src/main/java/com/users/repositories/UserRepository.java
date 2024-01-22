package com.users.repositories;

import org.hibernate.validator.constraints.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import com.users.models.UserModel;

public interface UserRepository extends JpaRepository<UserModel, UUID> {

}
