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

public class FeedbackDto   {
  @JsonProperty("ASIN")
  private Integer ASIN = null;

  @JsonProperty("like_status")
  private String likeStatus = null;

  public FeedbackDto ASIN(Integer ASIN) {
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

  public FeedbackDto likeStatus(String likeStatus) {
    this.likeStatus = likeStatus;
    return this;
  }

  /**
   * Get likeStatus
   * @return likeStatus
  **/
  @ApiModelProperty(value = "")


  public String getLikeStatus() {
    return likeStatus;
  }

  public void setLikeStatus(String likeStatus) {
    this.likeStatus = likeStatus;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FeedbackDto feedback = (FeedbackDto) o;
    return Objects.equals(this.ASIN, feedback.ASIN) &&
        Objects.equals(this.likeStatus, feedback.likeStatus);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ASIN, likeStatus);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Feedback {\n");
    
    sb.append("    ASIN: ").append(toIndentedString(ASIN)).append("\n");
    sb.append("    likeStatus: ").append(toIndentedString(likeStatus)).append("\n");
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