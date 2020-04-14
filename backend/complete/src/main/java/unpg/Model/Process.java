package unpg.Model;

import javax.persistence.*;

import java.util.Set;

@Entity
@Table(name = "processes")
public class Process {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int processId;
    private String number;
    private String report;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_processes", joinColumns = @JoinColumn(name = "process_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users;

    public Process() {
    }

    public Process(int processId, String number, String report, Set<User> users) {
        this.processId = processId;
        this.number = number;
        this.report = report;
        this.users = users;
    }

    public int getProcessId() {
        return processId;
    }

    public void setProcessId(int processId) {
        this.processId = processId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Process{" + "processId=" + processId + ", number=" + number + ", report='" + report + '\'' + '}';
    }
}
