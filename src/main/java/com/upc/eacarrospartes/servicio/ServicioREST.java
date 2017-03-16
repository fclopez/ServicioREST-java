/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upc.eacarrospartes.servicio;

import com.upc.eacarrospartesws.modelo.Persona;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author flopez
 */
/*url que atenderá las peticiones*/
@Path("/serviciorest")
public class ServicioREST {
    
    private static double Salario_Minimo = 737717;
    private static Map<Integer, Persona> personas = new HashMap<Integer, Persona>();
    
    static {
        for (int i = 0; i < 10; i++) {
            Persona persona = new Persona();
            int id = i + 1;
            persona.setId(id);
            persona.setNombreCompleto("Persona #: " + id);
            persona.setEdad(new Random().nextInt(40) + 1);
            double salario = Math.round((persona.getEdad() * Salario_Minimo / 3) * 100.0) / 100.0;
            persona.setSalario(salario);
            personas.put(id, persona);
        }
    }

    @GET
    @Path("/getPersonaxIdXML/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Persona getPersonaxIdXML(@PathParam("id") int id) {
        return personas.get(id);
    }

    @GET
    @Path("/getPersonaxIdJSON/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Persona getPersonaxIdJSON(@PathParam("id") int id) {
        return personas.get(id);
    }
   
    @GET
    @Path("/getAllPersonasXML")
    @Produces(MediaType.APPLICATION_XML)
    public List<Persona> getAllPersonasXML() {
        return new ArrayList<Persona>(personas.values());
    }
   
    @GET
    @Path("/getAllPersonasJSON")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Persona> getAllPersonsJSON() {
        return new ArrayList<Persona>(personas.values());
    }
   
      
    @GET
    @Path("/index")
    @Produces(MediaType.TEXT_HTML)
    public String indice() {
        return "<b>Hola Mundo</b>";
    }
   
    @GET
    /*Tipo de llamada*/
    @Path("/{param}")
    public Response getMsg(@PathParam("param") String msg) {
        /*Mostrará por pantalla el parámetro que le hemos pasado a la URL*/
        String output = "Test de parametros: " + msg;
        return Response.status(200).entity(output).build();
    }
    
}
