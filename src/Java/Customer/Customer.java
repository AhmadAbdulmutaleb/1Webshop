package Java.Customer;

import Java.Super.User;

public class Customer extends User {

    private String name;
    private String email;
    private String phone;
    private String address;

    public Customer(int id, String name, String userName, String phone, String address, String password) {
        super(id, userName, password);
        this.name = name;
        this.email = userName;
        this.phone = phone;
        this.address = address;
    }


    public  String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public  String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public  String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }


    @Override
    public String toString() {
        return "Customer.Customer{" +
                "customerId=" + getId() +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", password='" + getPassword() + '\'' +
                '}';
    }
}