package com.distribuida.rest;

import com.distribuida.db.SingerInstrument;
import com.distribuida.repo.SingerInstrumentRepo;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;

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
    @Timeout(4000)
    @Retry(maxRetries = 2)
    @Path("/singer/{id}")
    public List<SingerInstrument> findBySingerId(@PathParam("id") Integer id){
        return rep.findBySingerId(id);
    }

    @GET
    @Timeout(4000)
    @Retry(maxRetries = 2)
    @Path("/instrument/{id}")
    public List<SingerInstrument> findByInstrumentId(@PathParam("id") Integer id){
        return rep.findByInstrumentId(id);
    }

    @POST
    @Timeout(4000)
    @Retry(maxRetries = 2)
    public Response create(SingerInstrument obj) {
        rep.create(obj);
        return Response.ok(obj).status(Response.Status.CREATED).build();
    }

    @DELETE
    @Timeout(4000)
    @Retry(maxRetries = 2)
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
