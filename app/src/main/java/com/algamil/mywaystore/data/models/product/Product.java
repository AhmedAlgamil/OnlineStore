package com.algamil.mywaystore.data.models.product;

import java.util.List;

public class Product implements java.io.Serializable {
    private static final long serialVersionUID = - 4578474164514838434L;
    private List <ProductData> data;
    private String message;
    private String status;

    public List< ProductData> getData ( ) {
        return this.data;
    }

    public void setData ( List<ProductData> data ) {
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
