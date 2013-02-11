/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appjangle.demos.appjanglejavademo;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mroh004
 */
public class DisplayPostCountsPanel extends javax.swing.JPanel {

    /**
     * Creates new form DisplayPostCountsPanel
     */
    public DisplayPostCountsPanel() {
        initComponents();
        
        displayData(new HashMap<String, String>() {{
            this.put("Caesar", "1");
            this.put("Cleopetra", "5");
            
        }});
        
        displayData(new HashMap<String, String>() {{
            this.put("Caesar", "3");
            this.put("Cleopetra", "5");
            this.put("Sokrates", "2");
        }});
    }

    private void displayData(Map<String, String> data) {
       int currentRow =0;
        for (Entry<String, String> row : data.entrySet()) {

           renderRow((DefaultTableModel) postCountTable.getModel(), currentRow, row.getKey(), row.getValue());
                    
           currentRow++;
       }
        
        
    }
    
    private void renderRow(DefaultTableModel model, int row, String key, String value) {
       
        if (model.getRowCount() > row) {
              String oldKey =  (String) model.getValueAt(row, 0);
              String oldValue = (String) model.getValueAt(row, 1);
             
              if (oldKey.equals(key) && oldValue.equals(value)) {
                  return;
              }
             
              model.setValueAt(key, row, 0);
              model.setValueAt(value, row, 1);
              return;
        }
        
        model.insertRow(row, new Object[] {key, value});
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        postCountTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        wallUrlLabel = new javax.swing.JLabel();

        postCountTable.setAutoCreateRowSorter(true);
        postCountTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Avatar", "Posts"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(postCountTable);

        jLabel1.setText("Posts Counts");

        wallUrlLabel.setText("[wallurl]");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(wallUrlLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(wallUrlLabel)
                .addGap(7, 7, 7))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable postCountTable;
    private javax.swing.JLabel wallUrlLabel;
    // End of variables declaration//GEN-END:variables
}
