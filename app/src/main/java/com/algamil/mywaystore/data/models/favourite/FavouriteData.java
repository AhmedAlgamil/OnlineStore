package com.algamil.mywaystore.data.models.favourite;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Awesome Pojo Generator
 * */
public class FavouriteData {
  @SerializedName("cost")
  @Expose
  private String cost;
  @SerializedName("quantity")
  @Expose
  private String quantity;
  @SerializedName("updated_at")
  @Expose
  private String updated_at;
  @SerializedName("image_url")
  @Expose
  private String image_url;
  @SerializedName("created_at")
  @Expose
  private String created_at;
  @SerializedName("id")
  @Expose
  private String id;
  @SerializedName("category")
  @Expose
  private String category;
  @SerializedName("product_details")
  @Expose
  private String product_details;
  @SerializedName("customer_id")
  @Expose
  private String customer_id;
  @SerializedName("product_name")
  @Expose
  private String product_name;
  public void setCost(String cost){
   this.cost=cost;
  }
  public String getCost(){
   return cost;
  }
  public void setQuantity(String quantity){
   this.quantity=quantity;
  }
  public String getQuantity(){
   return quantity;
  }
  public void setUpdated_at(String updated_at){
   this.updated_at=updated_at;
  }
  public String getUpdated_at(){
   return updated_at;
  }
  public void setImage_url(String image_url){
   this.image_url=image_url;
  }
  public String getImage_url(){
   return image_url;
  }
  public void setCreated_at(String created_at){
   this.created_at=created_at;
  }
  public String getCreated_at(){
   return created_at;
  }
  public void setId(String id){
   this.id=id;
  }
  public String getId(){
   return id;
  }
  public void setCategory(String category){
   this.category=category;
  }
  public String getCategory(){
   return category;
  }
  public void setProduct_details(String product_details){
   this.product_details=product_details;
  }
  public String getProduct_details(){
   return product_details;
  }
  public void setCustomer_id(String customer_id){
   this.customer_id=customer_id;
  }
  public String getCustomer_id(){
   return customer_id;
  }
  public void setProduct_name(String product_name){
   this.product_name=product_name;
  }
  public String getProduct_name(){
   return product_name;
  }
}