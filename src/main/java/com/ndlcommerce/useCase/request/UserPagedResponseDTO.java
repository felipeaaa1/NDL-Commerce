package com.ndlcommerce.useCase.request;

import java.util.List;

public class UserPagedResponseDTO {
  private long total;
  private int page;
  private int pageSize;
  private List<UserResponseDTO> data;

  public UserPagedResponseDTO(long total, int page, int pageSize, List<UserResponseDTO> data) {
    this.total = total;
    this.page = page;
    this.pageSize = pageSize;
    this.data = data;
  }

  public long getTotal() {
    return total;
  }

  public int getPage() {
    return page;
  }

  public int getPageSize() {
    return pageSize;
  }

  public List<UserResponseDTO> getData() {
    return data;
  }
}
