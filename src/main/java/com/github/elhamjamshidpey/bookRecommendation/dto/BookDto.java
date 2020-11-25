package com.github.elhamjamshidpey.bookRecommendation.dto;

import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/*
@uthor by Elham
May 27, 2019
*/

@Validated

public class BookDto   {
  @JsonProperty("ASIN")
  private Integer ASIN = null;

  @JsonProperty("TITLE")
  private String TITLE = null;

  @JsonProperty("AUTHOR")
  private String AUTHOR = null;

  @JsonProperty("GENRE")
  private String GENRE = null;

  public BookDto() {
	  
  }
  
  public BookDto(Integer ASIN, String TITLE,String AUTHOR,String GENRE) {
	  this.ASIN = ASIN;
	  this.TITLE = TITLE;
	  this.AUTHOR = AUTHOR;
	  this.GENRE = GENRE;
  }
  
  public BookDto ASIN(Integer ASIN) {
    this.ASIN = ASIN;
    return this;
  }

  /**
   * Get ASIN
   * @return ASIN
  **/
  @ApiModelProperty(value = "")


  public Integer getASIN() {
    return ASIN;
  }

  public void setASIN(Integer ASIN) {
    this.ASIN = ASIN;
  }

  public BookDto TITLE(String TITLE) {
    this.TITLE = TITLE;
    return this;
  }

  /**
   * Get TITLE
   * @return TITLE
  **/
  @ApiModelProperty(value = "")


  public String getTITLE() {
    return TITLE;
  }

  public void setTITLE(String TITLE) {
    this.TITLE = TITLE;
  }

  public BookDto AUTHOR(String AUTHOR) {
    this.AUTHOR = AUTHOR;
    return this;
  }

  /**
   * Get AUTHOR
   * @return AUTHOR
  **/
  @ApiModelProperty(value = "")


  public String getAUTHOR() {
    return AUTHOR;
  }

  public void setAUTHOR(String AUTHOR) {
    this.AUTHOR = AUTHOR;
  }

  public BookDto GENRE(String GENRE) {
    this.GENRE = GENRE;
    return this;
  }

  /**
   * Get GENRE
   * @return GENRE
  **/
  @ApiModelProperty(value = "")


  public String getGENRE() {
    return GENRE;
  }

  public void setGENRE(String GENRE) {
    this.GENRE = GENRE;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BookDto book = (BookDto) o;
    return Objects.equals(this.ASIN, book.ASIN) &&
        Objects.equals(this.TITLE, book.TITLE) &&
        Objects.equals(this.AUTHOR, book.AUTHOR) &&
        Objects.equals(this.GENRE, book.GENRE);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ASIN, TITLE, AUTHOR, GENRE);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Book {\n");
    
    sb.append("    ASIN: ").append(toIndentedString(ASIN)).append("\n");
    sb.append("    TITLE: ").append(toIndentedString(TITLE)).append("\n");
    sb.append("    AUTHOR: ").append(toIndentedString(AUTHOR)).append("\n");
    sb.append("    GENRE: ").append(toIndentedString(GENRE)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
