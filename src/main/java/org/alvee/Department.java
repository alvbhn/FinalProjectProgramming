package org.alvee;

import lombok.*;

@Getter
@EqualsAndHashCode
@ToString
public class Department {
    private String departmentId;
    @Setter
    private String departmentName;

    private static int nextId = 1;

    /**
     * checks if a department name is valid or not
     * a department name should only contain letters or space
     * and should not be null or empty
     * @param departmentName the name to validate
     * @return return true if the name only contains letters or space, otherwise return false
     */
    public static boolean isDepartmentNameValid(String departmentName) {
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

    public Department(String departmentName) {
        if (isDepartmentNameValid(departmentName)) {
            this.departmentId = String.format("D%02d", nextId++);
            this.departmentName = departmentName;
        } else {
            this.departmentId = null;
            this.departmentName = null;
        }
    }
}
