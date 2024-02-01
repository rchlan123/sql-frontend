import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import net.proteanit.sql.DbUtils;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author me
 */
public class return_book extends javax.swing.JFrame {

    Connection con=null;
    Statement st=null;
    PreparedStatement ps=null;
    ResultSet rs=null;
    
    
    /**
     * Creates new form return_book
     */
    
    
    public return_book() {
        initComponents();
        tableReturn();
        tableBorrow();
        generatedId();
        
       setDefaultCloseOperation(return_book.EXIT_ON_CLOSE);

    }
    

    
private void tableReturn() {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdb?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "1234");

        String sqlQuery = "SELECT return_id, borrow.borrow_id, borrow.book_id_brfk, borrow.person_id_brfk AS person_id_rfk, return_date FROM return_table JOIN borrow ON return_table.borrow_id_rfk = borrow.borrow_id";

        ps = con.prepareStatement(sqlQuery);
        rs = ps.executeQuery();
        returnTable.setModel(DbUtils.resultSetToTableModel(rs));
    } catch (ClassNotFoundException | SQLException e) {
        JOptionPane.showMessageDialog(null, "Error fetching return details: " + e.getMessage());
    }
}

   private void tableBorrow(){        
        try{
        
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdb?zeroDateTimeBehavior=CONVERT_TO_NULL","root","1234");
            
            String sqlQuery = "SELECT borrow.borrow_id, borrow.book_id_brfk AS book_id, person.person_id, person.name, borrow.borrow_date, borrow.due_date, book.status FROM borrow JOIN book ON borrow.book_id_brfk = book.book_id JOIN person ON borrow.person_id_brfk = person.person_id;";
            
            ps = con.prepareStatement(sqlQuery);
            rs = ps.executeQuery();
            tableBorrow.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    
    
    }

          private void generatedId(){
    
    try{
      String sqlQuery = "SELECT MAX(return_id) AS last_id FROM return_table";
        ps = con.prepareStatement(sqlQuery);
        rs = ps.executeQuery();

        // Check if there is a result
        if (rs.next()) {
            // Get the last ID from the result set
            int lastId = rs.getInt("last_id");
            
            // Increment the last ID by 1 to generate the new ID
            int newId = lastId + 1;
            
            // Set the new ID to the borrow_id field
            return_idT.setText(Integer.toString(newId));
        }
        
    }catch(SQLException e){
                JOptionPane.showMessageDialog(null, e);

    }}
    
          private void deleteRecord(int idToDelete) {
   try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdb?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "1234");
        // Prepare the SQL DELETE statement for return_table
        String deleteQuery = "DELETE FROM return_table WHERE return_id = ?";
        ps = con.prepareStatement(deleteQuery);
        ps.setInt(1, idToDelete);

        // Execute the DELETE statement
        ps.executeUpdate();

        // Optionally, you can also update the book status or perform other actions here

        JOptionPane.showMessageDialog(null, "Record with ID " + idToDelete + " has been deleted successfully.");
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error deleting record: " + e.getMessage());
    }   catch (ClassNotFoundException ex) {
            Logger.getLogger(return_book.class.getName()).log(Level.SEVERE, null, ex);
        }
}
          
              private void reload() {
      try {
          Class.forName("com.mysql.cj.jdbc.Driver");
         con=DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdb?zeroDateTimeBehavior=CONVERT_TO_NULL","root","1234");
        String sqlQuery = "SELECT return_id,  borrow.borrow_id, borrow.book_id_brfk, borrow.person_id_brfk AS person_id_rfk, return_date FROM return_table JOIN borrow ON return_table.borrow_id_rfk = borrow.borrow_id";
        ps = con.prepareStatement(sqlQuery);
        rs = ps.executeQuery();
        
        // Set the data into the table model
        returnTable.setModel(DbUtils.resultSetToTableModel(rs));
        
        // Close the ResultSet and PreparedStatement
        rs.close();
        ps.close();
    } catch (SQLException ex) {
        // Handle SQL exception
        ex.printStackTrace();
    }   catch (ClassNotFoundException ex) {
            Logger.getLogger(return_book.class.getName()).log(Level.SEVERE, null, ex);
        }
}

   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        back = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        search = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        menu = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        returnTable = new javax.swing.JTable();
        return_idT = new javax.swing.JTextField();
        add = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        update = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        person_idT = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        borrow_idT = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        return_dateT = new com.toedter.calendar.JDateChooser();
        jScrollPane5 = new javax.swing.JScrollPane();
        tableBorrow = new javax.swing.JTable();
        search2 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("League Spartan Black", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("RETURN BOOK DETAILS");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, -1, 20));

        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/back-button.png"))); // NOI18N
        back.setBorder(null);
        back.setBorderPainted(false);
        back.setContentAreaFilled(false);
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });
        getContentPane().add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, -1, -1));

        jLabel12.setFont(new java.awt.Font("Montserrat ExtraBold", 1, 13)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Return Date");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 300, 100, 20));

        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });
        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchKeyReleased(evt);
            }
        });
        getContentPane().add(search, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 250, 200, 23));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/search.png"))); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 250, -1, -1));

        menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/menu icon.png"))); // NOI18N
        menu.setBorderPainted(false);
        menu.setContentAreaFilled(false);
        menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuActionPerformed(evt);
            }
        });
        getContentPane().add(menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 40, 30, -1));

        returnTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ));
        returnTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                returnTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(returnTable);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 290, 350, 160));

        return_idT.setText(" ");
        return_idT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                return_idTActionPerformed(evt);
            }
        });
        getContentPane().add(return_idT, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 180, 130, -1));

        add.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        add.setText("RETURN");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        getContentPane().add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 370, 110, 30));

        jLabel13.setFont(new java.awt.Font("Montserrat ExtraBold", 1, 13)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Person ID");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 220, 100, 20));

        update.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        update.setText("UPDATE");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });
        getContentPane().add(update, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 370, 110, 30));

        delete.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        delete.setText("DELETE");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });
        getContentPane().add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 370, 110, 30));

        jLabel14.setFont(new java.awt.Font("Montserrat ExtraBold", 1, 13)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Borrow ID");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 260, 100, 20));

        person_idT.setText(" ");
        person_idT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                person_idTActionPerformed(evt);
            }
        });
        getContentPane().add(person_idT, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, 130, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reload.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 30, 30, 40));

        borrow_idT.setText(" ");
        borrow_idT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrow_idTActionPerformed(evt);
            }
        });
        getContentPane().add(borrow_idT, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 260, 130, -1));

        jLabel16.setFont(new java.awt.Font("Montserrat ExtraBold", 1, 13)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Return ID");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, 100, 20));
        getContentPane().add(return_dateT, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 300, 130, -1));

        tableBorrow.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7"
            }
        ));
        tableBorrow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableBorrowMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tableBorrow);

        getContentPane().add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 100, 350, 130));

        search2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search2ActionPerformed(evt);
            }
        });
        search2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                search2KeyReleased(evt);
            }
        });
        getContentPane().add(search2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 70, 200, 23));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/search.png"))); // NOI18N
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 70, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fade_background.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        this.dispose();
        this.hide();
        action_window frm=new action_window();
        frm.setVisible(true);
    }//GEN-LAST:event_backActionPerformed

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchActionPerformed

    private void searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyReleased
        DefaultTableModel model = (DefaultTableModel) returnTable.getModel();
    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
    returnTable.setRowSorter(sorter);
    
    // Set the row filter to ignore case while searching
    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + search.getText()));
    }//GEN-LAST:event_searchKeyReleased

    private void menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuActionPerformed
        this.dispose();
        this.hide();
        menu_page frm=new menu_page();
        frm.setVisible(true);
    }//GEN-LAST:event_menuActionPerformed

    private void return_idTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_return_idTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_return_idTActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
   if (return_idT.getText().isEmpty() || return_dateT.getDate() == null || borrow_idT.getText().isEmpty() || person_idT.getText().isEmpty()) {
    JOptionPane.showMessageDialog(null, "All data is required. Please enter all necessary data.");
} else {
    try {
        int returnId = Integer.parseInt(return_idT.getText());
        String borrowId = borrow_idT.getText();
        String personId = person_idT.getText();
        Date selectedDate = return_dateT.getDate();

        // Check if borrow_id exists in the borrow table
        String checkBorrowIdSql = "SELECT * FROM borrow WHERE borrow_id = ?";
        PreparedStatement checkBorrowIdPs = con.prepareStatement(checkBorrowIdSql);
        checkBorrowIdPs.setString(1, borrowId);
        ResultSet resultSet = checkBorrowIdPs.executeQuery();

        if (!resultSet.next()) {
            JOptionPane.showMessageDialog(null, "Borrow ID does not exist.");
            return;
        }

        String returnSql = "INSERT INTO return_table(return_id, borrow_id_rfk,  person_id_rfk, return_date) VALUES (?, ?, ?, ?)";
        PreparedStatement returnPs = con.prepareStatement(returnSql);
        returnPs.setInt(1, returnId);
        returnPs.setString(2, borrowId);
        returnPs.setString(3, personId);
        returnPs.setDate(4, new java.sql.Date(selectedDate.getTime()));

        // Execute the insert statement
        returnPs.executeUpdate();

        // Clear input fields after successful insertion
        return_idT.setText("");
        borrow_idT.setText("");
        person_idT.setText("");
        return_dateT.setDate(null);

        JOptionPane.showMessageDialog(null, "Saved Successfully");
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        e.printStackTrace();}} // Print the stack trace for debugging
    }//GEN-LAST:event_addActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
     if (person_idT.getText().isEmpty() || borrow_idT.getText().isEmpty() || return_dateT.getDate() == null) {
        JOptionPane.showMessageDialog(null, "All fields are required. Please fill them.");
        return;
    }

    // Get the values from the fields
    String returnId = return_idT.getText();
    String personId = person_idT.getText();
    String borrowId = borrow_idT.getText();
    Date returnDate = return_dateT.getDate();

    try {
        // Prepare the update statement
        String updateQuery = "UPDATE return_table SET borrow_id_rfk=?, person_id_rfk=?, return_date=? WHERE return_id=?";
        ps = con.prepareStatement(updateQuery);
        ps.setString(1, borrowId);
        ps.setString(2, personId);
        ps.setDate(3, new java.sql.Date(returnDate.getTime()));
        ps.setString(4, returnId); // Make sure to set parameter 4 as returnId

        // Execute the update statement
        int rowsAffected = ps.executeUpdate();

        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "Record updated successfully.");

            // Reload the table data
            reload();
        } else {
            JOptionPane.showMessageDialog(null, "Failed to update record.");
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
    }      }//GEN-LAST:event_updateActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
     DefaultTableModel model = (DefaultTableModel) returnTable.getModel();

    try {
        int selectedRowIndex = returnTable.getSelectedRow();
        int idToDelete = Integer.parseInt(model.getValueAt(selectedRowIndex, 0).toString()); // Assuming the ID is in the second column

        // Delete the record from the MySQL database
        deleteRecord(idToDelete);

        // Remove the selected row from the table model
        model.removeRow(selectedRowIndex);
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(null, ex);
    }
    }//GEN-LAST:event_deleteActionPerformed

    private void person_idTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_person_idTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_person_idTActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        reload();
        generatedId();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void returnTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_returnTableMouseClicked
    DefaultTableModel model = (DefaultTableModel) returnTable.getModel();
