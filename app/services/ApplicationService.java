package services;

import dao.FieldValueDao;
import dao.ResponseDao;
import models.entities.FieldValue;
import models.entities.FieldValueId;
import models.entities.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import play.db.jpa.JPA;
import play.libs.F;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Maryna Pashkouskaya on 2015-12-18.
 */
@Service
public class ApplicationService {

    @Autowired
    private ResponseDao responseDao;

    @Autowired
    private FieldValueDao fieldValueDao;

    public Map<String, String> createResponseWithValues(Response response, Map<String, Object> values) {
        Map<String, String> resultMap = new HashMap<>();
        try {
            JPA.withTransaction(new F.Function0<Map<String, String>>() {
                @Override
                public Map<String, String> apply() throws Throwable {
                    Response r = responseDao.createResponse(response);
                    Integer resId = r.getId();
                    for (Map.Entry<String, Object> entry : values.entrySet())
                    {
                        FieldValue fv = new FieldValue();
                        fv.setId(new FieldValueId(resId, Integer.valueOf(entry.getKey())));
                        if (entry.getValue() instanceof Boolean) {
                            fv.setValue((Boolean)entry.getValue() ? "yes" : "no");
                        }
                        else  fv.setValue(entry.getValue().toString());
                        resultMap.put(entry.getKey(), fv.getValue());
                        fieldValueDao.createFieldValue(fv);
                    }
                    return resultMap;
                }
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return resultMap;
    }
}
