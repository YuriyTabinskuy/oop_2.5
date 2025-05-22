package model;

public class Customer {
    private int id;
    private String name;
    private String email;
    private String phone;

    public Customer() {}

    public Customer(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Customer(int id, String name, String email, String phone) {
        this(name, email, phone);
        this.id = id;
    }

    // Геттери і сеттери...

    @Override
    public String toString() {
        return id + ": " + name + " (" + email + ", " + phone + ")";
    }
}
