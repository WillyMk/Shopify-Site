package com.example.productservice.utility;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pagination<T> {
    private T content;
    private int page;
    private int pageSize;
    private Long totalElements;
    private int totalPages;
}
