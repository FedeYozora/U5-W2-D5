package it.epicode.Device.Management.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String username;
    private String name;
    private String surname;
    private String email;
    private String avatar;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Device> device;
}
