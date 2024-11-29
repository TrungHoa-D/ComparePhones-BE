package com.example.projectbase.domain.dto.pagination;

import com.example.projectbase.constant.CommonConstant;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PaginationFullRequestDto extends PaginationSortRequestDto {

  @Parameter(description = "Keyword to search")
  private String keyword = CommonConstant.EMPTY_STRING;

  @Parameter(description = "Filters as a JSON object.")
  private Object filters;

  public String getKeyword() {
    return keyword.trim();
  }

}
