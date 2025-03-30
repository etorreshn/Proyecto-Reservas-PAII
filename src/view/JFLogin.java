/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

/**
 *
 * @author Edwin
 */
import bd.ConexionBD;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import utils.PasswordUtils; // Import correcto
import javax.swing.*;
import java.sql.*;
import java.util.Arrays;

public class JFLogin extends javax.swing.JDialog  {
    private int intentos = 0;
    private JFMenu parent;
    /**
     * Creates new form JFLogin
     */
    public JFLogin(JFMenu parent, boolean modal) {
    super(parent, modal);
    this.parent = parent;
    initComponents();
    setLocationRelativeTo(parent);
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Impide que la X cierre directamente
    
    this.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            int respuesta = JOptionPane.showConfirmDialog(
                JFLogin.this, 
                "¿Desea salir de la aplicación?", 
                "Confirmar salida", 
                JOptionPane.YES_NO_OPTION
            );
            
            if (respuesta == JOptionPane.YES_OPTION) {
                Arrays.fill(jPasswordField1.getPassword(), '\0');
                System.exit(0);
            }
        }
    });
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Login");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Iniciar sesion");

        jLabel2.setText("Usuario:");

        jLabel3.setText("Contraseña:");

        jTextField1.setName("jTextField1"); // NOI18N

        jPasswordField1.setName("jPasswordField1"); // NOI18N

        jButton1.setText("Aceptar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPasswordField1))))))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel1)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(39, 39, 39))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
String usuario = jTextField1.getText().trim();
char[] passwordChars = jPasswordField1.getPassword();

if (usuario.isEmpty() || passwordChars.length == 0) { // Mejor práctica que isEmpty()
    JOptionPane.showMessageDialog(this, 
        "Usuario y contraseña son obligatorios", 
        "Error", JOptionPane.WARNING_MESSAGE);
    Arrays.fill(passwordChars, '0');
    return;
}

try (Connection conn = ConexionBD.getConnection()) {
    // Usa el mismo nombre de tabla (ej: "usuarios")
    String sql = "SELECT contrasena, salt, activo FROM usuarios WHERE usuario = ?";
    PreparedStatement stmt = conn.prepareStatement(sql);
    stmt.setString(1, usuario);
    ResultSet rs = stmt.executeQuery();
    
    if (rs.next()) {
        if (rs.getInt("activo") == 0) {
            JOptionPane.showMessageDialog(this,
                "Usuario bloqueado. Contacte al administrador.",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String hashBD = rs.getString("contrasena");
        String saltBD = rs.getString("salt");
        String hashCalculado = PasswordUtils.hashPassword(passwordChars, saltBD); // Usar char[]
        
        if (hashBD.equals(hashCalculado)) {
            parent.notificarLogin(true);
            dispose();
            return;
        }
    }
    
    // Manejo de intentos fallidos
    intentos++;
    if (intentos >= 3) {
        String sqlUpdate = "UPDATE usuarios SET activo = 0 WHERE usuario = ?"; // Tabla unificada
        try (PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate)) {
            stmtUpdate.setString(1, usuario);
            stmtUpdate.executeUpdate();
        }
        JOptionPane.showMessageDialog(this,
            "Demasiados intentos fallidos. Usuario bloqueado.",
            "Error", JOptionPane.ERROR_MESSAGE);
        dispose();
    } else {
        JOptionPane.showMessageDialog(this,
            "Credenciales incorrectas. Intentos: " + intentos + "/3",
            "Error", JOptionPane.ERROR_MESSAGE);
    }
} catch (SQLException ex) {
    JOptionPane.showMessageDialog(this,
        "Error de conexión: " + ex.getMessage(),
        "Error", JOptionPane.ERROR_MESSAGE);
} finally {
    Arrays.fill(passwordChars, '0');
}
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
            Arrays.fill(jPasswordField1.getPassword(), '\0');
    System.exit(0); // Cierra solo el diálogo de login
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(JFLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
              //  new JFLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
