package system.customer.controller;

import system.customer.model.CustomerModel;
import system.customer.repository.ICustomerRepository;
import system.customer.requestDTO.CustomerRequestDTO;
import system.customer.responseDTO.CustomerResponseDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private ICustomerRepository customerRepository;

    private final String routerOrigins = "http://localhost:5173/";

    private final Map<String, CustomerModel> customerModelDocumentMap = new HashMap<>();

    @CrossOrigin(origins = routerOrigins, allowedHeaders = "*")
    @PostMapping("/create")
    public ResponseEntity create(@RequestBody CustomerRequestDTO data) {
        CustomerModel customerModelData = new CustomerModel(data);

        var customerDocument = this.customerRepository.findByDocument(customerModelData.getDocument());
        var customerPhoneNumber = this.customerRepository.findByNumberPhone(customerModelData.getNumberPhone());
        var customerEmail = this.customerRepository.findByEmail(customerModelData.getEmail());

        if (customerDocument != null || customerPhoneNumber != null || customerEmail != null) {
            return ResponseEntity.badRequest().body("Cliente já está cadastrado");
        }

        var customerCreated = customerRepository.save(customerModelData);
        customerModelDocumentMap.put(customerCreated.getDocument(), customerCreated);

        return ResponseEntity.ok(customerCreated);
    }

    @CrossOrigin(origins = routerOrigins, allowedHeaders = "*")
    @RequestMapping("/list")
    public List<CustomerResponseDTO> getAll() {
        List<CustomerResponseDTO> customerModelList = customerRepository.findAll().stream().map(CustomerResponseDTO::new).toList();
        return customerModelList;
    }

    @CrossOrigin(origins = routerOrigins, allowedHeaders = "*")
    @RequestMapping("/{document}")
    public ResponseEntity<CustomerModel> getByDocument(@PathVariable String document) {
        CustomerModel customer = customerModelDocumentMap.get(document);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
