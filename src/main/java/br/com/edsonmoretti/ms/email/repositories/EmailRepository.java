package br.com.edsonmoretti.ms.email.repositories;

import br.com.edsonmoretti.ms.email.models.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmailRepository  extends JpaRepository<Email, UUID> {

}
