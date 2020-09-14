package com.algamil.mywaystore.data.models.login;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/**
 * Awesome Pojo Generator
 * */
public class Login{
  @SerializedName("data")
  @Expose
  private List< LoginData > data;
  @SerializedName("customer_id")
  @Expose
  private String customer_id;
  @SerializedName("message")
  @Expose
  private String message;
  @SerializedName("status")
  @Expose
  private Boolean status;
  public void setData(List< LoginData > data){
   this.data=data;
  }
  public List< LoginData > getData(){
   return data;
  }
  public void setCustomer_id(String customer_id){
   this.customer_id=customer_id;
  }
  public String getCustomer_id(){
   return customer_id;
  }
  public void setMessage(String message){
   this.message=message;
  }
  public String getMessage(){
   return message;
  }
  public void setStatus(Boolean status){
   this.status=status;
  }
  public Boolean getStatus(){
   return status;
  }
}