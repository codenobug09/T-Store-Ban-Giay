package com.tuyenvp.spring_boot_app.dto.request;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDTO {
    @Column(name = "category_name")
    private String category_name;
}
