import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import net.proteanit.sql.DbUtils;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author DELL
 */
public class registration_page extends javax.swing.JFrame {
    
    Connection con=null;
    PreparedStatement ps=null;
    ResultSet rs=null;
    
    /**
     * Creates new form registration_page
     * @throws java.sql.SQLException
     */
    public registration_page() throws SQLException {
        initComponents();
        tableRegister();
        generatedId();
        
               setDefaultCloseOperation(registration_page.EXIT_ON_CLOSE);


    }
 
           private void tableRegister(){        
        try{
              Class.forName("com.mysql.cj.jdbc.Driver");
         con=DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdb?zeroDateTimeBehavior=CONVERT_TO_NULL","root","1234");
        
            String sqlQuery = "SELECT person_id, name, email, phone_number FROM person";
            
            ps = con.prepareStatement(sqlQuery);
            rs = ps.executeQuery();
            registerTable.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(registration_page.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }
    
  private void generatedId(){
    
    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
         con=DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdb?zeroDateTimeBehavior=CONVERT_TO_NULL","root","1234");
        
      String sqlQuery = "SELECT MAX(person_id) AS last_id FROM person";
        ps = con.prepareStatement(sqlQuery);
        rs = ps.executeQuery();

        // Check if there is a result
        if (rs.next()) {
            // Get the last ID from the result set
            int lastId = rs.getInt("last_id");
            
            // Increment the last ID by 1 to generate the new ID
            int newId = lastId + 1;
            
            // Set the new ID to the borrow_id field
            person_idT.setText(Integer.toString(newId));
        }
        
    }catch(SQLException e){
                JOptionPane.showMessageDialog(null, e);
    }   catch (ClassNotFoundException ex) {
            Logger.getLogger(registration_page.class.getName()).log(Level.SEVERE, null, ex);
        }}
     
     private void reload() {
      try {
            Class.forName("com.mysql.cj.jdbc.Driver");
         con=DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdb?zeroDateTimeBehavior=CONVERT_TO_NULL","root","1234");
          
        // Fetch the data from the database
            String sqlQuery = "SELECT person_id, name, email, phone_number FROM person";
        ps = con.prepareStatement(sqlQuery);
        rs = ps.executeQuery();
        
        // Set the data into the table model
        registerTable.setModel(DbUtils.resultSetToTableModel(rs));

    } catch (SQLException ex) {
          // Handle SQL exception
    }   catch (ClassNotFoundException ex) {
            Logger.getLogger(registration_page.class.getName()).log(Level.SEVERE, null, ex);
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
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        back1 = new javax.swing.JButton();
        search = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        registerButton = new javax.swing.JButton();
        phoneT = new javax.swing.JTextField();
        nameT = new javax.swing.JTextField();
        emailT = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        person_idT = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        registerTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("League Spartan Black", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("PERSONAL INFORMATION");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, -1, -1));

        jLabel10.setFont(new java.awt.Font("Montserrat ExtraBold", 1, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Name");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, 50, 20));

        jLabel12.setFont(new java.awt.Font("Montserrat ExtraBold", 1, 13)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Phone Number");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 300, 110, 20));

        jLabel11.setFont(new java.awt.Font("Montserrat ExtraBold", 1, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Email");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 270, 60, 20));

        back1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/back-button.png"))); // NOI18N
        back1.setBorder(null);
        back1.setBorderPainted(false);
        back1.setContentAreaFilled(false);
        back1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back1ActionPerformed(evt);
            }
        });
        getContentPane().add(back1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, -1, -1));

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
        getContentPane().add(search, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 90, 200, 23));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/search.png"))); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 90, -1, -1));

        registerButton.setFont(new java.awt.Font("Montserrat", 1, 11)); // NOI18N
        registerButton.setText("REGISTER");
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });
        getContentPane().add(registerButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 340, 110, -1));

        phoneT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneTActionPerformed(evt);
            }
        });
        getContentPane().add(phoneT, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 300, 140, -1));

        nameT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameTActionPerformed(evt);
            }
        });
        getContentPane().add(nameT, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 240, 140, -1));
        getContentPane().add(emailT, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 270, 140, -1));

        jLabel13.setFont(new java.awt.Font("Montserrat ExtraBold", 1, 13)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Person ID");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, 70, 20));

        person_idT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                person_idTActionPerformed(evt);
            }
        });
        getContentPane().add(person_idT, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 210, 80, -1));

        registerTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(registerTable);
        if (registerTable.getColumnModel().getColumnCount() > 0) {
            registerTable.getColumnModel().getColumn(4).setHeaderValue("Title 5");
        }

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 120, 430, 320));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reload.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 30, 30, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fade_background.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void back1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back1ActionPerformed
        this.dispose();
        menu_page frm=new menu_page();
        frm.setVisible(true);
    }//GEN-LAST:event_back1ActionPerformed

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchActionPerformed

    private void searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyReleased
        DefaultTableModel obj=(DefaultTableModel) registerTable.getModel();
        TableRowSorter<DefaultTableModel> obj1=new TableRowSorter<>(obj);
        registerTable.setRowSorter(obj1);
        obj1.setRowFilter(RowFilter.regexFilter(search.getText()));
    }//GEN-LAST:event_searchKeyReleased

    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed
         try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdb?zeroDateTimeBehavior=CONVERT_TO_NULL","root","1234");
        
        String idText = person_idT.getText();
        String name = nameT.getText();
        String email = emailT.getText();
        String phone = phoneT.getText();
        
            String sql = "INSERT INTO person (person_id, name, email, phone_number) VALUES (?, ?, ?, ?)";
        ps = con.prepareStatement(sql);
        
        ps.setString(1,idText);
        ps.setString(2, name);
        ps.setString(3, email);
        ps.setString(4, phone);
        ps.executeUpdate();

        JOptionPane.showMessageDialog(null, "Registration Successful");

        // Clear the text fields after successful registration
        nameT.setText("");
        emailT.setText("");
        phoneT.setText("");
        
    }catch (HeadlessException | ClassNotFoundException | NumberFormatException | SQLException e) {
        // Handle other exceptions
        JOptionPane.showMessageDialog(null, e);
    }                    
    }//GEN-LAST:event_registerButtonActionPerformed

    private void phoneTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneTActionPerformed

    private void nameTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameTActionPerformed
        
        
    }//GEN-LAST:event_nameTActionPerformed

    private void person_idTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_person_idTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_person_idTActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        reload();
        generatedId();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(registration_page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(registration_page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(registration_page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(registration_page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new registration_page().setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(registration_page.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back1;
    private javax.swing.JTextField emailT;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField nameT;
    private javax.swing.JTextField person_idT;
    private javax.swing.JTextField phoneT;
    private javax.swing.JButton registerButton;
    private javax.swing.JTable registerTable;
    private javax.swing.JTextField search;
    // End of variables declaration//GEN-END:variables
}
