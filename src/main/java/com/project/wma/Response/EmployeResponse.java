package com.project.wma.Response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class EmployeResponse {

    private String nom;
    private String prenom;
    private int age;
}
