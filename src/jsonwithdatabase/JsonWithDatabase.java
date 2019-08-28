package jsonwithdatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JsonWithDatabase {

    public static void main(String[] args) {
        
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@144.217.163.57:1521:XE", "hr", "inf5180");
            
            String sql = "select COUNTRY_ID,COUNTRY_NAME,REGION_NAME from REGIONS,COUNTRIES where REGIONS.REGION_ID = COUNTRIES.REGION_ID";
            
            stm = con.createStatement();
            
            rs = stm.executeQuery(sql);
            
            
            String country_id,country_name,region_name;
            
            while(rs.next()){
                
            country_id = rs.getString("COUNTRY_ID");
            country_name = rs.getString("COUNTRY_NAME");
            region_name = rs.getString("REGION_NAME");
            System.out.println(country_id + " - " + country_name+ " - " + region_name);
                
            }
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JsonWithDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JsonWithDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException ex) {
                    
                }
            }
            
            if(stm != null){
                try {
                    stm.close();
                } catch (SQLException ex) {
                    
                }
            }
            if(con != null){
                try {
                    con.close();
                } catch (SQLException ex) {
                    
                }
            }
            
        }
        
    }
    
}
