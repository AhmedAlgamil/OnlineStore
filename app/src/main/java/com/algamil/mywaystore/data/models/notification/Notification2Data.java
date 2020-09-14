package com.algamil.mywaystore.data.models.notification;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Awesome Pojo Generator
 * */
public class Notification2Data {
  @SerializedName("customer_location")
  @Expose
  private String customer_location;
  @SerializedName("updated_at")
  @Expose
  private String updated_at;
  @SerializedName("customer_phone")
  @Expose
  private String customer_phone;
  @SerializedName("created_at")
  @Expose
  private String created_at;
  @SerializedName("id")
  @Expose
  private Integer id;
  @SerializedName("customer_name")
  @Expose
  private String customer_name;
  public void setCustomer_location(String customer_location){
   this.customer_location=customer_location;
  }
  public String getCustomer_location(){
   return customer_location;
  }
  public void setUpdated_at(String updated_at){
   this.updated_at=updated_at;
  }
  public String getUpdated_at(){
   return updated_at;
  }
  public void setCustomer_phone(String customer_phone){
   this.customer_phone=customer_phone;
  }
  public String getCustomer_phone(){
   return customer_phone;
  }
  public void setCreated_at(String created_at){
   this.created_at=created_at;
  }
  public String getCreated_at(){
   return created_at;
  }
  public void setId(Integer id){
   this.id=id;
  }
  public Integer getId(){
   return id;
  }
  public void setCustomer_name(String customer_name){
   this.customer_name=customer_name;
  }
  public String getCustomer_name(){
   return customer_name;
  }
}