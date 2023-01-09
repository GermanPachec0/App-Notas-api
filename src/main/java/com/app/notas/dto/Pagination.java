package com.app.notas.dto;

import lombok.Getter;
import lombok.Setter;

public class Pagination {
    @Getter @Setter
    private  Integer TotalPages;

    @Getter @Setter
    private  Long TotalElements;
}
