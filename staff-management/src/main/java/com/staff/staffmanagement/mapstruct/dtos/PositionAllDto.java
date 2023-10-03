package com.staff.staffmanagement.mapstruct.dtos;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.staff.staffmanagement.entity.Staff;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor  // generates a default no-arg constructor
@AllArgsConstructor  // generates an all-args constructor
public class PositionAllDto {

    @JsonProperty("position_id")
    private int positionID;

    @JsonProperty("position_title")
    private String positionTitle;

    @JsonProperty("position_description")
    private String positionDescription;

    @JsonProperty("position_staff_ids")
    private Set<Integer> positionStaffMemberIDs;
}
