package model;

import java.io.Serializable;

public class User implements Serializable {

    private static int nrUsers = 0;
    private String username;
    private String password;
    private String level;
    private final int id;

    public User(String username, String password, int id) {
        this.username = username;
        this.password = password;
        this.id = id;
    }

    public static int getNrUsers() {
        return nrUsers;
    }

    public static void setNrUsers(int nrUsers) {
        User.nrUsers = nrUsers;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }


}
