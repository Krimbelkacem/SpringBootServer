package khlifa.bankbdl.repositories;

import khlifa.bankbdl.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepo extends JpaRepository<Client, Long> {
    Client findByEmail(String email);
}
