package com.project.wma.domain;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "task")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID taskID;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employeID", referencedColumnName = "employeID")
    private Employee employee;
}
