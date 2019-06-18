package com.gystry.newrun.bean;

import org.json.JSONException;
import org.json.JSONObject;
import com.google.gson.Gson;

public class LiveListBean {

    /**
     * id : e72ece75457244ffa080d3eeb592b5ba
     * createBy : 1
     * createDate : 2019-05-30 17:03:19
     * updateDate : 2019-06-04 14:24:14
     * updateBy : 1
     * roomNum : 666
     * coachId : 0ea84a37ed3b401ca930c48871021f8e
     * typeId : 013b109ca0d344f5950e3fa59b7c733e
     * startDate : 2019-06-27 19:00:00
     * endDate : 2019-06-27 20:00:00
     * userNum : 202
     * application : 超简单的瑜伽串联 提臀又瘦腿适合初学入门小白，这4个超简单的动作，每次重复2-3遍。
     * title : 经典初级瑜伽教程
     * picture : http://cdn.suipaohealthy.com/99fab559a3ec45408d6076c5ceb656da.jpg
     * status : 1
     * coachName : 瑜伽小队长
     * typeName : 塑形
     * photo : http://cdn.suipaohealthy.com/f9a21c423f4942e794d98fdf64b1a63a.png
     * headPhoto : http://cdn.suipaohealthy.com/abc753ba3860498287f04b16dcbb41e7.png
     * description : 超简单的瑜伽串联
     */

    private String id;
    private String createBy;
    private String createDate;
    private String updateDate;
    private String updateBy;
    private String roomNum;
    private String coachId;
    private String typeId;
    private String startDate;
    private String endDate;
    private int userNum;
    private String application;
    private String title;
    private String picture;
    private int status;
    private String coachName;
    private String typeName;
    private String photo;
    private String headPhoto;
    private String description;

    public static LiveListBean objectFromData(String str) {

        return new Gson().fromJson(str, LiveListBean.class);
    }

    public static LiveListBean objectFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);

            return new Gson().fromJson(jsonObject.getString(str), LiveListBean.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public String getCoachId() {
        return coachId;
    }

    public void setCoachId(String coachId) {
        this.coachId = coachId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getUserNum() {
        return userNum;
    }

    public void setUserNum(int userNum) {
        this.userNum = userNum;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getHeadPhoto() {
        return headPhoto;
    }

    public void setHeadPhoto(String headPhoto) {
        this.headPhoto = headPhoto;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
