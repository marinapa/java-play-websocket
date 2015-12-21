package services;

import dao.ResponseDao;
import models.entities.Response;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import play.Logger;
import play.db.jpa.JPA;
import play.libs.F;

import java.util.List;

/**
 * Created by Maryna Pashkouskaya on 2015-12-18.
 */
@Service
public class ResponseService implements InitializingBean {
    @Autowired
    private ResponseDao responseDao;

    public static Integer numberResponses;
    
    public Response createResponse(Response response) {
        try {
            return JPA.withTransaction(new F.Function0<Response>() {
                @Override
                public Response apply() throws Throwable {
                    return responseDao.createResponse(response);
                }
            });
        } catch (Throwable throwable) {
            Logger.error("ResponseService.createResponse error " + throwable.getMessage());
            throwable.printStackTrace();
        }
        return null;
    }

    public Response getResponseById(Integer id) {
        try {
            return JPA.withTransaction(new F.Function0<Response>() {
                @Override
                public Response apply() throws Throwable {
                    return responseDao.getResponseById(id);
                }
            });
        } catch (Throwable throwable) {
            Logger.error("ResponseService.getResponseId error " + throwable.getMessage());
            throwable.printStackTrace();
        }
        return null;
    }

    public List<Response> getAllResponses() {
        try {
            return JPA.withTransaction(new F.Function0<List<Response>>() {
                @Override
                public List<Response> apply() throws Throwable {
                    return responseDao.getAllResponses();
                }
            });
        } catch (Throwable throwable) {
            Logger.error("ResponseService.getAllResponses error: " + throwable.getMessage());
            throwable.printStackTrace();
        }
        return null;
    }

    public Integer getResponseCount() {
        try {
            return JPA.withTransaction(new F.Function0<Integer>() {
                @Override
                public Integer apply() throws Throwable {
                    return responseDao.getResponseCount();
                }
            });
        } catch (Throwable throwable) {
            Logger.error("ResponseService.getResponseCount error " + throwable.getMessage());
            throwable.printStackTrace();
        }
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        numberResponses = getResponseCount();
    }
}
