package khlifa.bankbdl.services;

import khlifa.bankbdl.models.Client;
import khlifa.bankbdl.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//////

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ClientService(ClientRepository clientRepository, PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    public Client getClientByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    public ResponseEntity<?> addClient(Client client) {
        String email = client.getEmail();

        if (clientRepository.findByEmail(email) != null) {
            return new ResponseEntity<>("Email is already registered", HttpStatus.BAD_REQUEST);
        }

        String encodedPassword = passwordEncoder.encode(client.getPassword());
        client.setPassword(encodedPassword);

        try {
            Client newClient = clientRepository.save(client);
            return new ResponseEntity<>(newClient, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating the client", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Client> loginClient(Client client) {
        String email = client.getEmail();
        String password = client.getPassword();

        Client storedClient = clientRepository.findByEmail(email);

        if (storedClient != null) {
            String storedPassword = storedClient.getPassword();
            if (new BCryptPasswordEncoder().matches(password, storedPassword)) {
                return new ResponseEntity<>(storedClient, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    public ResponseEntity<Client> updateClient(Long id, Client updatedClient) {
        Client client = clientRepository.findById(id).orElse(null);
        if (client != null) {
            updatedClient.setId(id);
            Client savedClient = clientRepository.save(updatedClient);
            return new ResponseEntity<>(savedClient, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Void> deleteClient(Long id) {
        Client client = clientRepository.findById(id).orElse(null);
        if (client != null) {
            clientRepository.delete(client);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
