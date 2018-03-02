package com.example.amr.wearit.Model;

import java.util.List;

/**
 * Created by amr on 12/30/17.
 */

public class Request {

    private String address;
    private String total;
    private List<OrderModel> Clothes;
    private String Name;
    private String status;
    public Request(){
    }

    public Request(String address, String total, List<OrderModel> clothes, String name) {
        this.address = address;
        this.total = total;
        Clothes = clothes;
        this.Name = name;
        this.status="0";
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {

        this.status = status;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public void setClothes(List<OrderModel> clothes) {
        Clothes = clothes;
    }



    public String getAddress() {
        return address;
    }

    public String getTotal() {
        return total;
    }

    public List<OrderModel> getClothes() {
        return Clothes;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
