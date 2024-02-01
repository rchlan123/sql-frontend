import java.io.File;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import net.proteanit.sql.DbUtils;


public final class DonateBook extends javax.swing.JFrame {
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    private String fileName;

    public DonateBook(){
    initComponents();
    tableDonate();
    loadAuthors();
    loadGenres();
    generatedId();
    convertPersonId();
    AutoCompleteDecorator.decorate(authorT);
    
      donateTable.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            donateTableMouseClicked(evt);
        }
    });
       setDefaultCloseOperation(DonateBook.EXIT_ON_CLOSE);

}

    
private void tableDonate(){        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdb?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "1234");

            String sqlQuery = "SELECT book.book_id, person.name, book.title, author.author_name, book.isbn, book.description, book.picture, genre.genre_name FROM book JOIN author ON book.author_id_abfk = author.author_id JOIN genre ON book.genre_id_gnfk = genre.genre_id JOIN person ON book.person_id_ppfk = person.person_id ;";
            ps = con.prepareStatement(sqlQuery);
            rs = ps.executeQuery();

            // Set the table model
            donateTable.setModel(DbUtils.resultSetToTableModel(rs));

            // Set custom renderer for the "picture" column
        } catch (SQLException | ClassNotFoundException e) {
            // Handle SQLException
        }
        // Handle ClassNotFoundException

    }
    
    
 private int convertPersonId() {
    int personId = 0;
    try {
        String sqlQuery = "SELECT person_id FROM person WHERE name = ?";
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
        try {
            String sqlQuery = "SELECT MAX(book_id) AS last_id FROM book";
            ps = con.prepareStatement(sqlQuery);
            rs = ps.executeQuery();

            if (rs.next()) {
                int lastId = rs.getInt("last_id");
                int newId = lastId + 1;
                book_idT.setText(Integer.toString(newId));
            }
        } catch (SQLException e) {
        } 
    }
    
    private void loadAuthors() {
  try {
            String sqlQuery = "SELECT author_name FROM author";
            ps = con.prepareStatement(sqlQuery);
            rs = ps.executeQuery();
            authorT.removeAllItems();
            while (rs.next()) {
                authorT.addItem(rs.getString("author_name"));
            }
        } catch (SQLException e) {
        } 
    }
private void loadGenres() {
      try {
            String sqlQuery = "SELECT genre_name FROM genre";
            ps = con.prepareStatement(sqlQuery);
            rs = ps.executeQuery();
            genreT.removeAllItems();
            while (rs.next()) {
                genreT.addItem(rs.getString("genre_name"));
            }
        } catch (SQLException e) {
        } 
}

 private void deleteRecord(int idToDelete) {
   try {
        // Prepare the SQL DELETE statement for return_table
        String deleteQuery = "DELETE FROM book WHERE book_id = ?";
        ps = con.prepareStatement(deleteQuery);
        ps.setInt(1, idToDelete);

        // Execute the DELETE statement
        ps.executeUpdate();

        // Optionally, you can also update the book status or perform other actions here

        JOptionPane.showMessageDialog(null, "Record with ID " + idToDelete + " has been deleted successfully.");
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error deleting record: " + e.getMessage());
    }
}

private void reload() {
    try {
          Class.forName("com.mysql.cj.jdbc.Driver");
         con=DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdb?zeroDateTimeBehavior=CONVERT_TO_NULL","root","1234");
        
        String sqlQuery = "SELECT book.book_id, person.name, book.title, author.author_name, book.isbn, book.description, book.picture, genre.genre_name FROM book JOIN author ON book.author_id_abfk = author.author_id JOIN genre ON book.genre_id_gnfk = genre.genre_id JOIN person ON book.person_id_ppfk = person.person_id;";
        
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdb?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "1234");
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            // Set the data into the table model
            donateTable.setModel(DbUtils.resultSetToTableModel(resultSet));
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Failed to reload data from the database.");
    }   catch (ClassNotFoundException ex) {
            Logger.getLogger(DonateBook.class.getName()).log(Level.SEVERE, null, ex);
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

        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        GenreL = new javax.swing.JLabel();
        donatorIDL = new javax.swing.JLabel();
        authorL = new javax.swing.JLabel();
        isbnL = new javax.swing.JLabel();
        descriptionL = new javax.swing.JLabel();
        BookImageL = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        delete = new javax.swing.JButton();
        update = new javax.swing.JButton();
        back = new javax.swing.JButton();
        search = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        titleTT = new javax.swing.JTextField();
        isbnT = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        descriptionT = new javax.swing.JTextArea();
        authorT = new javax.swing.JComboBox<>();
        add = new javax.swing.JButton();
        book_idT = new javax.swing.JTextField();
        genreT = new javax.swing.JComboBox<>();
        menu = new javax.swing.JButton();
        titleL = new javax.swing.JLabel();
        person_idT = new javax.swing.JTextField();
        personIDL = new javax.swing.JLabel();
        insertImage = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        donateTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();

        jLabel6.setFont(new java.awt.Font("Montserrat ExtraBold", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("PERSONAL INFORMATION");

        jLabel9.setFont(new java.awt.Font("Montserrat ExtraBold", 1, 13)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Title");

        jLabel13.setFont(new java.awt.Font("Montserrat ExtraBold", 1, 13)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Description");

        jButton3.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        jButton3.setText("DONATE");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        GenreL.setFont(new java.awt.Font("Montserrat ExtraBold", 1, 13)); // NOI18N
        GenreL.setForeground(new java.awt.Color(255, 255, 255));
        GenreL.setText("Genre");
        getContentPane().add(GenreL, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 160, 50, 20));

        donatorIDL.setFont(new java.awt.Font("Montserrat ExtraBold", 1, 13)); // NOI18N
        donatorIDL.setForeground(new java.awt.Color(255, 255, 255));
        donatorIDL.setText("Book ID");
        getContentPane().add(donatorIDL, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 70, 40));

        authorL.setFont(new java.awt.Font("Montserrat ExtraBold", 1, 13)); // NOI18N
        authorL.setForeground(new java.awt.Color(255, 255, 255));
        authorL.setText("Author");
        getContentPane().add(authorL, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, 70, -1));

        isbnL.setFont(new java.awt.Font("Montserrat ExtraBold", 1, 13)); // NOI18N
        isbnL.setForeground(new java.awt.Color(255, 255, 255));
        isbnL.setText("ISBN");
        getContentPane().add(isbnL, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 130, 50, 20));

        descriptionL.setFont(new java.awt.Font("Montserrat ExtraBold", 1, 13)); // NOI18N
        descriptionL.setForeground(new java.awt.Color(255, 255, 255));
        descriptionL.setText("Description");
        getContentPane().add(descriptionL, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 210, 130, 30));

        BookImageL.setFont(new java.awt.Font("Montserrat ExtraBold", 1, 13)); // NOI18N
        BookImageL.setForeground(new java.awt.Color(255, 255, 255));
        BookImageL.setText("Book Image");
        getContentPane().add(BookImageL, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 170, 90, 50));

        jLabel7.setFont(new java.awt.Font("League Spartan Black", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("BOOK INFORMATION");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 200, 40));

        delete.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        delete.setText("DELETE");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });
        getContentPane().add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 280, 100, -1));

        update.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        update.setText("UPDATE");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });
        getContentPane().add(update, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 280, 100, -1));

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
        getContentPane().add(search, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 280, 200, 23));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/search.png"))); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 280, -1, -1));

        titleTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                titleTTActionPerformed(evt);
            }
        });
        getContentPane().add(titleTT, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 200, 190, -1));
        getContentPane().add(isbnT, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 130, 190, -1));

        descriptionT.setColumns(20);
        descriptionT.setRows(5);
        jScrollPane2.setViewportView(descriptionT);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 220, -1, 40));

        authorT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "     ", " " }));
        authorT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                authorTActionPerformed(evt);
            }
        });
        getContentPane().add(authorT, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 230, 190, -1));

        add.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        add.setText("DONATE");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        getContentPane().add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 280, 100, -1));

        book_idT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                book_idTActionPerformed(evt);
            }
        });
        getContentPane().add(book_idT, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 140, 80, -1));

        genreT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genreTActionPerformed(evt);
            }
        });
        getContentPane().add(genreT, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 160, 120, -1));

        menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/menu icon.png"))); // NOI18N
        menu.setBorderPainted(false);
        menu.setContentAreaFilled(false);
        menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuActionPerformed(evt);
            }
        });
        getContentPane().add(menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 40, 30, -1));

        titleL.setFont(new java.awt.Font("Montserrat ExtraBold", 1, 13)); // NOI18N
        titleL.setForeground(new java.awt.Color(255, 255, 255));
        titleL.setText("Title");
        getContentPane().add(titleL, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, 50, 40));

        person_idT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                person_idTActionPerformed(evt);
            }
        });
        getContentPane().add(person_idT, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 170, 80, -1));

        personIDL.setFont(new java.awt.Font("Montserrat ExtraBold", 1, 13)); // NOI18N
        personIDL.setForeground(new java.awt.Color(255, 255, 255));
        personIDL.setText("Person ID");
        getContentPane().add(personIDL, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, 70, 20));

        insertImage.setText("Upload Image");
        insertImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertImageActionPerformed(evt);
            }
        });
        getContentPane().add(insertImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 190, 150, -1));

        donateTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "null", "Title 6", "Title 7", "Title 8"
            }
        ));
        donateTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                donateTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(donateTable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 310, 730, 160));

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

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/fade_background.png"))); // NOI18N
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
      DefaultTableModel model = (DefaultTableModel) donateTable.getModel();

    try {
        int selectedRowIndex = donateTable.getSelectedRow();
        int idToDelete = Integer.parseInt(model.getValueAt(selectedRowIndex, 0).toString());

        // Delete the record from the MySQL database
        deleteRecord(idToDelete);

        // Remove the selected row from the table model
        model.removeRow(selectedRowIndex);
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(null, ex);
    }
    }//GEN-LAST:event_deleteActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
  int bookId = Integer.parseInt(book_idT.getText());
    String personId = person_idT.getText();
    String title = titleTT.getText();
    String authorName = authorT.getSelectedItem().toString();
    String isbn = isbnT.getText();
    String genreName = genreT.getSelectedItem().toString();
    String image = fileName;
    String description = descriptionT.getText();

    // Perform the update operation in the database
    try {
        String updateQuery = "UPDATE book SET person_id_ppfk = (SELECT person_id FROM person WHERE name = ?), title = ?, author_id_abfk = (SELECT author_id FROM author WHERE author_name = ?), isbn = ?, description = ?, picture = ?, genre_id_gnfk = (SELECT genre_id FROM genre WHERE genre_name = ?) WHERE book_id = ?";
        PreparedStatement pstmt = con.prepareStatement(updateQuery);
        
        pstmt.setString(1, personId);
        pstmt.setString(2, title);
        pstmt.setString(3, authorName);
        pstmt.setString(4, isbn);
        pstmt.setString(5, description);
        pstmt.setString(6, image);
        pstmt.setString(7, genreName);
        pstmt.setInt(8, bookId);
        
        int rowsAffected = pstmt.executeUpdate();
        
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "Book updated successfully!");
            
            // Update the UI or perform any other necessary actions
        } else {
            JOptionPane.showMessageDialog(null, "Failed to update book!");
        }
        
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e);
    }
    }//GEN-LAST:event_updateActionPerformed

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
             DefaultTableModel model = (DefaultTableModel) donateTable.getModel();
    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
    donateTable.setRowSorter(sorter);
    
    // Set the row filter to ignore case while searching
    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + search.getText()));
    }//GEN-LAST:event_searchKeyReleased

    
    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
  if (fileName.isEmpty() || person_idT.getText().isEmpty() || titleTT.getText().isEmpty() || authorT.getSelectedItem() == null || isbnT.getText().isEmpty() || genreT.getSelectedItem() == null || descriptionT.getText().isEmpty()) {
    JOptionPane.showMessageDialog(null, "All data is required. Please enter all required information.");
} else {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdb?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "1234");

        // Get data from UI components
        int bookId = Integer.parseInt(book_idT.getText());
        String personId = person_idT.getText();
        String title = titleTT.getText();
        String authorName = authorT.getSelectedItem().toString();
        String isbn = isbnT.getText();
        String genreName = genreT.getSelectedItem().toString();
        String image = fileName;
        String description = descriptionT.getText();

        // Prepare the SQL statement for insertion
        String sql = "INSERT INTO book (book_Id, person_id_ppfk, title, author_id_abfk, isbn, description, picture, genre_id_gnfk) " +
                  "VALUES (?,? ,?, (SELECT author_id FROM author WHERE author_name = ?), ?, ?, ?, (SELECT genre_id FROM genre WHERE genre_name = ?))";
        
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, bookId);
            pstmt.setString(2, personId);
            pstmt.setString(3, title);
            pstmt.setString(4, authorName);
            pstmt.setString(5, isbn);
            pstmt.setString(6, description);
            pstmt.setString(7, image);
            pstmt.setString(8, genreName);

            // Execute the update
            pstmt.executeUpdate();

            // Clear input fields after successful insertion
            person_idT.setText("");
            titleTT.setText("");
            authorT.setSelectedIndex(-1); // Clear selection
            isbnT.setText("");
            genreT.setSelectedIndex(-1); // Clear selection
            insertImage.setText("Upload Image"); // Clear the image button text
            descriptionT.setText("");

            JOptionPane.showMessageDialog(null, "Saved Successfully");
        } catch (SQLException e) {
            // Handle SQL exceptions (e.g., display an error message)
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    } catch (ClassNotFoundException | SQLException e) {
        // Handle class not found exception (e.g., display an error message)
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
       // Handle SQL exceptions (e.g., display an error message)
       
}

    }//GEN-LAST:event_addActionPerformed

    
    
    private void genreTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genreTActionPerformed

    }//GEN-LAST:event_genreTActionPerformed

    private void menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuActionPerformed
    this.dispose();
        this.hide();
        menu_page frm=new menu_page();
        frm.setVisible(true);
    }//GEN-LAST:event_menuActionPerformed

    private void book_idTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_book_idTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_book_idTActionPerformed

    private void titleTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_titleTTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_titleTTActionPerformed

    private void authorTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_authorTActionPerformed
        
    }//GEN-LAST:event_authorTActionPerformed

    private void person_idTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_person_idTActionPerformed

    }//GEN-LAST:event_person_idTActionPerformed

    private void insertImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertImageActionPerformed
 JFileChooser chooser = new JFileChooser();
    int returnValue = chooser.showOpenDialog(null);

    if (returnValue == JFileChooser.APPROVE_OPTION) {
        File f = chooser.getSelectedFile();
        if (f != null) {
            // Store the file path and name
            fileName = f.getAbsolutePath(); // Use f.getAbsolutePath() to get the absolute path
            insertImage.setText(f.getName()); // Set the button text to display the file name
        } else {
            JOptionPane.showMessageDialog(null, "No file selected.");
        }
    }
    }//GEN-LAST:event_insertImageActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        reload();
        generatedId();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void donateTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_donateTableMouseClicked
    DefaultTableModel model = (DefaultTableModel) donateTable.getModel();
    int selectedRowIndex = donateTable.getSelectedRow();

    // Ensure a row is selected and data is not null before accessing it
    if (selectedRowIndex >= 0 && selectedRowIndex < model.getRowCount()) {
        // Retrieve data from the selected row
        String bookId = Objects.toString(model.getValueAt(selectedRowIndex, 0), ""); // Default to empty string if null
        String personName = Objects.toString(model.getValueAt(selectedRowIndex, 1), "");
        String title = Objects.toString(model.getValueAt(selectedRowIndex, 2), "");
        String authorName = Objects.toString(model.getValueAt(selectedRowIndex, 3), "");
        String isbn = Objects.toString(model.getValueAt(selectedRowIndex, 4), "");
        String description = Objects.toString(model.getValueAt(selectedRowIndex, 5), "");
        String picture = Objects.toString(model.getValueAt(selectedRowIndex, 6), "");
        String genreName = Objects.toString(model.getValueAt(selectedRowIndex, 7), "");

        // Populate the form fields with the selected row's data
        book_idT.setText(bookId);
        person_idT.setText(personName);
        titleTT.setText(title);
        authorT.setSelectedItem(authorName);
        isbnT.setText(isbn);
        descriptionT.setText(description);
        // You may need to handle picture display separately
        // Assuming 'insertImage' is a button to upload image, you may set the button text or handle image display based on 'picture' value
        insertImage.setText(picture); // Set the button text to display the picture name
        genreT.setSelectedItem(genreName);
    }

    }//GEN-LAST:event_donateTableMouseClicked


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
            new DonateBook().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BookImageL;
    private javax.swing.JLabel GenreL;
    private javax.swing.JButton add;
    private javax.swing.JLabel authorL;
    private javax.swing.JComboBox<String> authorT;
    private javax.swing.JButton back;
    private javax.swing.JTextField book_idT;
    private javax.swing.JButton delete;
    private javax.swing.JLabel descriptionL;
    private javax.swing.JTextArea descriptionT;
    private javax.swing.JTable donateTable;
    private javax.swing.JLabel donatorIDL;
    private javax.swing.JComboBox<String> genreT;
    private javax.swing.JButton insertImage;
    private javax.swing.JLabel isbnL;
    private javax.swing.JTextField isbnT;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton menu;
    private javax.swing.JLabel personIDL;
    private javax.swing.JTextField person_idT;
    private javax.swing.JTextField search;
    private javax.swing.JLabel titleL;
    private javax.swing.JTextField titleTT;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables

}