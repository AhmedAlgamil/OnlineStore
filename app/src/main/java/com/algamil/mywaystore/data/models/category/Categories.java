package com.algamil.mywaystore.data.models.category;

public class Categories implements java.io.Serializable {
    private static final long serialVersionUID = 6849705858325444072L;
    private CategoriesData[] data;
    private String message;
    private String status;

    public CategoriesData[] getData ( ) {
        return this.data;
    }

    public void setData ( CategoriesData[] data ) {
        this.data = data;
    }

    public String getMessage ( ) {
        return this.message;
    }

    public void setMessage ( String message ) {
        this.message = message;
    }

    public String getStatus ( ) {
        return this.status;
    }

    public void setStatus ( String status ) {
        this.status = status;
    }
}
