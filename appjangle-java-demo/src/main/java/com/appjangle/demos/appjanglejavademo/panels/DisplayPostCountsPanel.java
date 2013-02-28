/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appjangle.demos.appjanglejavademo.panels;

import com.appjangle.demos.appjanglejavademo.Calculations;
import com.appjangle.demos.appjanglejavademo.MainWindow;
import io.nextweb.Node;
import io.nextweb.Session;
import io.nextweb.common.Interval;
import io.nextweb.common.Monitor;
import io.nextweb.common.MonitorContext;
import io.nextweb.fn.Closure;
import io.nextweb.jre.Nextweb;
import io.nextweb.operations.callbacks.NodeListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mroh004
 */
public class DisplayPostCountsPanel extends javax.swing.JPanel implements AppPanel {

    MainWindow mw;
    ExecutorService updateThread;

    @Override
    public void setMainWindow(MainWindow mw) {
        this.mw = mw;
        this.updateThread = Executors.newFixedThreadPool(1);
    }

    /**
     * Creates new form DisplayPostCountsPanel
     */
    public DisplayPostCountsPanel() {
        initComponents();
    }

    public void init(final Node posts) {
        this.wallUrlLabel.setText(posts.uri());
        updateData(posts);

        installMonitor(posts);

    }

    private void installMonitor(final Node posts) {
        Session monitorSession = Nextweb.createSession();
        monitorSession.node(posts).monitor().setInterval(Interval.FAST).setDepth(2).addListener(new NodeListener() {

            public void onWhenNodeChanged(MonitorContext context) {
                posts.reload(2).get(new Closure<Node>() {

                    @Override
                    public void apply(Node n) {
                        updateData(posts);
                    }
                });

            }
        }).get(new Closure<Monitor>() {

            public void apply(Monitor m) {
            }
        });
    }

    private void updateData(final Node posts) {
        this.updateThread.submit(
                new Runnable() {

                    @Override
                    public void run() {
                        Calculations c = new Calculations(posts.getSession());
                        Map<String, Integer> results = c.calculatePostsPerUser(posts);

                        displayData(toStringMap(results));
                    }
                });
    }

    private void displayData(Map<String, String> data) {
        int currentRow = 0;
        for (Entry<String, String> row : data.entrySet()) {

            renderRow((DefaultTableModel) postCountTable.getModel(), currentRow, row.getKey(), row.getValue());

            currentRow++;
        }


    }

    private void renderRow(DefaultTableModel model, int row, String key, String value) {

        if (model.getRowCount() > row) {
            String oldKey = (String) model.getValueAt(row, 0);
            String oldValue = (String) model.getValueAt(row, 1);

            if (oldKey.equals(key) && oldValue.equals(value)) {
                return;
            }

            model.setValueAt(key, row, 0);
            model.setValueAt(value, row, 1);
            return;
        }

        model.insertRow(row, new Object[]{key, value});

    }

    private Map<String, String> toStringMap(Map<String, Integer> source) {
        Map<String, String> dest = new HashMap<String, String>();

        for (Entry<String, Integer> e : source.entrySet()) {
            dest.put(e.getKey(), e.getValue().toString());
        }

        return dest;
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
        postCountTable.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
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

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
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