package com.staff.staffmanagement.mapstruct.dtos;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.staff.staffmanagement.entity.enums.StaffTitle;
import jakarta.persistence.Entity;
import lombok.*;



@Getter
@Setter
@ToString
@NoArgsConstructor  // generates a default no-arg constructor
@AllArgsConstructor  // generates an all-args constructor
public class PositionSimpleDto {

    @JsonProperty("position_id")
    private int positionID;

    @JsonProperty("position_title")
    private StaffTitle positionTitle;

    @JsonProperty("position_description")
    private String positionDescription;
}
