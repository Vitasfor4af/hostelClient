package model;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {

    private static final long serialVersionUID = -2565570290688784024L;

    private String name;
    private String login;
    private String password;
    private String role;
    private Integer id;

    public User(Integer id, String name, String role, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.role = role;
        this.id = id;
    }

    public User(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User client = (User) o;
        return id == client.id &&
                name.equals(client.name) &&
                login.equals(client.login) &&
                password.equals(client.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, login, password, id);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
