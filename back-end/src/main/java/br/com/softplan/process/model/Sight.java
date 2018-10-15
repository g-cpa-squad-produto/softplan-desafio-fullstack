package br.com.softplan.process.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Sight extends BaseEntity {

    @Id
    @NotNull
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column
    private String title;

    @NotNull
    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name = "process_id")
    private Process process;
}
