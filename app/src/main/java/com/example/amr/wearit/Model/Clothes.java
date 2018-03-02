package com.example.amr.wearit.Model;

/**
 * Created by amr on 12/29/17.
 */

public class Clothes {

    private String     name,
    price,
    discount,categoryID,
    ItemImage,
    ItemAvailable,
    TotalNumber,
    S,
    M,
    L,
    Description;

    public Clothes() {
    }

    public Clothes(String name, String price, String discount, String categoryID, String itemImage, String itemAvailable, String totalNumber, String s, String m, String l, String description) {
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.categoryID = categoryID;
        ItemImage = itemImage;
        ItemAvailable = itemAvailable;
        TotalNumber = totalNumber;
        S = s;
        M = m;
        L = l;
        Description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getItemImage() {
        return ItemImage;
    }

    public void setItemImage(String itemImage) {
        ItemImage = itemImage;
    }

    public String getItemAvailable() {
        return ItemAvailable;
    }

    public void setItemAvailable(String itemAvailable) {
        ItemAvailable = itemAvailable;
    }

    public String getTotalNumber() {
        return TotalNumber;
    }

    public void setTotalNumber(String totalNumber) {
        TotalNumber = totalNumber;
    }

    public String getS() {
        return S;
    }

    public void setS(String s) {
        S = s;
    }

    public String getM() {
        return M;
    }

    public void setM(String m) {
        M = m;
    }

    public String getL() {
        return L;
    }

    public void setL(String l) {
        L = l;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }


}
