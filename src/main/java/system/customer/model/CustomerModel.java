package system.customer.model;

import system.customer.requestDTO.CustomerRequestDTO;

import lombok.*;
import jakarta.persistence.*;

import java.util.UUID;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "customers")
@Table(name = "customers")
public class CustomerModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String document;
    private String numberPhone;
    private String email;

    public CustomerModel(CustomerRequestDTO data) {
        this.document = data.document();
        this.numberPhone = data.numberPhone();
        this.name = data.name();
        this.email = data.email();
    }
}