
package hotel_system;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class ROOMS {
    
    MY_CONNECTION my_connection = new MY_CONNECTION();
    
    // function pour afficher les chambes sur jtable
     public void fillRoomsTYPE_jTable(JTable table){
        PreparedStatement st;
        ResultSet rs;
        String selectQuery = "SELECT * FROM `type`";
        
        try {
            PreparedStatement ps = my_connection.createConnection().prepareStatement(selectQuery);
            
            rs = ps.executeQuery();
            
             DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
             
             Object[] row;
             
             while (rs.next()){
                  row = new Object[3];
                  row[0]= rs.getInt(1);
                  row[1]= rs.getString(2);
                  row[2]= rs.getString(3);

                  
                  tableModel.addRow(row);
                  
             }
            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
             
        }
     }
   // create a function pour charger combo id chambre type
         public void fillRoomsTYPE_jCombobox(JComboBox combobox){
        PreparedStatement st;
        ResultSet rs;
        String selectQuery = "SELECT * FROM `type`";
        
        try {
            PreparedStatement ps = my_connection.createConnection().prepareStatement(selectQuery);
            
            rs = ps.executeQuery();
            
                          
             while (rs.next()){
                  combobox.addItem(rs.getInt(1));   
                  
             }
            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
             
        }
     }
    // function pour afficher les chambes sur jtable
     public void fillRoomsjTable(JTable table){
        PreparedStatement st;
        ResultSet rs;
        String selectQuery = "SELECT * FROM `rooms`";
        
        try {
            PreparedStatement ps = my_connection.createConnection().prepareStatement(selectQuery);
            
            rs = ps.executeQuery();
            
             DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
             
             Object[] row;
             
             while (rs.next()){
                  row = new Object[4];
                  row[0]= rs.getInt(1);
                  row[1]= rs.getInt(2);
                  row[2]= rs.getString(3);
                  row[3]= rs.getString(4);
                  
                  tableModel.addRow(row);
                  
             }
            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
             
        }
     }
     // create a function pour ajouter une chambre
         public boolean addRoom(int number, int type ,String phone){
        PreparedStatement st;
        String addQuery = "INSERT INTO `rooms`(`r_number`, `type`, `phone`, `reserved`) VALUES (?,?,?,?)";
        
        try {
            st = my_connection.createConnection().prepareStatement(addQuery);
            st.setInt(1, number);
            st.setInt(2, type);
            st.setString(3, phone);
            st.setString(4, "No");

            
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
        public boolean editRoom(int number,int type ,String phone,String isReserved){
        PreparedStatement st;
        String editQuery = "UPDATE `rooms` SET `type`=?,`phone`=?,`reserved`=? WHERE `r_number`=?";
        
        try {
            st = my_connection.createConnection().prepareStatement(editQuery);
            st.setInt(1, type);
            st.setString(2, phone);
            st.setString(3, isReserved);
            st.setInt(4, number);
            return (st.executeUpdate() > 0);

            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
        
         public boolean removeRoom(int roomNumber){
        PreparedStatement st;
        String deleteQuery = "DELETE FROM `rooms` WHERE `r_number`=?";
        
        try {
            st = my_connection.createConnection().prepareStatement(deleteQuery); 
            st.setInt(1, roomNumber);
            

            return (st.executeUpdate() > 0);

            
        } catch (SQLException ex) {
            Logger.getLogger(CLIENT.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    // create a function to set a room reserved to reserved or no
         
         
         
         
         
         
         
         
         
}
