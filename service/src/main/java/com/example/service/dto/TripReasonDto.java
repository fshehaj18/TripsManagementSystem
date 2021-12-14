package com.example.service.dto;

import com.example.service.model.TripReason;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
public class TripReasonDto {

    @Enumerated(EnumType.ORDINAL)
    private TripReason tripReason;
}
