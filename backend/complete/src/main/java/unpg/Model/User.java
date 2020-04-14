package unpg.Model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String name;
    private String email;
    private String password;
    private String roles;

    @JsonBackReference
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_processes", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "process_id"))
    private Set<Process> processes;

    public User() {
    }

    public User(int idUser, String name, String email, String password, String roles) {
        this.userId = idUser;
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int idUser) {
        this.userId = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Set<Process> getProcesses() {
        return processes;
    }

    public void setProcesses(Set<Process> processes) {
        this.processes = processes;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", name='" + name + '\'' + ", email='" + email + '\'' + ", password='"
                + password + '\'' + ", roles=" + roles + '}';
    }
}
