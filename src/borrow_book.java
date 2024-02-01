import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
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
public class borrow_book extends javax.swing.JFrame {

    Connection con=null;
    PreparedStatement ps=null;
    ResultSet rs=null;
    
    
    /**
     * Creates new form return_book
     */
    public borrow_book() {
        initComponents();
        tableBorrow();
        tableBrowse();
        generatedId();
        convertPersonId();
        statusT.setBackground(new java.awt.Color(0,0,0,1));
               setDefaultCloseOperation(borrow_book.EXIT_ON_CLOSE);

    }
    
    private int convertPersonId() {
    int personId = 0;
    try {
        String sqlQuery = "SELECT person_id FROM person ORDER BY person_id DESC LIMIT 1";
        ps = con.prepareStatement(sqlQuery);
        rs = ps.executeQuery();
        if (rs.next()) {
            personId = rs.getInt("person_id");
        }
    } catch (SQLException e) {
    }
    return personId;
    }
    
    private void generatedId(){
    
    try{
         Class.forName("com.mysql.cj.jdbc.Driver");
         con=DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdb?zeroDateTimeBehavior=CONVERT_TO_NULL","root","1234");
      String sqlQuery = "SELECT MAX(borrow_id) AS last_id FROM borrow";
        ps = con.prepareStatement(sqlQuery);
        rs = ps.executeQuery();

        // Check if there is a result
        if (rs.next()) {
            // Get the last ID from the result set
            int lastId = rs.getInt("last_id");
            
            // Increment the last ID by 1 to generate the new ID
            int newId = lastId + 1;
            
            // Set the new ID to the borrow_id field
            borrow_idT.setText(Integer.toString(newId));
        }
        
    }catch(ClassNotFoundException | SQLException e){
                JOptionPane.showMessageDialog(null, e);

    }
        
    }
    
    private void tableBorrow(){        
        try{
        
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdb?zeroDateTimeBehavior=CONVERT_TO_NULL","root","1234");
            
            String sqlQuery = "SELECT borrow.borrow_id, borrow.book_id_brfk AS book_id, person.person_id, person.name, borrow.borrow_date, borrow.due_date, book.status FROM borrow JOIN book ON borrow.book_id_brfk = book.book_id JOIN person ON borrow.person_id_brfk = person.person_id;";
            
            ps = con.prepareStatement(sqlQuery);
            rs = ps.executeQuery();
            borrowTable.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    
    
    }
    
     private void tableBrowse(){        
        try{
        
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdb?zeroDateTimeBehavior=CONVERT_TO_NULL","root","1234");
            
            String sqlQuery = "SELECT book.book_id, book.title, author.author_name, book.description FROM book JOIN author ON book.author_id_abfk = author.author_id";
            
            ps = con.prepareStatement(sqlQuery);
            rs = ps.executeQuery();
            browseTable.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
     private void deleteRecord(int idToDelete) {
    try {
        // Prepare the SQL DELETE statement
        String deleteQuery = "DELETE FROM borrow WHERE borrow_id = ?";
        ps = con.prepareStatement(deleteQuery);
        ps.setInt(1, idToDelete);

        // Execute the DELETE statement
        ps.executeUpdate();

        JOptionPane.showMessageDialog(null, "Record with ID " + idToDelete + " has been deleted successfully.");
    } catch (SQLException e) {
        } 
}
private void reload1() {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Establish a new connection
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdb?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "1234");

        // Fetch the data from the database
        String sqlQuery = "SELECT borrow.borrow_id, borrow.book_id_brfk AS book_id, person.person_id, person.name, borrow.borrow_date, borrow.due_date, book.status FROM borrow JOIN book ON borrow.book_id_brfk = book.book_id JOIN person ON borrow.person_id_brfk = person.person_id;";

        // Close the previous PreparedStatement and ResultSet if they exist
        closePrevious();

        // Create a new PreparedStatement
        ps = con.prepareStatement(sqlQuery);
        rs = ps.executeQuery();

        // Set the data into the table model
        borrowTable.setModel(DbUtils.resultSetToTableModel(rs));
    } catch (SQLException | ClassNotFoundException ex) {
        // Handle exceptions
        ex.printStackTrace();
    }
}

private void reload2() {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Establish a new connection
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdb?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "1234");

        // Fetch the data from the database
        String sqlQuery = "SELECT book.book_id, book.title, author.author_name, book.description FROM book JOIN author ON book.author_id_abfk = author.author_id";

        // Close the previous PreparedStatement and ResultSet if they exist
        closePrevious();

        // Create a new PreparedStatement
        ps = con.prepareStatement(sqlQuery);
        rs = ps.executeQuery();

        // Set the data into the table model
        browseTable.setModel(DbUtils.resultSetToTableModel(rs));
    } catch (SQLException | ClassNotFoundException ex) {
        // Handle exceptions
        ex.printStackTrace();
    }
}

