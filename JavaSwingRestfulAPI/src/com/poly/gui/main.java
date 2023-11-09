/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.poly.gui;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.poly.bean.Student;
import com.poly.bean.StudentMap;
import com.poly.dao.StudentDAO;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tungt
 */
public class main extends javax.swing.JFrame {

    StudentDAO dao = new StudentDAO();
    StudentMap items;
    String key = null;
    
    public main() {
        initComponents();
        load();
    }

    
    void FillTable(){
        items.keySet().toArray();
        DefaultTableModel model = (DefaultTableModel) tblStudents.getModel();
        model.setRowCount(0);
        items.forEach((key, s) ->
        {
            model.addRow(s.getArray());
        });
    }   
    
    void setForm(Student s){
        txtEmail.setText(s.getEmail());
        txtFullname.setText(s.getFullname());
        txtMarks.setText(String.valueOf(s.getMarks()));
        rdoNam.setSelected(s.isGender());
        rdoNu.setSelected(!s.isGender());
        cboCountry.setSelectedItem(s.getCountry());
    }
    
    Student getForm(){
        Student s = new Student();
        s.setEmail(txtEmail.getText());
        s.setFullname(txtFullname.getText());
        s.setMarks(Double.parseDouble(txtMarks.getText()));
        s.setGender(rdoNam.isSelected());
        s.setCountry(cboCountry.getSelectedItem().toString());
        return s;
    }
    
    void load(){
        try {
            this.items = dao.findAll();
            this.FillTable();
        } catch (Exception e) {
//            JOptionPane.showMessageDialog(this, "Lỗi tải dữ liệu Firebase (url)");
        }
    }
    
    void edit(String key){
        try {
            Student s = dao.findByKey(key);
            this.key = key;
            this.setForm(s);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi tải dữ liệu (Index)");
        }
    }
    
    void create(){
        try {
            Student s = this.getForm();
            this.key = dao.create(s);
            this.items.put(this.key, s);
            JOptionPane.showMessageDialog(this, "Thêm sinh viên thành công");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Thêm sinh viên thất bại");
        }
        
    }
    
    void update(){
        try {
            Student s = this.getForm();
            dao.update(this.key, s);
            this.items.put(this.key, s);
            JOptionPane.showMessageDialog(this, "Cập nhật thông tin thành công");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Cập nhật thông tin thất bại");
        }
    }
    
    void delete(){
        try {
            dao.delete(this.key);
            this.items.remove(this.key);
            this.reset();
            
            JOptionPane.showMessageDialog(this, "Xóa sinh viên thành công");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Xóa sinh viên thất bại");
        }
    }
    
    void reset(){
        txtEmail.setText("");
        txtFullname.setText("");
        txtMarks.setText("");
        cboCountry.setSelectedIndex(0);
        rdoNam.setSelected(true);
//        tabs.setSelectedIndex(0);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        tabs = new javax.swing.JTabbedPane();
        tab1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblStudents = new javax.swing.JTable();
        tab2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtFullname = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtMarks = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cboCountry = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        btnCreat = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Restful API");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        tblStudents.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fullname", "Email", "Gender", "Marks", "Country"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblStudents.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblStudentsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblStudents);

        javax.swing.GroupLayout tab1Layout = new javax.swing.GroupLayout(tab1);
        tab1.setLayout(tab1Layout);
        tab1Layout.setHorizontalGroup(
            tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 695, Short.MAX_VALUE)
        );
        tab1Layout.setVerticalGroup(
            tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
        );

        tabs.addTab("Danh Sách", tab1);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Fullname");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Email Address");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Marks");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Country");

        cboCountry.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "vn", "us", " " }));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Gender");

        buttonGroup1.add(rdoNam);
        rdoNam.setText("Nam");

        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");

        btnCreat.setText("Thêm");
        btnCreat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreatActionPerformed(evt);
            }
        });

        btnUpdate.setText("Sửa");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("Xóa");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnReset.setText("Làm Mới");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tab2Layout = new javax.swing.GroupLayout(tab2);
        tab2.setLayout(tab2Layout);
        tab2Layout.setHorizontalGroup(
            tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tab2Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(tab2Layout.createSequentialGroup()
                        .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFullname, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMarks, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboCountry, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(38, 38, 38))
                    .addGroup(tab2Layout.createSequentialGroup()
                        .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCreat, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tab2Layout.createSequentialGroup()
                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(rdoNu, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(258, 258, 258))))
        );
        tab2Layout.setVerticalGroup(
            tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(tab2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtFullname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tab2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMarks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoNam)
                    .addComponent(rdoNu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                .addGroup(tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreat)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete)
                    .addComponent(btnReset))
                .addGap(66, 66, 66))
        );

        tabs.addTab("Chỉnh Sửa", tab2);

        jLabel1.setFont(new java.awt.Font("Castellar", 0, 24)); // NOI18N
        jLabel1.setText("Restful API - URL");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabs)
            .addGroup(layout.createSequentialGroup()
                .addGap(222, 222, 222)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 16, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tblStudentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblStudentsMouseClicked
        if(evt.getClickCount() == 2){
            int row = tblStudents.getSelectedRow();
            String key = (String) items.keySet().toArray()[row];
            this.edit(key);
            tabs.setSelectedIndex(1);
        }
    }//GEN-LAST:event_tblStudentsMouseClicked

    private void btnCreatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreatActionPerformed
        this.create();
        this.reset();
        this.load();
    }//GEN-LAST:event_btnCreatActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        this.update();
        this.reset();
        this.load();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        this.delete();
        this.load();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        this.reset();
    }//GEN-LAST:event_btnResetActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        this.load();
    }//GEN-LAST:event_formWindowOpened

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatDarkLaf.setup();

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreat;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboCountry;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JPanel tab1;
    private javax.swing.JPanel tab2;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblStudents;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFullname;
    private javax.swing.JTextField txtMarks;
    // End of variables declaration//GEN-END:variables
}
