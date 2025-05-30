package org.group15.tveely;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.group15.tveely.dto.CategoryDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDtoImpl implements CategoryDto {
public Long  id;
public String category;
}
