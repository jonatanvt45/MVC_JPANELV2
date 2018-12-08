/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import models.ModelAgenda;
import views.ViewAgenda;

/**
 *
 * @author Zeo
 */
public class ControllerAgenda {

    ModelAgenda modelAgenda;
    ViewAgenda viewAgenda;

   
    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        
            if (e.getSource() == viewAgenda.jbtn_primero) {
                jbtn_primero_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_anterior) {
                jbtn_anterior_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_siguiente) {
                jbtn_siguiente_actionPerformed();
            } else if (e.getSource() == viewAgenda.jbtn_ultimo) {
                jbtn_ultimo_actionPerformed();
            } else if(e.getSource() == viewAgenda.jbn_eliminar){
                int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro de "
                    + "eliminar el registro?", "Alerta!", JOptionPane.YES_NO_OPTION);
                if(resp==0){
                    jbtn_eliminar();
                } else{
                    jbtn_primero_actionPerformed();
                }                
            } else if(e.getSource() == viewAgenda.jbn_insertar){
                jbtn_insertar();
            } else if(e.getSource() == viewAgenda.jbn_modificar){
                jbtn_modificar();
            } else if(e.getSource() == viewAgenda.jbn_nuevo){
                jbtn_nuevo();
            }
        }
        /**
         * Metodo para las acciones del boton eliminar
         */
        private void jbtn_eliminar() {
            System.out.println("Action del boton jbtn_eliminar");
            System.out.println(modelAgenda.getEmail());
            modelAgenda.eliminarRegistro(modelAgenda.getEmail());
            JOptionPane.showMessageDialog(viewAgenda, "Registro eliminado correctamente");
            jbtn_primero_actionPerformed();
        }
        /**
         * Metodo para las acciones del boton insertar
         */
        private void jbtn_insertar() {
            System.out.println("Action del boton jbtn_insertar");
            modelAgenda.setNombre(viewAgenda.jtf_nombre.getText());
            modelAgenda.setEmail(viewAgenda.jtf_email.getText());
            modelAgenda.insertarRegistro(modelAgenda.getNombre(),modelAgenda.getEmail());
            JOptionPane.showMessageDialog(viewAgenda, "Registro guardado correctamente");
            jbtn_primero_actionPerformed();
        }
        /**
         * Metodo para las acciones del boton modifcar
         */
        private void jbtn_modificar() {
            System.out.println("Action del boton jbtn_modificar");
            modelAgenda.modificarRegistro(viewAgenda.jtf_nombre.getText(), viewAgenda.jtf_email.getText());
            JOptionPane.showMessageDialog(viewAgenda, "Registro actualizado correctamente");
            jbtn_primero_actionPerformed();
        }
        /**
         * Metodo para las acciones del boton nuevo
         */
        private void jbtn_nuevo() {
            System.out.println("Action del boton jbtn_nuevo");
            modelAgenda.setEmail(null);
            modelAgenda.setNombre(null);
            viewAgenda.jtf_email.setText(modelAgenda.getEmail());
            viewAgenda.jtf_nombre.setText(modelAgenda.getNombre());
        }

    };

    /**
     * Constructor de Controlador para unir el ModelAgenda y ViewAgenda
     *
     * @param modelAgenda objeto de tipo ModelAgenda
     * @param viewAgenda objeto de tipo ViewAgenda
     */
    public ControllerAgenda(ModelAgenda modelAgenda, ViewAgenda viewAgenda) {
        this.modelAgenda = modelAgenda;
        this.viewAgenda = viewAgenda;
        initComponents();
        setActionListener();
        initDB();
    }

    /**
     * Método que llama al método conectarBD del modelo y muestra el nombre y
     * email del primer registro en las cajas de texto de ViewAgenda.
     */
    public void initDB(){
        modelAgenda.conectarDB();
        viewAgenda.jtf_nombre.setText(modelAgenda.getNombre());
        viewAgenda.jtf_email.setText(modelAgenda.getEmail());
    }
    /**
     * Metodo para inicializar la ViewAgenda
     */
    public void initComponents() {
        viewAgenda.setLocationRelativeTo(null);
        viewAgenda.setTitle("Agenda MVC");
        viewAgenda.setVisible(true);
    }

    /**
     * Método para agregar el actionListener a cada boton de la vista
     */
    public void setActionListener() {
        viewAgenda.jbtn_primero.addActionListener(actionListener);
        viewAgenda.jbtn_anterior.addActionListener(actionListener);
        viewAgenda.jbtn_siguiente.addActionListener(actionListener);
        viewAgenda.jbtn_ultimo.addActionListener(actionListener);
        viewAgenda.jbn_nuevo.addActionListener(actionListener);
        viewAgenda.jbn_eliminar.addActionListener(actionListener);
        viewAgenda.jbn_modificar.addActionListener(actionListener);
        viewAgenda.jbn_insertar.addActionListener(actionListener);
    }

    /**
     * Método para ver el primer registro de la tabla contactos
     */
    private void jbtn_primero_actionPerformed() {
        System.out.println("Action del boton jbtn_primero");
        modelAgenda.moverPrimerRegistro();
        viewAgenda.jtf_nombre.setText(modelAgenda.getNombre());
        viewAgenda.jtf_email.setText(modelAgenda.getEmail());
        //invocar al metodo moverPrimerRegistro
        //mostrar nombre en la vista
        //mostar email en la vista
    }

    /**
     * Método para ver el registro anterior de la tabla contactos
     */
    private void jbtn_anterior_actionPerformed() {
        System.out.println("Action del boton jbtn_anterior");
        modelAgenda.moverAnteriorRegistro();
        viewAgenda.jtf_nombre.setText(modelAgenda.getNombre());
        viewAgenda.jtf_email.setText(modelAgenda.getEmail());
        //invocar al metodo moverAnteriorRegistro
        //mostrar nombre en la vista
        //mostar email en la vista
    }

    /**
     * Método para ver el último registro de la tabla contactos
     */
    private void jbtn_ultimo_actionPerformed() {
        System.out.println("Action del boton jbtn_ultimo");
        modelAgenda.moverUltimoRegistro();
        viewAgenda.jtf_nombre.setText(modelAgenda.getNombre());
        viewAgenda.jtf_email.setText(modelAgenda.getEmail());
        //invocar al metodo moverUltimoRegistro
        //mostrar nombre en la vista
        //mostar email en la vista
    }

    /**
     * Método para ver el siguiente registro de la tabla contactos
     */
    private void jbtn_siguiente_actionPerformed() {
        System.out.println("Action del boton jbtn_siguiente");
        modelAgenda.moverSiguienteRegistro();
        viewAgenda.jtf_nombre.setText(modelAgenda.getNombre());
        viewAgenda.jtf_email.setText(modelAgenda.getEmail());
        //invocar al metodo moverSiguienteRegistro
        //mostrar nombre en la vista
        //mostar email en la vista
    }
}
