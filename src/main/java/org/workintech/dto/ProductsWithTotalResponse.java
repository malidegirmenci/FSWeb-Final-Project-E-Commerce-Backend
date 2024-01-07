package org.workintech.dto;

import java.util.List;

public record ProductsWithTotalResponse(List<ProductResponse> products, long total){
}
