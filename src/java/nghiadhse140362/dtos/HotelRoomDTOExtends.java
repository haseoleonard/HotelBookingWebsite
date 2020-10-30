/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiadhse140362.dtos;

/**
 *
 * @author haseo
 */
public class HotelRoomDTOExtends extends HotelRoomDTO{
    private String hotelName;
    private String categoryName;
    private int areaID;
    private String areaName;
    private int price;

    public HotelRoomDTOExtends() {
        super();
    }

    public HotelRoomDTOExtends(String hotelName, String categoryName, int areaID, String areaName, int price, int roomID, int hotelID, int categoryID, String image, int quantity) {
        super(roomID, hotelID, categoryID, image, quantity);
        this.hotelName = hotelName;
        this.categoryName = categoryName;
        this.areaID = areaID;
        this.areaName = areaName;
        this.price = price;
    }


    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getAreaID() {
        return areaID;
    }

    public void setAreaID(int areaID) {
        this.areaID = areaID;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
}
