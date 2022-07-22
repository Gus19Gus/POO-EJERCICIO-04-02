/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicio;

import Modelo.Producto;
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
public class ProductoServicio implements IProductoServicio{
    
    private static List<Producto> productoList= new ArrayList<>();
    
    @Override
    public Producto crear(Producto producto) {
        this.productoList.add(producto);
        return producto;
    }
 @Override
    public void almacenarEnArchivo(Producto producto, String ruta) throws Exception {
    ObjectOutputStream salida = null;
        try {
            salida = new ObjectOutputStream(new FileOutputStream(new File(ruta),true));
            salida.writeObject(producto);
            salida.close();
        } catch (Exception ex) {
            salida.close();
        } 
    }
    @Override
    public Producto modificar(int codigoNuevo, Producto productoNuevo) {
        var posicion = this.buscarPosicion(this.buscarPorCodigo(codigoNuevo));
        this.listar().get(posicion).setNombreProducto(productoNuevo.getNombreProducto());
        this.listar().get(posicion).setTipoProducto(productoNuevo.getTipoProducto());
        this.listar().get(posicion).setCatidadProducto(productoNuevo.getCatidadProducto());
        this.listar().get(posicion).setPrecioProducto(productoNuevo.getPrecioProducto());
        this.listar().get(posicion).setProductoStok(productoNuevo.getProductoStok());

        
        return productoNuevo;
    }

    @Override
    public Producto eliminar(int codigoProducto) {
        Producto producto = this.buscarPorCodigo(codigoProducto);
        var posicion=this.buscarPosicion(producto);
        this.listar().remove(posicion);
        return producto;
    }

    @Override
    public Producto buscarPorCodigo(int codigoProducto) {
        Producto producto = null;
        for(var p:this.listar()){
            if(codigoProducto==p.getCodigoProducto()){
                producto = p;
                break;
            }
        }
        return producto;
    }

    @Override
    public int buscarPosicion(Producto producto) {
        
        int posicion = -1;
        for(var p:this.productoList){
            posicion++;
            if(p.getCodigoProducto()==producto.getCodigoProducto()){
                break;
            }
        }
        return posicion;
    }

    @Override
    public List<Producto> listar() {
        return this.productoList;
    }

   

    @Override
    public List<Producto> recuperarDeArchivo(String ruta) throws Exception {
           List<Producto> personaList = new ArrayList<Producto>();
        var fis = new FileInputStream(new File(ruta));
        ObjectInputStream entrada = null;
        try{        
            while(fis.available()>0){
                entrada = new ObjectInputStream(fis);
                Producto producto = (Producto) entrada.readObject();
                personaList.add(producto);
            }
            entrada.close();
        }catch(Exception ex){
            entrada.close();
        }
        return productoList;
    }
       
}

    
    

