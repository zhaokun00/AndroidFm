package com.mongo.fm.bean;

/*
 **************************************************************************************
 * company    : 
 * Filename   : GoodsGson
 * Author 	  : zhaokun
 * Description: 
 * Date		  ：2018-03-15
 * Others	  :
 **************************************************************************************
 */
public class GoodsGson {

    private int id;
    private double price;

    private Data data;

    public GoodsGson(int id, double price, Data data) {
        this.id = id;
        this.price = price;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GoodsGson{" +
                "id=" + id +
                ", price=" + price +
                ", data=" + data +
                '}';
    }

    public static class Data {
        String name;

        public Data(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
