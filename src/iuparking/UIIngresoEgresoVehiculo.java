package iuparking;

import dominio.Cochera;
import dominio.Estadia;
import dominio.Vehiculo;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import observador.Observable;
import sistemas.Fachada;
import observador.IObservador;

public class UIIngresoEgresoVehiculo extends javax.swing.JFrame implements IObservador {
    
    private Fachada fachada = Fachada.getInstancia();

    /**
     * Creates new form IUIngresarVehiculo
     */
    public UIIngresoEgresoVehiculo() {
        initComponents();
        cargarSelectVehiculos();
        cargarSelectCocheras();
        fachada.agregarObservador(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnEntraVehiculo = new javax.swing.JToggleButton();
        btnSaleVehiculo = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        comboVehiculos = new javax.swing.JComboBox();
        comboCocheras = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableEstadias = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnEntraVehiculo.setBackground(new java.awt.Color(0, 204, 0));
        btnEntraVehiculo.setForeground(new java.awt.Color(255, 255, 255));
        btnEntraVehiculo.setText("ENTRA");
        btnEntraVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntraVehiculoActionPerformed(evt);
            }
        });

        btnSaleVehiculo.setBackground(new java.awt.Color(255, 51, 51));
        btnSaleVehiculo.setForeground(new java.awt.Color(255, 255, 255));
        btnSaleVehiculo.setText("SALE");
        btnSaleVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaleVehiculoActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("VEHICULOS");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("COCHERAS");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("ESTADIAS");

        tableEstadias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Vehiculo", "Cochera", "Entrada", "Salida", "Estado", "Anomalias", "Tiempo Estacionado", "Valor Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableEstadias);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(345, 345, 345)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 834, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(54, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(177, 177, 177)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(126, 126, 126))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(comboVehiculos, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSaleVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEntraVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(115, 115, 115)
                        .addComponent(comboCocheras, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(32, 32, 32))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboCocheras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboVehiculos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(93, 93, 93))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnEntraVehiculo)
                        .addGap(26, 26, 26)
                        .addComponent(btnSaleVehiculo)
                        .addGap(64, 64, 64)))
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEntraVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntraVehiculoActionPerformed
        // TODO add your handling code here:
        ingresoVehiculo();
    }//GEN-LAST:event_btnEntraVehiculoActionPerformed

    private void btnSaleVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaleVehiculoActionPerformed
        // TODO add your handling code here:
        egresoVehiculo();
    }//GEN-LAST:event_btnSaleVehiculoActionPerformed

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
            java.util.logging.Logger.getLogger(UIIngresoEgresoVehiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UIIngresoEgresoVehiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UIIngresoEgresoVehiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UIIngresoEgresoVehiculo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UIIngresoEgresoVehiculo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnEntraVehiculo;
    private javax.swing.JToggleButton btnSaleVehiculo;
    private javax.swing.JComboBox comboCocheras;
    private javax.swing.JComboBox comboVehiculos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableEstadias;
    // End of variables declaration//GEN-END:variables

    private void cargarSelectVehiculos(){
        comboVehiculos.addItem("Seleccionar vehiculo");
        ArrayList<Vehiculo> vehiculos = fachada.obtenerVehiculos();
        for(Vehiculo v:vehiculos){
            comboVehiculos.addItem(v.getPatente());
        }
    }
    
    private void cargarSelectCocheras(){
        comboCocheras.addItem("Seleccionar cochera");
       ArrayList<Cochera> cocheras = fachada.obtenerCocheras();
       for(Cochera c:cocheras){
           comboCocheras.addItem(c.getCodigo());
       }
    }
    
   private void cargarEstadias() {
        ArrayList<Estadia> estadias = fachada.obtenerEstadias();
        DefaultTableModel model = (DefaultTableModel) tableEstadias.getModel();
        model.setRowCount(0); 

        for (Estadia estadia : estadias) {
            Object[] row = new Object[8];
            row[0] = estadia.getVehiculo().getPatente();
            row[1] = estadia.getCochera() != null ? estadia.getCochera().getCodigo() : "";
            row[2] = estadia.getEntrada();
            row[3] = estadia.getSalida();
            row[4] = estadia.getEsFinalizadaToString();
            row[5] = estadia.getAnomalia() != null ? estadia.getAnomalia().getCodigoError() : "";
            row[6] = estadia.getCantidadUT();
            row[7] = estadia.getValorFacturado();

            model.addRow(row);
        }
    }
    
    
   private void ingresoVehiculo() {
        String patenteVehiculo = (String) comboVehiculos.getSelectedItem();
        String codigoCochera = (String) comboCocheras.getSelectedItem();

        if (!patenteVehiculo.equals("Seleccionar vehiculo") && !codigoCochera.equals("Seleccionar cochera")) {
            fachada.iniciarEstadia(patenteVehiculo, codigoCochera);
        } else {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un vehículo y una cochera antes de ingresar!");
        }
    }
   
   private void egresoVehiculo(){
        String patenteVehiculo = (String) comboVehiculos.getSelectedItem();
        String codigoCochera = (String) comboCocheras.getSelectedItem();
        
        if (!patenteVehiculo.equals("Seleccionar vehiculo") && !codigoCochera.equals("Seleccionar cochera")) {
            fachada.finalizarEstadia(patenteVehiculo, codigoCochera);
        } else {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un vehículo y una cochera antes de salir!");
        }
   }

    @Override
    public void actualizar(Object evento, Observable origen) {
        if(evento.equals(Fachada.Eventos.entraVehiculo) || evento.equals(Fachada.Eventos.saleVehiculo)){
            cargarEstadias();            
        } else {
        }
    }
    
}