// Helper method to close the previous PreparedStatement and ResultSet
private void closePrevious() {
    try {
        if (ps != null) {
            ps.close();
        }
        if (rs != null) {
            rs.close();
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
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

        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        back = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        book_idT = new javax.swing.JTextField();
        statusT = new javax.swing.JTextField();
        borrow_dateT = new com.toedter.calendar.JDateChooser();
        update = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        search2 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        person_idT = new javax.swing.JTextField();
        checker = new javax.swing.JButton();
        add = new javax.swing.JButton();
        borrow_idT = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        search1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        menu = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        browseTable = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        borrowTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jLabel2.setFont(new java.awt.Font("League Spartan Black", 0, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("BORROW DETAILS");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, -1, -1));

        jLabel11.setFont(new java.awt.Font("Montserrat ExtraBold", 1, 15)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Book ID");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, 100, 20));

        jLabel12.setFont(new java.awt.Font("Montserrat ExtraBold", 1, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Status");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 280, 80, 20));

        book_idT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                book_idTActionPerformed(evt);
            }
        });
        getContentPane().add(book_idT, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 130, -1));

        statusT.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        statusT.setForeground(new java.awt.Color(255, 255, 255));
        statusT.setText(" ");
        statusT.setBorder(null);
        statusT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusTActionPerformed(evt);
            }
        });
        getContentPane().add(statusT, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 280, 130, 20));

        borrow_dateT.setBackground(new java.awt.Color(255, 255, 255));
        borrow_dateT.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        getContentPane().add(borrow_dateT, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 320, 130, -1));

        update.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        update.setText("UPDATE");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });
        getContentPane().add(update, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 380, 110, 30));

        delete.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        delete.setText("DELETE");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });
        getContentPane().add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 380, 110, 30));

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
        getContentPane().add(search2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 270, 200, 23));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/search.png"))); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 270, -1, -1));

        jLabel13.setFont(new java.awt.Font("Montserrat ExtraBold", 1, 15)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Person ID");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, 100, 20));

        person_idT.setText(" ");
        person_idT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                person_idTActionPerformed(evt);
            }
        });
        getContentPane().add(person_idT, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 200, 130, -1));

        checker.setFont(new java.awt.Font("Segoe UI", 1, 10)); // NOI18N
        checker.setText("CHECK");
        checker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkerActionPerformed(evt);
            }
        });
        getContentPane().add(checker, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 240, 70, 20));

        add.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        add.setText("BORROW");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        getContentPane().add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 380, 110, 30));

        borrow_idT.setText(" ");
        borrow_idT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrow_idTActionPerformed(evt);
            }
        });
        getContentPane().add(borrow_idT, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 160, 70, -1));

        jLabel15.setFont(new java.awt.Font("Montserrat ExtraBold", 1, 15)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Borrow ID");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 100, 20));

        search1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search1ActionPerformed(evt);
            }
        });
        search1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                search1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                search1KeyTyped(evt);
            }
        });
        getContentPane().add(search1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 80, 200, 23));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/search.png"))); // NOI18N
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 80, -1, -1));

        jLabel14.setFont(new java.awt.Font("Montserrat ExtraBold", 1, 15)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Borrow Date");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 320, 100, 20));

        menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/menu icon.png"))); // NOI18N
        menu.setBorderPainted(false);
        menu.setContentAreaFilled(false);
        menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuActionPerformed(evt);
            }
        });
        getContentPane().add(menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 40, 30, -1));

        browseTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(browseTable);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 110, 350, 150));

        borrowTable.setModel(new javax.swing.table.DefaultTableModel(
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
        borrowTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                borrowTableMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(borrowTable);

        getContentPane().add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 300, 350, 170));

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

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fade_background.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        this.dispose();
        this.hide();
        action_window frm=new action_window();
        frm.setVisible(true);
    }//GEN-LAST:event_backActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
       if (person_idT.getText().isEmpty() || borrow_idT.getText().isEmpty() || book_idT.getText().isEmpty() || statusT.getText().isEmpty() || borrow_dateT.getDate() == null) {
    JOptionPane.showMessageDialog(null, "All data is required. Please enter all required information.");

    // Clear the text fields and date chooser
    person_idT.setText("");
    book_idT.setText("");
    statusT.setText("");
    borrow_dateT.setDate(null);
} else {
    try {
        // Load the JDBC driver and establish a connection
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdb?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "1234");

        String id = book_idT.getText();
        Date selectedDate = borrow_dateT.getDate();
        int personId = convertPersonId();

        // Prepare the SQL statement for inserting into the borrow table
        String borrowSql = "INSERT INTO borrow(book_id_brfk, person_id_brfk, borrow_date) VALUES (?, ?, ?)";
        PreparedStatement borrowPs = con.prepareStatement(borrowSql);

        // Set parameters for the borrow statement
        borrowPs.setString(1, id);
        borrowPs.setInt(2, personId);
        borrowPs.setDate(3, new java.sql.Date(selectedDate.getTime()));

        // Execute the borrow statement
        borrowPs.executeUpdate();

        // Update the status of the book to 'BORROWED'
        String updateBorrowStatusSql = "UPDATE book SET status = 'BORROWED' WHERE book_id = ?";
        PreparedStatement updateBorrowStatusPs = con.prepareStatement(updateBorrowStatusSql);
        updateBorrowStatusPs.setString(1, id);
        updateBorrowStatusPs.executeUpdate();

        // Clear the text fields and date chooser after successful insertion
        person_idT.setText("");
        book_idT.setText("");
        statusT.setText("");
        borrow_dateT.setDate(null);

        JOptionPane.showMessageDialog(null, "Saved Successfully");
    } catch (HeadlessException | SQLException e) {
        JOptionPane.showMessageDialog(null, e);
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(borrow_book.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    }//GEN-LAST:event_addActionPerformed
 
  
    private void book_idTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_book_idTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_book_idTActionPerformed

    private void statusTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statusTActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
  if (borrow_idT.getText().isEmpty() || person_idT.getText().isEmpty() || book_idT.getText().isEmpty() || borrow_dateT.getDate() == null) {
        JOptionPane.showMessageDialog(null, "All fields are required. Please fill them.");
        return;
    }

    // Get the values from the fields
    int borrowId = Integer.parseInt(borrow_idT.getText());
    int personId = Integer.parseInt(person_idT.getText());
    String bookId = book_idT.getText();
    Date borrowDate = borrow_dateT.getDate();

    try {
        // Prepare the update statement
        String updateQuery = "UPDATE borrow SET book_id_brfk=?, person_id_brfk=?, borrow_date=? WHERE borrow_id=?";
        ps = con.prepareStatement(updateQuery);
        ps.setString(1, bookId);
        ps.setInt(2, personId);
        ps.setDate(3, new java.sql.Date(borrowDate.getTime()));
        ps.setInt(4, borrowId);

        // Execute the update statement
        int rowsAffected = ps.executeUpdate();

        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "Record updated successfully.");

            // Reload the table data
            reload1();
        } else {
            JOptionPane.showMessageDialog(null, "Failed to update record.");
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
    }    }//GEN-LAST:event_updateActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
       DefaultTableModel model = (DefaultTableModel) borrowTable.getModel();