int selectedRowIndex = returnTable.getSelectedRow();

// Ensure a row is selected and data is not null before accessing it
if (selectedRowIndex >= 0 && selectedRowIndex < model.getRowCount()) {
    // Retrieve data from the selected row
    String return_id= Objects.toString(model.getValueAt(selectedRowIndex, 0), ""); // Default to empty string if null
    String borrow_id = Objects.toString(model.getValueAt(selectedRowIndex, 1), "");
    String person_id = Objects.toString(model.getValueAt(selectedRowIndex, 3), "");
    String return_date = Objects.toString(model.getValueAt(selectedRowIndex, 4), "");

    // Populate the form fields with the selected row's data
    return_idT.setText(return_id);
    borrow_idT.setText(borrow_id);
   person_idT.setText(person_id);
    return_dateT.setDate(parseDate(return_date)); // Using parseDate method to set the date
    // You may need to handle picture display separately
}
    }//GEN-LAST:event_returnTableMouseClicked

    private void borrow_idTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrow_idTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_borrow_idTActionPerformed

    private void tableBorrowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableBorrowMouseClicked
      
    }//GEN-LAST:event_tableBorrowMouseClicked

    private void search2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_search2ActionPerformed

    private void search2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search2KeyReleased
        DefaultTableModel model = (DefaultTableModel) tableBorrow.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        tableBorrow.setRowSorter(sorter);

        // Set the row filter to ignore case while searching
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + search2.getText()));
    }//GEN-LAST:event_search2KeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(return_book.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(return_book.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(return_book.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(return_book.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new return_book().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JButton back;
    private javax.swing.JTextField borrow_idT;
    private javax.swing.JButton delete;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JButton menu;
    private javax.swing.JTextField person_idT;
    private javax.swing.JTable returnTable;
    private com.toedter.calendar.JDateChooser return_dateT;
    private javax.swing.JTextField return_idT;
    private javax.swing.JTextField search;
    private javax.swing.JTextField search2;
    private javax.swing.JTable tableBorrow;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables

    private Date parseDate(String dateString) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Adjust the format based on your date format
    try {
        return dateFormat.parse(dateString); // Parse the date string into a Date object
    } catch (ParseException ex) {
        ex.printStackTrace(); // Handle the parsing exception
        return null; // Return null if parsing fails
    }
}
}
