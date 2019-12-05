package gryszq.dev.angularshop.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @NotEmpty(message = "Proszę podaj swoję imię..")
    private String name;

    @Email(message = "Wprowadź poprawny adres mailowy..")
    private String username;

//    @Size(min = 3 , max = 15, message = "Hasło musi mieć od 4 do 15 znaków..")
    private String password;

    @Transient
    private String passwordConfirm;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY)
    private List<Order> order_list = new ArrayList<>();

    public User() {}

    public User(Long id, String name, String username,Integer role){

        Role admin = new Role("admin");
        Role normalUser = new Role("użytkownik");

        this.id = id;
        this.name = name;
        this.username = username;
        if(role == 1){
            roles.add(admin);
        } else
        {
            roles.add(normalUser);
        }
    }

    public User(String name, String username, String password, String passwordConfirm, Set<Role> roles) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.roles = roles;
    }

    public User(String name, String username, String password, String passwordConfirm) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role){
        roles.add(role);
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public List<Order> getOrder_list() {
        return order_list;
    }

    public void setOrder_list(List<Order> order_list) {
        this.order_list = order_list;
    }

    public void addOrder(Order order){
        this.order_list.add(order);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", passwordConfirm='" + passwordConfirm + '\'' +
                ", roles=" + roles +
                ", order_list=" + order_list +
                '}';
    }
}
