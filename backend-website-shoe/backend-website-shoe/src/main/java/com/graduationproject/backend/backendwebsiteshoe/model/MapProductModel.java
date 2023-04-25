package com.graduationproject.backend.backendwebsiteshoe.model;

import com.graduationproject.backend.backendwebsiteshoe.dto.IComment;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MapProductModel {

  ProductModel productModel;

  List<IComment> commentList;
}
