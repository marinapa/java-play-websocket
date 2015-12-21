package services;

import dao.FieldValueDao;
import models.entities.FieldValue;
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
public class FieldValueService {
    @Autowired
    private FieldValueDao fieldValueDao;

    public void createFieldValue(FieldValue value) {
        JPA.withTransaction(new F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                fieldValueDao.createFieldValue(value);
            }
        });
    }

    public List<FieldValue> getAllValues() {
        try {
            return JPA.withTransaction(new F.Function0<List<FieldValue>>() {
                @Override
                public List<FieldValue> apply() throws Throwable {
                    return fieldValueDao.getAllValues();
                }
            });
        } catch (Throwable throwable) {
            Logger.error("ResponseService.getAllValues error: " + throwable.getMessage());
            throwable.printStackTrace();
        }
        return null;
    }

}
