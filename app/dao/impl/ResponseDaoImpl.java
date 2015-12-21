package dao.impl;

import dao.ResponseDao;
import models.entities.Response;
import play.db.jpa.JPA;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by Maryna Pashkouskaya on 2015-12-11.
 */
public class ResponseDaoImpl implements ResponseDao {

    @Override
    public Response createResponse(Response response) {
        EntityManager em = JPA.em();
        em.persist(response);
        em.flush();
        return response;
    }

    @Override
    public Response getResponseById(Integer id) {
        return null;
    }

    public static final String SQL_GET_ALL_RESPONSES = "from Response";

    @SuppressWarnings("unchecked")
    @Override
    public List<Response> getAllResponses() {
        return JPA.em().createQuery(SQL_GET_ALL_RESPONSES).getResultList();
    }

    public static final String SQL_GET_RESPONSE_COUNT = "select Count(*) from Response";

    @Override
    public Integer getResponseCount() {
        EntityManager em = JPA.em();
        Query query = em.createNativeQuery(SQL_GET_RESPONSE_COUNT);
        return ((BigInteger) query.getSingleResult()).intValue();
    }
}
