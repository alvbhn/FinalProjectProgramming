package org.alvee;

import lombok.*;

@Getter
@EqualsAndHashCode
@ToString
public class Department {
    private String departmentId;
    private String departmentName;
    private static int nextId = 1;


}
