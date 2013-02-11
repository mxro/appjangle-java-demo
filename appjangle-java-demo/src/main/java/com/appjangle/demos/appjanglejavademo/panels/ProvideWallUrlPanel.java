/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appjangle.demos.appjanglejavademo.panels;

import com.appjangle.demos.appjanglejavademo.MainWindow;
import io.nextweb.Link;
import io.nextweb.Node;
import io.nextweb.Session;
import io.nextweb.fn.Closure;
import io.nextweb.fn.ExceptionListener;
import io.nextweb.fn.ExceptionResult;
import io.nextweb.jre.Nextweb;

/**
 *
 * @author mroh004
 */
public class ProvideWallUrlPanel extends javax.swing.JPanel implements AppPanel {

    MainWindow mw;

    @Override
    public void setMainWindow(MainWindow mw) {
        this.mw = mw;
    }

    /**
     * Creates new form ProvideWallUrlPanel
     */
    public ProvideWallUrlPanel() {
        initComponents();


    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        wallUrlTextField = new javax.swing.JTextField();
        submitButton = new javax.swing.JButton();

        jLabel1.setText("Please provide a wall URL:");

        wallUrlTextField.setText("http://slicnet.com/seed1/seed1/4/6/7/9/h/sd");

        submitButton.setText("Submit");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(wallUrlTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(submitButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(wallUrlTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(submitButton)
                .addContainerGap(215, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
        this.mw.display(new LoadingPanel());
        String url = wallUrlTextField.getText();

        final Session session = Nextweb.createSession();
        try {
           
            Link posts = session.node(url);

            posts.catchExceptions(new ExceptionListener() {

                public void onFailure(ExceptionResult r) {
                    mw.display(new ErrorPanel());
                    session.close();
                }
            });

            posts.get(new Closure<Node>() {

                public void apply(Node n) {
                    DisplayPostCountsPanel panel = new DisplayPostCountsPanel();
                    mw.display(panel);
                    panel.init(n);
                }
            });
        } catch (Throwable t) {
            mw.display(new ErrorPanel());
            session.close();
        }



    }//GEN-LAST:event_submitButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton submitButton;
    private javax.swing.JTextField wallUrlTextField;
    // End of variables declaration//GEN-END:variables
}
