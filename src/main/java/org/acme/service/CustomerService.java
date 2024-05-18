package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.acme.model.Customer;
import org.acme.repository.CustomerRepository;

import java.util.List;

@ApplicationScoped
public class CustomerService {

    @Inject
    CustomerRepository customerRepository;

    public List<Customer> findAllCustomers() {
        return customerRepository.findAll().list();
    }

    public void addCustomer(Customer customer) {
        customerRepository.persist(customer);
    }



}
