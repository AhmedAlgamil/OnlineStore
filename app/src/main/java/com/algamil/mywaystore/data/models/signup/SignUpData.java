package com.algamil.mywaystore.data.models.signup;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class SignUpData implements Serializable {

	@SerializedName("id")
	private String id;

	@SerializedName("name")
	private String name;

	@SerializedName("email")
	private String email;

	@SerializedName("phone")
	private String phone;

	@SerializedName("password")
	private String password;

	@SerializedName("location")
	private String location;

	@SerializedName("image_url")
	private String imageUrl;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("updated_at")
	private String updatedAt;

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setLocation(String location){
		this.location = location;
	}

	public String getLocation(){
		return location;
	}

	public void setImageUrl(String imageUrl){
		this.imageUrl = imageUrl;
	}

	public String getImageUrl(){
		return imageUrl;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	@Override
 	public String toString(){
		return 
			"SignUpData{" +
			"id = '" + id + '\'' + 
			",name = '" + name + '\'' + 
			",email = '" + email + '\'' + 
			",phone = '" + phone + '\'' + 
			",password = '" + password + '\'' + 
			",location = '" + location + '\'' + 
			",image_url = '" + imageUrl + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			"}";
		}
}