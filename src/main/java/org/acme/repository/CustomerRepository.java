package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.ApplicationPath;
import org.acme.model.Customer;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer> {
}
