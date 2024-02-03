package com.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.application.entity.Customer;
import com.application.repository.CustomerRepository;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable).getContent();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long id, Customer customer) {
        Customer existingCustomer = customerRepository.findById(id).orElse(null);
        if (existingCustomer != null) {
            existingCustomer.setFirstName(customer.getFirstName());
            existingCustomer.setLastName(customer.getLastName());
            existingCustomer.setStreet(customer.getStreet());
            existingCustomer.setAddress(customer.getAddress());
            existingCustomer.setCity(customer.getCity());
            existingCustomer.setState(customer.getState());
            existingCustomer.setEmail(customer.getEmail());
            existingCustomer.setPhone(customer.getPhone());
            return customerRepository.save(existingCustomer);
        }
        return null;
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
    
    public List<Customer> getid(){
    	List<Customer> list=(List<Customer>)this.customerRepository.findAll();
    	return list;
    }
	/*
	 * public List<Customer> getlist(){ return this.customerRepository.findAll(); }
	 */

    public List<Customer> searchCustomers(String query) {
        return customerRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrPhoneContainingIgnoreCase(query, query, query);
    }
    public Customer createCustomer(String username, String email, String password) {
        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setEmail(email);
        customer.setPassword(password);
        return customerRepository.save(customer);
    }
}