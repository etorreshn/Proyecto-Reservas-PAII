/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.UsuariosDAO;
import dto.UsuariosDTO;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Edwin
 */
public class usuarios extends javax.swing.JDialog {

    /**
     * Creates new form Usuarios
     */
    
    private DefaultTableModel modeloTabla;
    private UsuariosDAO usuariosDAO = new UsuariosDAO();
    private JFMenu parent;
    
    public usuarios(JFMenu parent, boolean modal) throws SQLException {
        super(parent, modal); 
        initComponents();
        setLocationRelativeTo(null);
        this.parent = parent;

        // Configuración de tabla
        modeloTabla = (DefaultTableModel) jTable1.getModel();
        configurarTabla();  // Ahora incluye toda la configuración

        refrescarTabla();
    }

    
private class CheckBoxRenderer extends JCheckBox implements TableCellRenderer {
    public CheckBoxRenderer() {
        setHorizontalAlignment(JCheckBox.CENTER);
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                 boolean isSelected, boolean hasFocus,
                                                 int row, int column) {
        if (value instanceof Integer) {
            setSelected((Integer) value == 1);
        }
        
        // Cambiar color de fondo para selección
        if (isSelected) {
            setBackground(table.getSelectionBackground());
            setForeground(table.getSelectionForeground());
        } else {
            setBackground(table.getBackground());
            setForeground(table.getForeground());
        }
        
        return this;
    }
}

private class CheckBoxEditor extends DefaultCellEditor {
    private final JCheckBox checkBox = new JCheckBox();

    public CheckBoxEditor(JCheckBox checkBox) {
        super(checkBox);
        this.checkBox.setHorizontalAlignment(JCheckBox.CENTER);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                              boolean isSelected, int row, int column) {
        if (value instanceof Integer) {
            checkBox.setSelected((Integer) value == 1);
        }
        return checkBox;
    }

    @Override
    public Object getCellEditorValue() {
        return checkBox.isSelected() ? 1 : 0;
    }
}

private void configurarTabla() {
    // Ocultar contraseña
    jTable1.getColumnModel().getColumn(4).setMinWidth(0);
    jTable1.getColumnModel().getColumn(4).setMaxWidth(0);
    
    // Configurar CheckBox para Activo
    jTable1.getColumnModel().getColumn(6).setCellRenderer(new CheckBoxRenderer());
    jTable1.getColumnModel().getColumn(6).setCellEditor(new CheckBoxEditor(new JCheckBox()));
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
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Usuarios");
        setResizable(false);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Usuario", "Nombre", "Email", "Contraseña", "Rol", "Activo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Nuevo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Editar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Eliminar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 777, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
                UsuariosDTO oUsuariosDTO = new UsuariosDTO();
        // Crear la ventana para la operación de insertar (INS)
        OpUsuarios oOpUsuarios = new OpUsuarios(this, true, "INS", oUsuariosDTO);
        oOpUsuarios.setVisible(true);  // Mostrar la ventana de operación
        oOpUsuarios.addWindowListener(new WindowAdapter() {
    @Override
    public void windowClosed(WindowEvent e) {
        refrescarTabla();
    }
});
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
            // TODO add your handling code here:
    int fila = jTable1.getSelectedRow();

    if (fila != -1) {
        try {
            // 1. Obtener el nombre de usuario de la tabla
            String usuario = modeloTabla.getValueAt(fila, 1).toString();

            // 2. Obtener TODOS los datos desde la BD (evita inconsistencia)
            UsuariosDTO usuarioDTO = usuariosDAO.getByUsuario(usuario);

            if (usuarioDTO == null) {
                JOptionPane.showMessageDialog(this, 
                    "Usuario no encontrado en la base de datos", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 3. Validar campo activo (opcional, si es editable en la tabla)
            Object valor = modeloTabla.getValueAt(fila, 6);
            int activo = (valor instanceof Integer) ? (int) valor : 0; // Valor por defecto
            if (activo != 0 && activo != 1) {
                JOptionPane.showMessageDialog(this, 
                    "El valor de 'activo' debe ser 0 o 1", 
                    "Error en datos", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            usuarioDTO.setActivo(activo); // Actualizar solo si es válido

            // 4. Crear y mostrar ventana de edición
            OpUsuarios oOpUsuarios = new OpUsuarios(this, true, "UPD", usuarioDTO);
            oOpUsuarios.setVisible(true);
            oOpUsuarios.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosed(WindowEvent e) {
            refrescarTabla();
        }
    });
        
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, 
            "Error en los datos numéricos (ID, Rol o Activo). Verifique los tipos.", 
            "Error de formato", 
            JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, 
            "Error al acceder a la base de datos: " + e.getMessage(), 
            "Error crítico", 
            JOptionPane.ERROR_MESSAGE);
    }
} else {
    JOptionPane.showMessageDialog(this, 
        "Debe seleccionar un usuario para editar", 
        "ERROR", 
        JOptionPane.ERROR_MESSAGE);
}
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
            int fila = jTable1.getSelectedRow();  // Obtener la fila seleccionada
    if (fila != -1) {
        int idUsuario = Integer.parseInt(jTable1.getValueAt(fila, 0).toString());  // Obtener el ID del usuario desde la tabla
        int opcion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de eliminar este usuario?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        
        if (opcion == JOptionPane.YES_OPTION) {
            boolean eliminado = usuariosDAO.delete(idUsuario);  // Llamada al método delete() del DAO para eliminar el usuario
            if (eliminado) {
                try {
                    cargarDatos();  // Actualizar la tabla después de la eliminación
                } catch (SQLException ex) {
                    Logger.getLogger(usuarios.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(this, "Usuario eliminado exitosamente.");

            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } else {
        JOptionPane.showMessageDialog(this, "Debe seleccionar un usuario", "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
             //   new usuarios().setVisible(true);
            }
        });
    }

    public void cargarDatos() throws SQLException  {
        modeloTabla.setRowCount(0);
        List<UsuariosDTO> usuarios = usuariosDAO.getAll();

        for(UsuariosDTO usuario : usuarios) {
            modeloTabla.addRow(new Object[]{
                usuario.getId(),
                usuario.getUsuario(),
                usuario.getNombre(),
                usuario.getEmail(),
                "********",
                usuario.getId_Rol(),
                usuario.getActivo() // Envía directamente 0 o 1
            });
        }
        if (modeloTabla.getRowCount() > 0) {
            jTable1.setRowSelectionInterval(0, 0); 
            jTable1.scrollRectToVisible(jTable1.getCellRect(0, 0, true)); 
    }
    }

// Nuevo método para refresco seguro
public void refrescarTabla() {
    try {
        cargarDatos();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, 
            "Error al actualizar datos: " + e.getMessage(), 
            "Error", 
            JOptionPane.ERROR_MESSAGE);
    }
}

private void limpiarPassword(char[] password) {
    if (password != null) {
        Arrays.fill(password, '\0');
    }
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