try {
    int selectedRowIndex = borrowTable.getSelectedRow();
    int idToDelete = Integer.parseInt(model.getValueAt(selectedRowIndex, 0).toString()); // Assuming the ID is in the first column

    // Delete the record from the MySQL database
    deleteRecord(idToDelete);

    // Remove the selected row from the table model
    model.removeRow(selectedRowIndex);
} catch (NumberFormatException ex) {
    JOptionPane.showMessageDialog(null, ex);
}

    }//GEN-LAST:event_deleteActionPerformed

    private void search2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_search2ActionPerformed

    private void search2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search2KeyReleased
          DefaultTableModel model = (DefaultTableModel) borrowTable.getModel();
    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
    borrowTable.setRowSorter(sorter);
    
    // Set the row filter to ignore case while searching
    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + search2.getText()));
    }//GEN-LAST:event_search2KeyReleased

    private void person_idTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_person_idTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_person_idTActionPerformed

    private void checkerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkerActionPerformed
     
        
        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdb?zeroDateTimeBehavior=CONVERT_TO_NULL","root","1234");
        
      String bookIdText = book_idT.getText();

        if (!bookIdText.isEmpty()) {
            int id = Integer.parseInt(bookIdText);

            String sqlQuery = "SELECT CASE WHEN status = 'AVAILABLE' THEN 'AVAILABLE' WHEN status = 'BORROWED' THEN 'BORROWED' END AS status FROM book WHERE book_id = ?";
            ps = con.prepareStatement(sqlQuery);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                String status = rs.getString("status");
                statusT.setText(status);
            } else {
                statusT.setText("Book not found"); 
            }
        } else {
            statusT.setText("Please enter a book ID");
        }
        
    } catch (NumberFormatException e) {
        statusT.setText("Invalid book ID format");
    } catch (ClassNotFoundException | SQLException e) {
        // Handle other exceptions
        JOptionPane.showMessageDialog(null, e);
    }//GEN-LAST:event_checkerActionPerformed
    }
    private void borrow_idTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrow_idTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_borrow_idTActionPerformed

    private void search1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_search1ActionPerformed

    private void search1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search1KeyReleased
              DefaultTableModel model = (DefaultTableModel) browseTable.getModel();
    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
    browseTable.setRowSorter(sorter);
    
    // Set the row filter to ignore case while searching
    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + search1.getText()));
    }//GEN-LAST:event_search1KeyReleased

    private void search1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_search1KeyTyped

    private void menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuActionPerformed
        this.dispose();
        this.hide();
        menu_page frm=new menu_page();
        frm.setVisible(true);
    }//GEN-LAST:event_menuActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        reload1();
        reload2();
        generatedId();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void borrowTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_borrowTableMouseClicked
 DefaultTableModel model = (DefaultTableModel) borrowTable.getModel();
    int selectedRowIndex = borrowTable.getSelectedRow();

    // Retrieve data from the selected row
    String borrowId = model.getValueAt(selectedRowIndex, 0).toString();
    String bookId = model.getValueAt(selectedRowIndex, 1).toString();
    String personId = model.getValueAt(selectedRowIndex, 2).toString();
    String borrowDate = model.getValueAt(selectedRowIndex, 4).toString();

    // Set the retrieved data into the respective text fields
    borrow_idT.setText(borrowId);
    book_idT.setText(bookId);
    person_idT.setText(personId);

    try {
        // Convert the string representation of the borrow date to a Date object
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(borrowDate);
        borrow_dateT.setDate(date);
    } catch (ParseException ex) {
        ex.printStackTrace();
    }

    // Retrieve the book status from the table
    String status = model.getValueAt(selectedRowIndex, 6).toString();
    statusT.setText(status);
    }//GEN-LAST:event_borrowTableMouseClicked

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
            new borrow_book().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JButton back;
    private javax.swing.JTextField book_idT;
    private javax.swing.JTable borrowTable;
    private com.toedter.calendar.JDateChooser borrow_dateT;
    private javax.swing.JTextField borrow_idT;
    private javax.swing.JTable browseTable;
    private javax.swing.JButton checker;
    private javax.swing.JButton delete;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton menu;
    private javax.swing.JTextField person_idT;
    private javax.swing.JTextField search1;
    private javax.swing.JTextField search2;
    private javax.swing.JTextField statusT;
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
