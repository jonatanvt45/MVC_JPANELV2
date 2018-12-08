/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Zeo
 */
public class ModelAgenda {

    private Connection conexion;
    private Statement st;
    private ResultSet rs;
    private String nombre;
    private String email;

    public String getNombre() {
        System.out.println("get nombre");
        return nombre;
    }

    public void setNombre(String nombre) {
        System.out.println("set nombre");
        this.nombre = nombre;
    }

    public String getEmail() {
        System.out.println("get email");
        return email;
    }

    public void setEmail(String email) {
        System.out.println("set email");
        this.email = email;
    }

    public void conectarDB() {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/agenda_mvc", "root", "");
            st = conexion.createStatement();
            rs = st.executeQuery("SELECT * FROM contactos;");
            rs.next();
            nombre = rs.getString("nombre");
            email = rs.getString("email");
            this.setEmail(email);
            this.setNombre(nombre);
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Error ModelAgenda 001: " + err.getMessage());
        }

    }
    
    /**
     * Método que realiza las siguiente acciones:
     * 1.- Moverse al primer registro
     * 2.- obtener el valor del nombre de rs y guardarlo en la variable nombre
     * 3.- obtener el valor del email de rs y guardarlo en la variable email
     */
    public void moverPrimerRegistro(){
        System.out.print("Programa accion moverPrimerRegistro");
        try {
            rs.first();
            llenarDatos();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 004" + ex.getMessage());
        }
    }
    
    /**
     * Método que realiza las siguiente acciones:
     * 1.- Moverse al siguiente registro
     * 2.- obtener el valor del nombre de rs y guardarlo en la variable nombre
     * 3.- obtener el valor del email de rs y guardarlo en la variable email
     */
    public void moverSiguienteRegistro(){
        System.out.print("Programa accion moverSiguienteRegistro");
        try {
            if (!rs.isLast()) {
                rs.next();
                llenarDatos();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 003" + ex.getMessage());
        }
    }
    
    /**
     * Método que realiza las siguiente acciones:
     * 1.- Moverse al anterior registro
     * 2.- obtener el valor del nombre de rs y guardarlo en la variable nombre
     * 3.- obtener el valor del email de rs y guardarlo en la variable email
     */
    public void moverAnteriorRegistro(){
        System.out.print("Programa accion moverAnteriorRegistro");
        try {
            if (!rs.isFirst()) {
                rs.previous();
                llenarDatos();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 004" + ex.getMessage());
        }
    }
    
    /**
     * Método que realiza las siguiente acciones:
     * 1.- Moverse al ultimo registro
     * 2.- obtener el valor del nombre de rs y guardarlo en la variable nombre
     * 3.- obtener el valor del email de rs y guardarlo en la variable email
     */
    public void moverUltimoRegistro(){
        System.out.print("Programa accion moverUltimoRegistro");
        try {
            rs.last();
            llenarDatos();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 005" + ex.getMessage());
        }
    }
    
    /**
     * Este metodo se encarga de obtener los registros y mandarlos a los setter
     * para despues ser obtenidos por el controlador y mostrados en la vista
     */
    
    private void llenarDatos() {
        System.out.print("Programa accion llenarDatos");
        try {
            this.setNombre(rs.getString("nombre"));
            this.setEmail(rs.getString("email"));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error 006" + ex.getMessage());
        }        
    }
    
    /**
     * Este metodo se encarga de insertar un registro nuevo a la BDD
     * @param nombre Nombre de la persona a quien queremos registrar
     * @param email  Email de la persona a quien queremos registrar
     */
    
    public void insertarRegistro(String nombre, String email){
        System.out.print("Programa accion insertarRegistro");
        try{
            st.executeUpdate("INSERT INTO contactos(nombre,email) VALUES"+"('"+nombre+"','"+email+"');");
            this.conectarDB();
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error 007: " + ex.getMessage());
        }
    }
    
    /**
     * Este metodo se encarga de modificar un registro existente
     * @param nombre Nuevo nombre
     * @param email  Nuevo email
     */
    
    public void modificarRegistro(String nombre, String email){
        System.out.print("Programa accion modificarRegistro");
        try{
            String actualEmail = this.getEmail();
            st.executeUpdate("UPDATE contactos SET nombre='"+nombre+"',email='"+email+"' WHERE email='"+actualEmail+"';");
            this.conectarDB();
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error 008: " + ex.getMessage());
        }
    }
    
   
    
    public void eliminarRegistro(String email){
        System.out.print("Programa accion eliminarRegistro");
        try{
            st.executeUpdate("DELETE FROM contactos WHERE email='"+email+"';");
            this.conectarDB();
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error 009: " + ex.getMessage());
        }
    }
}