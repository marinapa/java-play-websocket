package dao;

import models.entities.Response;

import java.util.List;

/**
 * Created by Maryna Pashkouskaya on 2015-12-11.
 */
public interface ResponseDao {

    Response createResponse(Response response);
    Response getResponseById(Integer id);
    List<Response> getAllResponses();
    Integer getResponseCount();

}
