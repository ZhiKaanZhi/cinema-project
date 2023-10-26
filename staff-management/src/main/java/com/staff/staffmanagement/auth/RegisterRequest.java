package com.staff.staffmanagement.auth;

import com.staff.staffmanagement.entity.enums.StaffTitle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String staffName;
    private String staffUsername;
    private String staffEmail;
    private String staffPassword;
    private Date staffDOB;
    private Date staffHireDate;
    private StaffTitle staffPosition;
}
