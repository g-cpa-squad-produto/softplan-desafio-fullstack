//package org.gradle.entidade;
//
//import java.util.List;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.ManyToMany;
//import javax.persistence.SequenceGenerator;
//import javax.persistence.Table;
//import javax.validation.constraints.NotNull;
//
//@Entity
//@Table(name = "AUTHORITY")
//public class Authority {
//
//    @Id
//    @Column(name = "ID")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authority_seq")
//    @SequenceGenerator(name = "authority_seq", sequenceName = "authority_seq", allocationSize = 1)
//    private Long id;
//
//    @Column(name = "NAME", length = 50)
//    @NotNull
//    @Enumerated(EnumType.STRING)
//    private AuthorityName name;
//
//    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
//    private List<Usuario> users;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public AuthorityName getName() {
//        return name;
//    }
//
//    public void setName(AuthorityName name) {
//        this.name = name;
//    }
//
//    public List<Usuario> getUsers() {
//        return users;
//    }
//
//    public void setUsers(List<Usuario> users) {
//        this.users = users;
//    }
//}
