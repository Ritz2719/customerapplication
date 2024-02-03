document.addEventListener('DOMContentLoaded', () => {
  const customersLink = document.getElementById('customers-link');
  const addCustomerButton = document.getElementById('add-customer-button');

  const customersSection = document.getElementById('customers');
  const addCustomerSection = document.getElementById('add-customer');
  const customerTable = document.getElementById('customer-table');

  customersLink.addEventListener('click', () => {
    customersSection.style.display = 'block';
    addCustomerSection.style.display = 'none';
    fetchCustomers();
  });

  addCustomerButton.addEventListener('click', () => {
    customersSection.style.display = 'none';
    addCustomerSection.style.display = 'block';
  });

  const fetchCustomers = async () => {
    try {
      const response = await fetch('/api/customers');
      const customers = await response.json();
      displayCustomers(customers);
    } catch (error) {
      console.error(error);
    }
  };

  const displayCustomers = (customers) => {
    const tbody = customerTable.querySelector('tbody');
    tbody.innerHTML = '';
    customers.forEach((customer) => {
      const tr = document.createElement('tr');
      tr.innerHTML = `
        <td>${customer.id}</td>
        <td>${customer.first_name}</td>
        <td>${customer.last_name}</td>
        <td>${customer.street}</td>
        <td>${customer.address}</td>
        <td>${customer.city}</td>
        <td>${customer.state}</td>
        <td>${customer.email}</td>
        <td>${customer.phone}</td>
        <td>
          <button class="edit-customer-button" data-id="${customer.id}">Edit</button>
          <button class="delete-customer-button" data-id="${customer.id}">Delete</button>
        </td>
      `;
      tbody.appendChild(tr);
    });

    const editButtons = customerTable.querySelectorAll('.edit-customer-button');
    editButtons.forEach((button) => {
      button.addEventListener('click', () => {
        const id = button.getAttribute('data-id');
        editCustomer(id);
      });
    });

    const deleteButtons = customerTable.querySelectorAll('.delete-customer-button');
    deleteButtons.forEach((button) => {
      button.addEventListener('click', () => {
        const id = button.getAttribute('data-id');
        deleteCustomer(id);
      });
    });
  };

  const editCustomer = async (id) => {
    // TODO: Implement edit customer functionality
  };

  const deleteCustomer = async (id) => {
    if (confirm('Are you sure you want to delete this customer?')) {
      try {
        await fetch(`/api/customers/${id}`, { method: 'DELETE' });
        fetchCustomers();
      } catch (error) {
        console.error(error);
      }
    }
  };
});