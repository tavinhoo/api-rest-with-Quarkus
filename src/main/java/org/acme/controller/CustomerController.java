package org.acme.controller;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.acme.model.Customer;
import org.acme.service.CustomerService;

import java.util.ArrayList;
import java.util.List;

@Path("/api/customer")
public class CustomerController {

    @Inject
    CustomerService customerService;

    @GET
    public List<Customer> retrieveCutomers() {
        List<Customer> customers = new ArrayList<>();
        try {
            customers = customerService.findAllCustomers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }

    @POST
    @Transactional
    public void addCustomer(Customer customer) {
        try {
            customerService.addCustomer(customer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateCustomer(@PathParam("id") Long id, Customer customer) {
        try {
            customerService.updateCustomer(id, customer);
            return Response.noContent().build(); // 204 No Content
        } catch (Exception e) {
            if (e.getMessage().equals("Customer not found!")) {
                return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build(); // 404 Not Found
            }
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build(); // 500 Internal Server Error
        }
    }
}
