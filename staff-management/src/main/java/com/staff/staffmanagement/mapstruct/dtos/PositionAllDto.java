package com.staff.staffmanagement.mapstruct.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.staff.staffmanagement.entity.enums.StaffTitle;
import lombok.*;

import java.util.List;

@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PositionAllDto {

    @JsonProperty("position_id")
    private int positionID;

    @JsonProperty("position_title")
    private StaffTitle positionTitle;

    @JsonProperty("position_description")
    private String positionDescription;

    @JsonProperty("position_staff_ids")
    private List<Integer> positionStaffMemberIDs;

}
