
package hotel_system;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class RESERVATION {
    MY_CONNECTION my_connection = new MY_CONNECTION();
     
    
      public boolean addRreservation(int client_id, int room_number,String dateIn , String dateOut){
        PreparedStatement st;
        String addQuery = "INSERT INTO `reservations`( `client_id`, `room_number`, `date_in`, `date_out`) VALUES (?,?,?,?)";
        
        try {
            st = my_connection.createConnection().prepareStatement(addQuery);
            st.setInt(1, client_id);
            st.setInt(2, room_number);
            st.setString(3, dateIn);
            st.setString(4, dateOut);

            
            if (st.executeUpdate() > 0){
                return true;
            }else{
                return false;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
       public boolean editReservation(int reservation_id,int client_id, int room_number,String dateIn , String dateOut){
        PreparedStatement st;
        String editQuery = "UPDATE `reservations` SET `client_id`=?,`room_number`=?,`date_in`=?,`date_out`=? WHERE `id`=?";
        
        try {
            st = my_connection.createConnection().prepareStatement(editQuery);
            st.setInt(1, reservation_id);
            st.setInt(2, client_id);
            st.setInt(3, room_number);
            st.setString(4, dateIn);
            st.setString(5, dateOut);
            return (st.executeUpdate() > 0);

            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
       
        public boolean removeReservation(int reservation_id){
        PreparedStatement st;
        String deleteQuery = "DELETE FROM `reservations` WHERE id =?";
        
        try {
            st = my_connection.createConnection().prepareStatement(deleteQuery); 
            st.setInt(1, reservation_id);
            

            return (st.executeUpdate() > 0);

            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
        
     public void fillReservationjTable(JTable table){
        PreparedStatement st;
        ResultSet rs;
        String selectQuery = "SELECT * FROM `reservations`";
        
        try {
            PreparedStatement ps = my_connection.createConnection().prepareStatement(selectQuery);
            
            rs = ps.executeQuery();
            
             DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
             
             Object[] row;
             
             while (rs.next()){
                  row = new Object[5];
                  row[0]= rs.getInt(1);
                  row[1]= rs.getInt(2);
                  row[2]= rs.getInt(3);
                  row[3]= rs.getString(4);
                  row[4]= rs.getString(5);
                  
                  tableModel.addRow(row);
                  
             }
            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
             
        }
     }


    
}
