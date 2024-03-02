package system.customer.controller;

import system.customer.model.CustomerModel;
import system.customer.repository.ICustomerRepository;
import system.customer.responseDTO.CustomerRequestDTO;
import system.customer.responseDTO.CustomerResponseDTO;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private ICustomerRepository customerRepository;

    private final String routerOrigins = "*";

    @CrossOrigin(origins = routerOrigins, allowedHeaders = "*")
    @PostMapping("/create")
    public ResponseEntity create(@RequestBody CustomerRequestDTO data) {
        CustomerModel customerModelData = new CustomerModel(data);
        var customerDocument = this.customerRepository.findByDocument(customerModelData.getDocument());
        if (customerDocument != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cliente já está cadastrado");
        }
        var CustomerCreated = customerRepository.save(customerModelData);
        return ResponseEntity.status(HttpStatus.CREATED).body(CustomerCreated);
    }

    @CrossOrigin(origins = routerOrigins, allowedHeaders = "*")
    @RequestMapping("/list")
    public List<CustomerResponseDTO> getAll() {
        List<CustomerResponseDTO> customerModelList = customerRepository.findAll().stream().map(CustomerResponseDTO::new).toList();
        return customerModelList;
    }
}
