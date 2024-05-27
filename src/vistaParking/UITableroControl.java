/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vistaParking;

import controlador.ControladorTableroControl;
import dominio.Anomalia;
import dominio.Estadia;
import dominio.Parking;
import interfaces.ITableroControl;
import java.awt.Frame;
import java.util.ArrayList;
import javax.swing.table.TableModel;

/**
 *
 * @author facul
 */
public class UITableroControl extends javax.swing.JFrame implements ITableroControl{
    private ControladorTableroControl controlador;
    private ArrayList<Estadia> estadiasAnomaliasCheckbox; 
    /**
     * Creates new form TableroControl
     */
    public UITableroControl(Frame parent, boolean modal) {
        initComponents();
        this.controlador = new ControladorTableroControl(this);
        this.estadiasAnomaliasCheckbox = new ArrayList<Estadia> ();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_tablero_control = new javax.swing.JTable();
        jSeparator2 = new javax.swing.JSeparator();
        btn_precios = new javax.swing.JToggleButton();
        btn_cartelera = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_anomalias = new javax.swing.JTable();
        ch_monitorear_anomalias = new javax.swing.JCheckBox();
        btn_cerrar = new javax.swing.JButton();
        txt_cantidad_estadias = new javax.swing.JLabel();
        txt_total_facturado = new javax.swing.JLabel();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Tablero de Control");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Estadias:");

        jLabel3.setText("Facturacion:");

        tb_tablero_control.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Parking", "# Ocupadas", "# Libres", "Estado", "Factor Demanda", "# Estadias", "Multas", "Subtotal"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Float.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tb_tablero_control);

        btn_precios.setBackground(new java.awt.Color(255, 204, 204));
        btn_precios.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_precios.setText("Precios");
        btn_precios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_preciosActionPerformed(evt);
            }
        });

        btn_cartelera.setBackground(new java.awt.Color(255, 255, 153));
        btn_cartelera.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_cartelera.setText("Cartelera");
        btn_cartelera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_carteleraActionPerformed(evt);
            }
        });

        tb_anomalias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Fecha/Hora", "Propietario", "Codigo de anomalia", "Cochera"
            }
        ));
        jScrollPane2.setViewportView(tb_anomalias);

        ch_monitorear_anomalias.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ch_monitorear_anomalias.setText("Monitorear anomalías");
        ch_monitorear_anomalias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ch_monitorear_anomaliasActionPerformed(evt);
            }
        });

        btn_cerrar.setBackground(new java.awt.Color(51, 102, 255));
        btn_cerrar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_cerrar.setForeground(new java.awt.Color(255, 255, 255));
        btn_cerrar.setText("Cerrar");

        txt_cantidad_estadias.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        txt_cantidad_estadias.setText(" ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_precios)
                .addGap(51, 51, 51)
                .addComponent(btn_cartelera)
                .addGap(28, 28, 28))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 784, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_cantidad_estadias, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_total_facturado, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 752, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btn_cerrar)
                                .addGap(31, 31, 31))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(ch_monitorear_anomalias, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(txt_cantidad_estadias))
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(txt_total_facturado, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_precios)
                    .addComponent(btn_cartelera))
                .addGap(33, 33, 33)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ch_monitorear_anomalias)
                        .addGap(18, 18, 18)
                        .addComponent(btn_cerrar))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_preciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_preciosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_preciosActionPerformed

    private void ch_monitorear_anomaliasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ch_monitorear_anomaliasActionPerformed
        // TODO add your handling code here:
        boolean monitorear = this.ch_monitorear_anomalias.isSelected();
        if(monitorear) this.controlador.agregarObservador();
        if(!monitorear)this.controlador.quitarObservador();
    }//GEN-LAST:event_ch_monitorear_anomaliasActionPerformed

    private void btn_carteleraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_carteleraActionPerformed
        // TODO add your handling code here:
        //SOLO POR TEST
        
    }//GEN-LAST:event_btn_carteleraActionPerformed

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
            java.util.logging.Logger.getLogger(UITableroControl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UITableroControl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UITableroControl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UITableroControl.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cartelera;
    private javax.swing.JButton btn_cerrar;
    private javax.swing.JToggleButton btn_precios;
    private javax.swing.JCheckBox ch_monitorear_anomalias;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable tb_anomalias;
    private javax.swing.JTable tb_tablero_control;
    private javax.swing.JLabel txt_cantidad_estadias;
    private javax.swing.JLabel txt_total_facturado;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mostrarCantidadEstadias(int cantidad) {
        this.txt_cantidad_estadias.setText(Integer.toString(cantidad));
    }

    @Override
    public void mostrarTotalFacturado(float total) {
       this.txt_total_facturado.setText(Float.toString(total));
    }
    @Override
    public void mostrarListaParkings(ArrayList<Parking> parkings) {
        TableModel modeloParkings = this.tb_tablero_control.getModel();
        for (int i = 0; i < parkings.size(); i++) {
            Parking p = parkings.get(i);
            modeloParkings.setValueAt(p.getNombre(), i, 0);
            modeloParkings.setValueAt(p.getCantidadCocherasOcupadas(), i, 1);
            modeloParkings.setValueAt(p.getCantidadCocherasDisponibles(), i, 2);
            modeloParkings.setValueAt("TODO", i, 3);
            modeloParkings.setValueAt(p.getFactorDemanda(), i, 4);
            modeloParkings.setValueAt(p.obtenerCantidadEstadias(), i, 5);
            modeloParkings.setValueAt(p.obtenerTotalFacturadoMultas(), i, 6);
            modeloParkings.setValueAt(p.obtenerTotalFacturado(), i, 7);
        }
    }

    @Override
    public void mostrarAnomaliasCheckbox(Estadia nueva) {
      this.estadiasAnomaliasCheckbox.add(nueva);
      TableModel modeloParkings = this.tb_anomalias.getModel();
        for (int i = 0; i < this.estadiasAnomaliasCheckbox.size(); i++) {
            Estadia e = this.estadiasAnomaliasCheckbox.get(i);
            modeloParkings.setValueAt(e.getAnomalia().getFechaCreacion(), i, 0);
            modeloParkings.setValueAt(e.getVehiculo().getPropietario().getNombre(), i, 1);
            modeloParkings.setValueAt(e.getAnomalia().getCodigoError(), i, 2);
            modeloParkings.setValueAt(e.getCochera().getCodigo(), i, 3);
        }
    }
}
