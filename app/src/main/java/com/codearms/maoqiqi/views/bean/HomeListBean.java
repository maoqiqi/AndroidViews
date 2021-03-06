package com.codearms.maoqiqi.views.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 首页数据实体类
 * Author: fengqi.mao.march@gmail.com
 * Date: 2018/8/23 17:41
 */
public class HomeListBean {
    @SerializedName("error")
    private boolean error;
    @SerializedName("results")
    private List<ItemBean> resultList;

    public class ItemBean {
        @SerializedName(value = "_id", alternate = "ganhuo_id")
        private String id;
        private String desc;
        private List<String> images;
        private String publishedAt;
        private String type;
        private String url;
        private String who;

        // 自己添加属性
        private String imageUrl;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ItemBean> getResultList() {
        return resultList;
    }

    public void setResultList(List<ItemBean> resultList) {
        this.resultList = resultList;
    }

    @Override
    public String toString() {
        return "DataBean{" +
                "error=" + error +
                ", resultList=" + resultList +
                '}';
    }
}
