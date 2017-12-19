package rest;

import bean.SporocilaBean;
import entity.Sporocilo;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("sporocilni_sistem")
public class SporocilniSistemREST {

    @Context
    private UriInfo uriInfo;

    @Inject
    private SporocilaBean sporocilaBean;

    @GET
    public Response getSporocila() {

        List<Sporocilo> orders = sporocilaBean.getSporocila(uriInfo);

        return Response.ok(orders).build();
    }

    @GET
    @Path("/{sporociloId}")
    public Response getSporocilo(@PathParam("sporociloId") String sporociloId) {

        Sporocilo s = sporocilaBean.getSporocilo(sporociloId);

        if (s == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(s).build();
    }

    @POST
    public Response createSporocilo(Sporocilo sporocilo) {

        if (sporocilo.getPosiljateljId() == null || sporocilo.getPosiljateljId().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            sporocilo = sporocilaBean.createSporocilo(sporocilo);
        }

        if (sporocilo.getId() != null) {
            return Response.status(Response.Status.CREATED).entity(sporocilo).build();
        } else {
            return Response.status(Response.Status.CONFLICT).entity(sporocilo).build();
        }
    }

    @DELETE
    @Path("{sporociloId}")
    public Response deleteCustomer(@PathParam("sporociloId") String sporociloId) {

        boolean deleted = sporocilaBean.deleteSporocilo(sporociloId);

        if (deleted) {
            return Response.status(Response.Status.GONE).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
