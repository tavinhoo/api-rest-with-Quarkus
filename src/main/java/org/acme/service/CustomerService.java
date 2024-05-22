package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.model.Customer;
import org.acme.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CustomerService {

    @Inject
    CustomerRepository customerRepository;

    public List<Customer> findAllCustomers() {
        return customerRepository.findAll().list();
    }

    public Optional<Customer> findCustomerById(Long id) {
        Optional<Customer> customer = Optional.of(customerRepository.findById(id));
        return customer;
    }

    public void addCustomer(Customer customer) {
        customerRepository.persist(customer);
    }

    public void updateCustomer(Long id, Customer customer) throws Exception {
        Optional<Customer> customer0 = customerRepository.findByIdOptional(id);
        if(customer0.isEmpty()) {
            throw new Exception("Customer not found!");
        }

        Customer customer1 = customer0.get();
        customer1.setName(customer.getName());
        customer1.setLastName(customer.getLastName());
        customer1.setAge(customer.getAge());
        customer1.setEmail(customer.getEmail());
        customerRepository.persist(customer1);
    }
}
