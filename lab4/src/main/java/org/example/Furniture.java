package org.example;
import java.text.*;
import java.util.*;

import org.json.simple.JSONObject;

import java.io.*;
import java.lang.*;


public class Furniture {
    private String type;
    private String material;
    private Integer price;

    Furniture(){
        type = "";
        material = "";
        price = 0;
    }

    Furniture(String t_type, String t_material, int  t_price) throws Exception{
        setType(t_type);
        setMaterial(t_material);
        setPrice(t_price);
    }

    Furniture(JSONObject object){
        type = (String)object.get("type");
        material = (String)object.get("material");
        long tempPrice = (long)object.get("price");
        price = (int)tempPrice;
    }

    public void setType(String t_type) {
        type = t_type;
    }

    public void setMaterial(String t_material) throws Exception{
        if(t_material.equals("steel") && t_material.equals("wood")){
            throw new Exception("wrong material");
        }
        material = t_material;
    }

    public void setPrice(int t_price) throws Exception{
        if(t_price < 0) {
            throw new Exception("price < 0");
        }
        price = t_price;
    }

    public String getType() {
        return type;
    }

    public String getMaterial() {
        return material;
    }

    public Integer getPrice() {
        return price;
    }

    public String toString() {
        String result = "";
        result += "type: " + type + " material: "
                + material + " price: " + price;
        return result;
    }
}
