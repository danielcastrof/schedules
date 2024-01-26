package com.email.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.email.models.EmailModel;
import java.util.*;

public interface EmailRepository extends JpaRepository<EmailModel, UUID> {

}
