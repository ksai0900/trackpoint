package com.appointments.trackpoint.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PaginationResponse<T> {
        private List<T> data;
        private PaginationResponseMeta meta;
}
