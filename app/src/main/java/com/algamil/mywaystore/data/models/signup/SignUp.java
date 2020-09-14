package com.algamil.mywaystore.data.models.signup;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class SignUp implements Serializable {

	@SerializedName("status")
	private String status;

	@SerializedName("customer_id")
	private String customerId;

	@SerializedName("message")
	private String message;

	@SerializedName("data")
	private List< SignUpData > data;

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	public void setCustomerId(String customerId){
		this.customerId = customerId;
	}

	public String getCustomerId(){
		return customerId;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setData(List< SignUpData > data){
		this.data = data;
	}

	public List< SignUpData > getData(){
		return data;
	}

	@Override
 	public String toString(){
		return 
			"SignUp{" +
			"status = '" + status + '\'' + 
			",customer_id = '" + customerId + '\'' + 
			",message = '" + message + '\'' + 
			",data = '" + data + '\'' + 
			"}";
		}
}