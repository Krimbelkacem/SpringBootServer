package khlifa.bankbdl.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // Assuming the ID is of type long
    private String name;
    private String email;
    private String password;

    // Default constructor required by JPA
    public Client() {
    }


    // Getter and setter for ID
    // Getter and setter for ID
    // Getter and setter for ID
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    // Getters and setters for name, email, and password
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // You can also override toString() to get a string representation of the object
    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
