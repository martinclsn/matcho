package com.clowndata;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("persons")
@Stateless
public class PersonRepository {

    @PersistenceContext
    EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersons() {
        TypedQuery<Person> query = em.createQuery("select p from Person p", Person.class);
        return Response.ok().entity(query.getResultList()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postPerson(Person person) {
        em.persist(person);
        return Response.ok().build();
    }

}
