package com.algamil.mywaystore.data.models.login;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Awesome Pojo Generator
 * */
public class LoginData {
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("updated_at")
    @Expose
    private String updated_at;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("created_at")
    @Expose
    private String created_at;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("image_url")
    @Expose
    private String image_url;

    @SerializedName("location")
    @Expose
    private String location;


    public void setPassword(String password){
        this.password=password;
    }
    public String getPassword(){
        return password;
    }
    public void setUpdated_at(String updated_at){
        this.updated_at=updated_at;
    }
    public String getUpdated_at(){
        return updated_at;
    }
    public void setPhone(String phone){
        this.phone=phone;
    }
    public String getPhone(){
        return phone;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
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
    public void setEmail(String email){
        this.email=email;
    }
    public String getEmail(){
        return email;
    }
    public String getImage_url ( ) {
        return image_url;
    }
    public void setImage_url ( String image_url ) {
        this.image_url = image_url;
    }

    public String getLocation ( ) {
        return location;
    }

    public void setLocation ( String location ) {
        this.location = location;
    }
}