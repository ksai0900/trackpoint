package com.appointments.trackpoint.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PaginationResponseMeta{
        private int currentPage;
        private int itemsPerPage;
        private long totalItems;
        private int totalPages;
}
