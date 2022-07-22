/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Cliente;
import Servicio.ClienteServicio;
import java.util.List;

/**
 *
 * @author Gus-Gus01
 */
public class ClienteControl {
    
    private ClienteServicio clienteServicio = new ClienteServicio();
    
    public Cliente crearCliente(String [] args)throws RuntimeException{
        Cliente cliente = new Cliente(this.convertirEntero(args[0]), args [1], args[2], args[3], this.convertirEntero(args[4]), args[5]);
        this.clienteServicio.crear(cliente);
        return cliente;
    }
      private int convertirEntero(String numero)
    {
        try{
            return Integer.valueOf(numero);
        }catch(NumberFormatException e1){
            throw new RuntimeException("Valor ingresado no es un número entero"); 
        }catch(Exception e1){
            throw new RuntimeException("Error inesperado"); 
        }
    }
        private double convertirDecimal(String numero)
    {
        try{
            return Double.valueOf(numero);
        }catch(NumberFormatException e1){
            throw new RuntimeException("Valor ingresado no es un número entero"); 
        }catch(Exception e1){
            throw new RuntimeException("Error inesperado"); 
            
        }
    }
    
    public Cliente buscarCliente(String arg){
        return this.clienteServicio.buscarPorCedula(convertirEntero(arg));
    }
    
    public Cliente eliminar(String arg){
        return this.clienteServicio.eliminar(convertirEntero(arg));
    }
    public Cliente modificar(String []args){
        Cliente clienteNuevo = new Cliente(Integer.valueOf(args[0]), args [1], args[2], args[3], Integer.valueOf(args[4]), args[5]);
        this.clienteServicio.modificar(convertirEntero(args[0]), clienteNuevo);
        return clienteNuevo;
    }
    public List<Cliente> listar(){
        return this.clienteServicio.listar();
    }
   
}
