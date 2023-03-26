package com.graduationproject.backend.backendwebsiteshoe.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SourceImageModel {
    
    private String imageId;

    private String fileName;

    private String fileType;

    private String fileCode;

    private byte[] fileSize;
    
}
