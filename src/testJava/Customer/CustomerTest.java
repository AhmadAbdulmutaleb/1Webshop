package testJava.Customer;

import Java.Customer.CustomerService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerTest {

    public static void addAndRemoveCustomer() throws SQLException {
        var baos = new ByteArrayOutputStream();
        var out = System.out;
        try {

            System.setOut(new PrintStream(baos));
            CustomerService customerService = new CustomerService();
            customerService.showAllUsers();
        }

        finally {

            System.setOut(out);

        }

        System.out.println(baos.toString());
        int r = baos.toString().split("\n").length;

        System.out.println(r);

    }


    public static void addCustomer() throws Exception {

        var customerService = new CustomerService();
        int count = customerService.getCustomerCount();

        System.out.println( " Count is = " + count);
        customerService.addCustomer("Fake", "FAke@Fake.se", "555555", "Fakestreet" , "FakeFake");
        assert(customerService.getCustomerCount()== count+1);
    }

    public static void main(String[] args) throws Exception{
        CustomerTest.addAndRemoveCustomer();
        CustomerTest.addCustomer();
    }

}
