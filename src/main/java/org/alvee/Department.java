package org.alvee;

import lombok.*;

@Getter
@EqualsAndHashCode
@ToString
public class Department {
    private String departmentId;
    private String departmentName;
    private static int nextId = 1;

    static boolean isDepartmentNameValid(String departmentName) {
        if (departmentName == null || departmentName.isEmpty()) {
            return false;
        }

        for (int i = 0; i <departmentName.length(); i++) {
            char c = departmentName.charAt(i);
            if (c == ' ') {
                continue;
            }
            if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
                 continue;
            }

            return false;
        }

        return true;
    }


}
