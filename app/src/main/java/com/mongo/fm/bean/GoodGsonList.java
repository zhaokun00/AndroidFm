package com.mongo.fm.bean;

import java.util.List;

/*
 **************************************************************************************
 * company    : 
 * Filename   : GoodGsonList
 * Author 	  : zhaokun
 * Description: 
 * Date		  ：2018-03-15
 * Others	  :
 **************************************************************************************
 */
public class GoodGsonList {

    private String name;
    private Data data;

    public GoodGsonList(String name, Data data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GoodGsonList{" +
                "name='" + name + '\'' +
                ", data=" + data +
                '}';
    }

    public static class Data {

        private List<Item> items;

        public Data(List<Item> items) {
            this.items = items;
        }

        public List<Item> getItems() {
            return items;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "items=" + items +
                    '}';
        }

        public void setItems(List<Item> items) {
            this.items = items;
        }

        public static class Item {

            private int id;

            public Item(int id) {
                this.id = id;
            }

            public int getId() {
                return id;
            }
            public void setId(int id) {
                this.id = id;
            }

            @Override
            public String toString() {
                return "Item{" +
                        "id=" + id +
                        '}';
            }
        }
    }
}
