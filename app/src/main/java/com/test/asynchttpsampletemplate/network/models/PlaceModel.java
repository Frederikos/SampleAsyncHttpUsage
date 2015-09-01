package com.test.asynchttpsampletemplate.network.models;

import com.google.gson.annotations.Expose;
import com.test.asynchttpsampletemplate.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class PlaceModel {
    @Expose
    private String id;
    @Expose
    private String subtext;
    @Expose
    private String category;
    @Expose
    private String title;
    @Expose
    private Integer price;
    @Expose
    private String name;
    @Expose
    private String image;

    public static List<PlaceModel> createPlaceModelListFromJson(JSONObject jsonObject) {
        ArrayList<PlaceModel> result = new ArrayList<>();
        if (jsonObject != null) {
            try {
                JSONArray items = jsonObject.getJSONObject("block").getJSONArray("items");
                for (int i = 0; i < items.length(); i++) {
                    result.add(Utils.getGson().fromJson(items.getJSONObject(i).toString(), PlaceModel.class));
                }
            } catch (JSONException ex) {
                Timber.e(ex.toString());
            }
        }
        return result;
    }

    /**
     * @return The id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return The subtext
     */
    public String getSubtext() {
        return subtext;
    }

    /**
     * @param subtext The subtext
     */
    public void setSubtext(String subtext) {
        this.subtext = subtext;
    }

    /**
     * @return The category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category The category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The price
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * @param price The price
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image The image
     */
    public void setImage(String image) {
        this.image = image;
    }

}
