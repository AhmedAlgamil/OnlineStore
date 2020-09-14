package com.algamil.mywaystore.data.models.favourite;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
/**
 * Awesome Pojo Generator
 * */
public class Favourite{
  @SerializedName("data")
  @Expose
  private List<FavouriteData> data;
  @SerializedName("message")
  @Expose
  private String message;
  @SerializedName("status")
  @Expose
  private Boolean status;
  public void setData(List<FavouriteData> data){
   this.data=data;
  }
  public List<FavouriteData> getData(){
   return data;
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