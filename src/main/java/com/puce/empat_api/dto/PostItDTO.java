package com.puce.empat_api.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostItDTO {
    private Long id;
    private Long quadrantId;
    private String text;
    private Long canvasId;
}

