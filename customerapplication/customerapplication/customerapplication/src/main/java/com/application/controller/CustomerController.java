package com.application.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.entity.Customer;
import com.application.repository.CustomerRepository;
import com.application.request.CustomerRequest;
import com.application.response.CustomerResponse;
import com.application.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    
    //kushal
    @GetMapping("/getlist")
    public List<Customer> getCustomers()
    {
    	return customerService.getid();
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers(Pageable pageable) {
        List<Customer> customers = customerService.getAllCustomers(pageable);
        System.out.println("yo");
        return new ResponseEntity<>(customers, HttpStatus.OK);
    
    }

    
   
   @GetMapping("/{id}")
   public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long id) {
       Customer customer = customerService.getCustomerById(id);
       
       return new ResponseEntity<>(customer, HttpStatus.OK);
   }

	/*
	 * @GetMapping("/getlist") public List<Customer> list(){ return
	 * this.customerService.getlist(); }
	 */
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer newCustomer = customerService.createCustomer(customer);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        Customer updatedCustomer = customerService.updateCustomer(id, customer);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Customer>> searchCustomers(@RequestBody String query) {
        List<Customer> customers = customerService.searchCustomers(query);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
    @PostMapping("/api/customers")
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerRequest customerRequest) {
        Customer customer = customerService.createCustomer(customerRequest.getUsername(), customerRequest.getEmail(), customerRequest.getPassword());
        CustomerResponse customerResponse = new CustomerResponse(customer.getId(), customer.getUsername(), customer.getEmail());
        System.out.println("yo2"+customerResponse);
        return ResponseEntity.ok(customerResponse);
    }
}