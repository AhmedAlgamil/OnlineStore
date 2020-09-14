package com.algamil.mywaystore.data.models.category;

public class CategoriesData implements java.io.Serializable {
    private static final long serialVersionUID = 2538183929455065753L;
    private String cost;
    private String quantity;
    private String updated_at;
    private String image_url;
    private String created_at;
    private String id;
    private String category;
    private String product_details;
    private String product_name;

    public String getCost ( ) {
        return this.cost;
    }

    public void setCost ( String cost ) {
        this.cost = cost;
    }

    public String getQuantity ( ) {
        return this.quantity;
    }

    public void setQuantity ( String quantity ) {
        this.quantity = quantity;
    }

    public String getUpdated_at ( ) {
        return this.updated_at;
    }

    public void setUpdated_at ( String updated_at ) {
        this.updated_at = updated_at;
    }

    public String getImage_url ( ) {
        return this.image_url;
    }

    public void setImage_url ( String image_url ) {
        this.image_url = image_url;
    }

    public String getCreated_at ( ) {
        return this.created_at;
    }

    public void setCreated_at ( String created_at ) {
        this.created_at = created_at;
    }

    public String getId ( ) {
        return this.id;
    }

    public void setId ( String id ) {
        this.id = id;
    }

    public String getCategory ( ) {
        return this.category;
    }

    public void setCategory ( String category ) {
        this.category = category;
    }

    public String getProduct_details ( ) {
        return this.product_details;
    }

    public void setProduct_details ( String product_details ) {
        this.product_details = product_details;
    }

    public String getProduct_name ( ) {
        return this.product_name;
    }

    public void setProduct_name ( String product_name ) {
        this.product_name = product_name;
    }
}
