package com.staff.staffmanagement.mapstruct.dtos;
import com.fasterxml.jackson.annotation.JsonProperty;

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
    private String positionTitle;

    @JsonProperty("position_description")
    private String positionDescription;
}
