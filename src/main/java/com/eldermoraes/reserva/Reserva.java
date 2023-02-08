package com.eldermoraes.reserva;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.transaction.Transactional;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import com.eldermoraes.cliente.Cliente;


@Entity
public class Reserva extends PanacheEntity {

    @ManyToOne(fetch = FetchType.LAZY)
   
    public Cliente cliente;
}

@POST
@Transactional
public Response createReserva(Reserva reserva){
    reserva.id = null;
    resrva.persist();

    return Response.status(Status.CREATED).entity(reserva);
}
