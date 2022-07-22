/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicio;

import Modelo.Cliente;
import Modelo.Factura;
import Modelo.Producto;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gus-Gus
 */
public class FacturaServicio implements IFacturaServicio{
    
    private static List<Factura> facturaList = new ArrayList<>();

    @Override
    public Factura crear(Factura factura) {
         this.facturaList.add(factura);
         return factura;
    }

    public void almacenarEnArchivo(Producto producto, Cliente cliente, Factura factura, String ruta) throws Exception {
       DataOutputStream salida=null;
        try{
            salida = new DataOutputStream(new FileOutputStream(ruta,true));
            salida.writeInt(producto.getCodigoProducto());
            salida.writeInt(cliente.getNumCedula());
            salida.writeUTF(producto.getNombreProducto());
            salida.writeUTF(cliente.getApellidoCliente());
            salida.close();
        }
        catch(Exception e1){
            salida.close();
        }
    
    }
    
    
    public Factura modificar(int numeroNuevo, Factura facturaNuevo) {
        var posicion=this.buscarPosicion(this.buscarNFactura(numeroNuevo));
        this.listar().get(posicion).setUnCliente(facturaNuevo.getUnCliente());
        this.listar().get(posicion).setDireccion(facturaNuevo.getDireccion());
        this.listar().get(posicion).setUnProducto(facturaNuevo.getUnProducto());
        this.listar().get(posicion).setFecha(facturaNuevo.getFecha());
        this.listar().get(posicion).setTotal(facturaNuevo.getTotal());
        this.listar().get(posicion).setCantidad(facturaNuevo.getCantidad());
        return facturaNuevo;
    }

    @Override
    public Factura eliminar(int nuevoFactura) {
        Factura factura = this.buscarNFactura(nuevoFactura);
        var posicion = this.buscarPosicion(factura);
        this.listar().remove(posicion);
        return factura;
    }

    @Override
    public Factura buscarNFactura(int nuevoFactura) {
        Factura factura = null;
        for(var p:this.listar()){
            if(nuevoFactura==p.getNumFactura()){
                factura = p;
                break;
            }
        }
        return factura;
    }

    @Override
    public int buscarPosicion(Factura factura) {
        
        int posicion =-1;
        for(var p:this.facturaList){
            posicion++;
            if(p.getNumFactura()==factura.getNumFactura()){
                break;
            }
        }
        return posicion;
    }

    @Override
    public List<Factura> listar() {
        return this.facturaList;
    }


    @Override
    public List<Factura> recuperarDeArchivo(String rutaArchivo) throws Exception {
            List<Factura> personaList = new ArrayList<Factura>();
        var fis = new FileInputStream(new File(rutaArchivo));
        ObjectInputStream entrada = null;
        try{        
            while(fis.available()>0){
                entrada = new ObjectInputStream(fis);
                Factura factura = (Factura) entrada.readObject();
                personaList.add(factura);
            }
            entrada.close();
        }catch(Exception ex){
            entrada.close();
        }
        return personaList;
    }

    @Override
    public Factura modificar(int numeroNuevo, Producto producto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void almacenarEnArchivo(Factura factura, String ruta) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
 }
    
    

