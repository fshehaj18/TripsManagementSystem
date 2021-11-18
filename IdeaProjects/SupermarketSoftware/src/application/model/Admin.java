package model;

public class Admin extends User implements Checker{
    public Admin(String username, String password, int name) {
        super(username, password, name);
    }

    @Override
    public boolean checkUser(String username, String password) {
        return this.getUsername() == username && this.getPassword() == password;
    }
}
