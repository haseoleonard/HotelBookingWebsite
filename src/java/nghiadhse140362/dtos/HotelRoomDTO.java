/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiadhse140362.dtos;

import java.io.Serializable;

/**
 *
 * @author haseo
 */
public class HotelRoomDTO implements Serializable{
    private int roomID;
    private int hotelID;
    private int categoryID;
    private String image;
    private int quantity;

    public HotelRoomDTO() {
    }

    public HotelRoomDTO(int roomID, int hotelID, int categoryID, String image, int quantity) {
        this.roomID = roomID;
        this.hotelID = hotelID;
        this.categoryID = categoryID;
        this.image = image;
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
