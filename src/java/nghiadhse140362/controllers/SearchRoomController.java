/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiadhse140362.controllers;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nghiadhse140362.daos.HotelRoomsDAO;
import nghiadhse140362.daos.OrderDetailsDAO;
import nghiadhse140362.dtos.HotelRoomDTOExtends;
import nghiadhse140362.utils.Constants;
import org.apache.log4j.Logger;

/**
 *
 * @author haseo
 */
@WebServlet(name = "SearchRoomController", urlPatterns = {"/SearchRoomController"})
public class SearchRoomController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(SearchRoomController.class);

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String areaName = request.getParameter("txtArea");
            String hotelName = request.getParameter("txtHotelName");
            String checkin = request.getParameter("checkInDate");
            String checkout = request.getParameter("checkOutDate");
            LocalDate date = null;
            try {
                date = LocalDate.parse(checkin);
            } catch (Exception ex) {
                date = LocalDate.now();
                LOGGER.error(ex.getClass() + ": " + ex.getMessage());
            }
            Date checkinDate = Date.valueOf(date);
            try {
                date = LocalDate.parse(checkout);
            } catch (Exception ex) {
                date = LocalDate.now();
                LOGGER.error(ex.getClass() + ": " + ex.getMessage());
            }
            Date checkOutDate = Date.valueOf(date);
            if (areaName == null) {
                areaName = "";
            }
            if (hotelName == null) {
                hotelName = "";
            }
            HotelRoomsDAO dao = new HotelRoomsDAO();
            int result = dao.getAllRoom(areaName, hotelName);
            List<HotelRoomDTOExtends> roomList = dao.getRoomList();
            OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO();
            orderDetailsDAO.loadRentedList(checkinDate, checkOutDate);
            Map<Integer, Integer> rentedList = orderDetailsDAO.getRentedList();
            if (rentedList != null && !rentedList.isEmpty()) {
                Iterator<HotelRoomDTOExtends> iter = roomList.iterator();
                while (iter.hasNext()) {
                    HotelRoomDTOExtends hotelRoomDTOExtends = iter.next();
                    if (rentedList.containsKey(hotelRoomDTOExtends.getRoomID())) {
                        int rented = rentedList.get(hotelRoomDTOExtends.getRoomID());
                        int defaultQuantity = hotelRoomDTOExtends.getQuantity();
                        hotelRoomDTOExtends.setQuantity(defaultQuantity - rented);
                    }
                    if (hotelRoomDTOExtends.getQuantity() <= 0) {
                        iter.remove();
                    }
                }
            }
            if (roomList.size() > 0) {
                request.setAttribute("ROOM_LIST", roomList);
            }
        } catch (SQLException | NamingException ex) {
            LOGGER.error(ex.getMessage());
        } finally {
            request.getRequestDispatcher(Constants.HOME_PAGE).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
