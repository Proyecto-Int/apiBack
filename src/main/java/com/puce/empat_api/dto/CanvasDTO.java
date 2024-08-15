package com.puce.empat_api.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CanvasDTO {
    private Long id;
    private String name;
    private String description;
    private String dateCreated;
    private Long userId;
}
