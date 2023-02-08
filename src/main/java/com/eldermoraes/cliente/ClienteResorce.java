package com.eldermoraes.cliente;
import java.util.List;
import com.eldermoraes.cliente.Cliente;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteResorce {

      @GET
      public List<Cliente> getAllClients(){
          return Cliente.listAll();
      }

      @GET
      @Path("/{id}")
      public Cliente findById(@QueryParam("id") long id){
          return Cliente.findById(id);
      }

      @DELETE
      @Path("/{id}")
      @Transactional
      public void deleteCliente(@QueryParam("id") long id){
          Cliente entity = Cliente.findById(id);
          if(entity == null) {
              throw new NotFoundException();
          }
          entity.delete();
      }

      @Transactional
      @POST
      public Response createClient (Cliente cliente){
          cliente.id = null;
          cliente.persist();

          return Response.status(Status.CREATED).entity(cliente).build();
      }
  
}
