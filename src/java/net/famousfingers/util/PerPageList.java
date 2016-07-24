package net.famousfingers.util;

public class PerPageList
{
  public static final int NO_OF_RECORDS_PER_PAGE = 10;
  int total_no_of_pages = 0;
  
  public int getNumberOfPages(int page_no, int total_rows)
  {
    if (total_rows % 10 == 0) {
      this.total_no_of_pages = (total_rows / 10);
    } else {
      this.total_no_of_pages = (total_rows / 10 + 1);
    }
    return this.total_no_of_pages;
  }
  
  public int getCustomizedNumberOfPages(int total_rows, int first_page_records)
  {
    if (total_rows > first_page_records)
    {
      total_rows -= first_page_records;
      if (total_rows % 10 == 0) {
        this.total_no_of_pages = (total_rows / 10);
      } else {
        this.total_no_of_pages = (total_rows / 10 + 1);
      }
    }
    else
    {
      this.total_no_of_pages = 0;
    }
    return this.total_no_of_pages + 1;
  }
  
  int start_index = 0;
  
  public int getStartIndex(int page_no)
  {
    this.start_index = ((page_no - 1) * 10);
    return this.start_index;
  }
  
  public int getCustomizedStartIndex(int page_no)
  {
    if (page_no == 1) {
      this.start_index = 0;
    } else {
      this.start_index = ((page_no - 1) * 10 + 10);
    }
    return this.start_index;
  }
}
