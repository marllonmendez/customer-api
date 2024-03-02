package system.customer.responseDTO;

import system.customer.model.CustomerModel;

import java.util.UUID;

public record CustomerResponseDTO(UUID id, int document, int numberPhone, String name, String email) {
    public CustomerResponseDTO(CustomerModel customerModel) {
        this(
            customerModel.getId(),
            customerModel.getDocument(),
            customerModel.getNumberPhone(),
            customerModel.getName(),
            customerModel.getEmail()
        );
    }
}
