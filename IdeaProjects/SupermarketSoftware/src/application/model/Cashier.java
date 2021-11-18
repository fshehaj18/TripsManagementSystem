package model;

public class Cashier extends User implements Checker{
    private String name;
    private  DateFormat date;
    private String email;
    private String phone;
    private double salary;

    public Cashier(String username, String password, int id, String name, DateFormat date, String email, String phone, double salary) {
        super(username, password, id);
        this.name = name;
        this.date = date;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
    }

    @Override
    public boolean checkUser(String username, String password) {
        return this.getUsername() == username && this.getPassword() == password;
    }
}
