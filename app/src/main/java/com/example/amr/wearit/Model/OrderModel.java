package com.example.amr.wearit.Model;

/**
 * Created by amr on 12/30/17.
 */

public class OrderModel {
    private String ItemID;
    private String ItemName;
    private String ItemPrice;
    private String ItemQuantity;
    private String ItemDiscount;
    private String Size;
    private String Image;
    private String Uname;

    public OrderModel() {
    }

    public OrderModel(String itemID, String itemName, String itemPrice, String itemQuantity, String itemDiscount) {
        ItemID = itemID;
        ItemName = itemName;
        ItemPrice = itemPrice;
        ItemQuantity = itemQuantity;
        ItemDiscount = itemDiscount;
      //  Size=Size1;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getItemID() {
        return ItemID;
    }

    public void setItemID(String itemID) {
        ItemID = itemID;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getItemPrice() {
        return ItemPrice;
    }

    public void setItemPrice(String itemPrice) {
        ItemPrice = itemPrice;
    }

    public String getItemQuantity() {
        return ItemQuantity;
    }

    public void setItemQuantity(String itemQuantity) {
        ItemQuantity = itemQuantity;
    }

    public String getItemDiscount() {
        return ItemDiscount;
    }

    public void setItemDiscount(String itemDiscount) {
        ItemDiscount = itemDiscount;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }
}


