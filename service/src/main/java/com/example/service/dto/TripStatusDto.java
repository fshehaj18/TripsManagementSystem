package com.example.service.dto;

import com.example.service.model.TripStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
public class TripStatusDto {

    @Enumerated(EnumType.ORDINAL)
    private TripStatus tripStatus;

}
