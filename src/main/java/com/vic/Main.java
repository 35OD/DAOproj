package com.vic;

import service.CustomerService;

public class Main {
    public static void main(String[] args) {
CustomerService cs = new CustomerService();
//cs.addCustomer();
//        cs.removeCustomerById();
        cs.getCustomerById(3);
    }
}