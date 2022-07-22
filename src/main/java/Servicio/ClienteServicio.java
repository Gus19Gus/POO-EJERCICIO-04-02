/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Servicio;

import Modelo.Cliente;
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
public class ClienteServicio implements IClienteServicio {
    
    private static List<Cliente> clienteList = new ArrayList<>();

    @Override
    public Cliente crear(Cliente cliente) {
      this.clienteList.add(cliente);
      return cliente;
    }

    @Override
    public Cliente modificar(long numCedulaNuevo, Cliente clienteNuevo) {
       var posicion = this.buscarPosicion(this.buscarPorCedula(numCedulaNuevo));
       this.listar().get(posicion).setNombreCliente(clienteNuevo.getNombreCliente());
       this.listar().get(posicion).setApellidoCliente(clienteNuevo.getApellidoCliente());
       this.listar().get(posicion).setDireccion(clienteNuevo.getDireccion());
       this.listar().get(posicion).setTelefono(clienteNuevo.getTelefono());
       this.listar().get(posicion).setCorreo(clienteNuevo.getCorreo());
       return clienteNuevo;
       
    }

    @Override
    public Cliente eliminar(long numCedula) {
        Cliente cliente= this.buscarPorCedula(numCedula);
        var posicion = this.buscarPosicion(cliente);
        this.listar().remove(posicion);
        return cliente;
    }

    @Override
    public Cliente buscarPorCedula(long numCedula) {
      Cliente cliente = null;
      for(var p:this.listar()){
          if(numCedula==p.getNumCedula()){
              cliente=p;
              break;
          }
      }
      return cliente;
    }

    @Override
    public int buscarPosicion(Cliente cliente) {
        int posicion =-1;
        for(var p:this.clienteList){
            posicion++;
            if(p.getNumCedula()==cliente.getNumCedula()){
                break;
            }
        }
        return posicion;
    }

    @Override
    public List<Cliente> listar() {
        return this.clienteList;
    }

    @Override
    public void almacenarEnArchivo(Cliente cliente, String ruta) throws Exception {
    ObjectOutputStream salida = null;
        try {
            salida = new ObjectOutputStream(new FileOutputStream(new File(ruta),true));
            salida.writeObject(cliente);
            salida.close();
        } catch (Exception ex) {
            salida.close();
        } 
    
    }

    @Override
    public List<Cliente> recuperarDeArchivo(String ruta) throws Exception {
            List<Cliente> personaList = new ArrayList<Cliente>();
        var fis = new FileInputStream(new File(ruta));
        ObjectInputStream entrada = null;
        try{        
            while(fis.available()>0){
                entrada = new ObjectInputStream(fis);
                Cliente cliente = (Cliente) entrada.readObject();
                personaList.add(cliente);
            }
            entrada.close();
        }catch(Exception ex){
            entrada.close();
        }
        return personaList;
    }
    }


