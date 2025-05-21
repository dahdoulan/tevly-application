package org.group15.tveely;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.group15.tveely.models.CategoryAdapter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category implements CategoryAdapter {
public Long  id;
public String category;
}
