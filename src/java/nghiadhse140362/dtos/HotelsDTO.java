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
public class HotelsDTO implements Serializable{
    private int hotelID;
    private String hotelName;

    public HotelsDTO() {
    }

    public HotelsDTO(int hotelID, String hotelName) {
        this.hotelID = hotelID;
        this.hotelName = hotelName;
    }

    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }
    
}
