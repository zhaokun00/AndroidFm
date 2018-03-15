package com.mongo.fm.bean;

import java.util.List;

/*
 **************************************************************************************
 * company    : 
 * Filename   : GoodsInfo
 * Author 	  : zhaokun
 * Description: 
 * Date		  ：2018-03-14
 * Others	  :
 **************************************************************************************
 */
public class GoodsInfo {

    private String rs_code;
    private String rs_msg;
    private DataInfo data;

    @Override
    public String toString() {
        return "GoodsInfo{" +
                "rs_code='" + rs_code + '\'' +
                ", rs_msg='" + rs_msg + '\'' +
                ", data=" + data +
                '}';
    }

    public String getRs_msg() {
        return rs_msg;
    }

    public void setRs_msg(String rs_msg) {
        this.rs_msg = rs_msg;
    }

    public String getRs_code() {
        return rs_code;
    }

    public void setRs_code(String rs_code) {
        this.rs_code = rs_code;
    }

    public DataInfo getData() {
        return data;
    }

    public void setData(DataInfo data) {
        this.data = data;
    }


    public static class DataInfo {

        private int count;
        private List<Items> items;

        public List<Items> getItems() {
            return items;
        }

        public void setItems(List<Items> items) {
            this.items = items;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        @Override
        public String toString() {
            return "DataInfo{" +
                    "count=" + count +
                    ", items=" + items +
                    '}';
        }

        public static class Items {
            private int id;
            private String title;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            @Override
            public String toString() {
                return "Items{" +
                        "id=" + id +
                        ", title='" + title + '\'' +
                        '}';
            }
        }
    }
}
