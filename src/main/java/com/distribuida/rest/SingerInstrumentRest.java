package com.distribuida.rest;

import com.distribuida.db.SingerInstrument;
import com.distribuida.repo.SingerInstrumentRepo;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/singers-instruments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
@Singleton
public class SingerInstrumentRest {

    @Inject
    SingerInstrumentRepo rep;

    @GET
    public List<SingerInstrument> findAll(){
        return rep.findAll();
    }

    @GET
    @Path("/singer/{id}")
    public List<SingerInstrument> findBySingerId(@PathParam("id") Integer id){
        return rep.findBySingerId(id);
    }

    @GET
    @Path("/instrument/{id}")
    public List<SingerInstrument> findByInstrumentId(@PathParam("id") Integer id){
        return rep.findByInstrumentId(id);
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Integer id) {
        var obj = rep.findById(id);
        if (obj == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(obj).build();
    }

    @POST
    public Response create(SingerInstrument obj) {
        rep.create(obj);
        return Response.ok(obj).status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, SingerInstrument obj) {
        var singerInstrument = rep.findById(id);
        if (singerInstrument == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        singerInstrument.setSingerId(obj.getSingerId());
        singerInstrument.setInstrumentId(obj.getInstrumentId());
        rep.update(singerInstrument);
        return Response.ok(singerInstrument).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        var obj = rep.findById(id);
        if (obj == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        rep.delete(id);
        return Response.ok().build();
    }


}
