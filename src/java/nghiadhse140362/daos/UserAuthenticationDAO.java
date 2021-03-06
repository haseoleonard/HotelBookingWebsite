/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiadhse140362.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.naming.NamingException;
import nghiadhse140362.utils.DBHelpers;

/**
 *
 * @author haseo
 */
public class UserAuthenticationDAO implements Serializable{
    private static final int TIMELIMIT = 60*60*3;
    private Connection con;
    private PreparedStatement psm;
    private ResultSet rs;

    public UserAuthenticationDAO() {
        this.con = null;
        this.psm = null;
        this.rs = null;
    }

    private void closeConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (psm != null) {
            psm.close();
        }
        if (con != null) {
            con.close();
        }
    }
    
    public boolean createAuthenRecord(String email,String verifyCode) throws SQLException, NamingException{
        try{
            con = DBHelpers.makeConnection();
            if(con!=null){
                String sql = "insert into UserAuthentication(email,verifyCode) "
                        + "values(?,?)";
                psm = con.prepareStatement(sql);
                psm.setString(1, email);
                psm.setString(2, verifyCode);
                int res = psm.executeUpdate();
                if(res>0)return true;
            }
        }finally{
            closeConnection();
        }
        return false;
    }
    
    public boolean checkAuthCode(String email,String verifyCode) throws SQLException, NamingException{
        try{
            con = DBHelpers.makeConnection();
            if(con!=null){
                String sql = "select email from UserAuthentication "
                        + "where email=? and verifyCode=? "
                        + "and DATEDIFF(ss,generatedTime,GETDATE())<?";
                psm = con.prepareStatement(sql);
                psm.setString(1, email);
                psm.setString(2, verifyCode);
                psm.setInt(3, TIMELIMIT);
                rs = psm.executeQuery();
                if(rs.next())return true;
            }
        }finally{
            closeConnection();
        }
        return false;
    }
    private String authCode;

    public String getAuthCode() {
        return authCode;
    }
    
    public Timestamp getAuthCodeTimeOutDate(String email) throws SQLException, NamingException{
        Timestamp AuthCodeExpTime = null;
        try{
            con = DBHelpers.makeConnection();
            if(con!=null){
                String sql = "select authCode,DATEADD(ss,?,generatedTime) as expTime"
                        + " from UserAuthentication "
                        + "where email=? "
                        + "and DATEDIFF(ss,generatedTime,GETDATE())<?";
                psm=con.prepareStatement(sql);
                psm.setInt(1, TIMELIMIT);
                psm.setString(2, email);
                psm.setInt(3, TIMELIMIT);
                rs = psm.executeQuery();
                if(rs.next()){
                    authCode=rs.getString("authCode");
                    AuthCodeExpTime = rs.getTimestamp("expTime");
                }
            }
        }finally{
            closeConnection();
        }
        return AuthCodeExpTime;
    }
    public boolean deleteTimeOutAuthCode() throws NamingException, SQLException{
        try{
            con = DBHelpers.makeConnection();
            if(con!=null){
                String sql = "delete from UserAuthentication "
                        + "where DATEDIFF(ss,generatedTime,GETDATE())>?";
                psm=con.prepareStatement(sql);
                psm.setInt(1, TIMELIMIT);
                int rs = psm.executeUpdate();
                if(rs>0)return true;
            }
        }finally{
            closeConnection();
        }
        return false;
    }
}
