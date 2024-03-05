package system.customer.repository;

import system.customer.model.CustomerModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ICustomerRepository extends JpaRepository<CustomerModel, Integer> {
    CustomerModel findByDocument(long document);
}
