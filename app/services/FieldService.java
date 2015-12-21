package services;

import dao.FieldDao;
import dao.FieldItemDao;
import models.Type;
import models.entities.Field;
import models.entities.FieldItem;
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
public class FieldService {

    @Autowired
    private FieldDao fieldDao;

    @Autowired
    private FieldItemDao fieldItemDao;

    public Field createField(Field field) {
        try {
            return JPA.withTransaction(new F.Function0<Field>() {
                @Override
                public Field apply() throws Throwable {
                    fieldDao.createField(field);
                    for (FieldItem fi : field.getFieldItems()) {
                        fi.setField(field);
                        fieldItemDao.createFieldItem(fi);
                    }
                    return field;
                }
            });
        } catch (Throwable throwable) {
            Logger.error("ApplicationService.createField error:  " + throwable.getMessage());
            throwable.printStackTrace();
        }
        return null;
    }

    public Field editField(Field field) {
        try {
            return JPA.withTransaction(new F.Function0<Field>() {
                @Override
                public Field apply() throws Throwable {
                    fieldDao.editField(field);
                        fieldItemDao.deleteFieldItemByFieldId(field.getId());
                        for (FieldItem fi : field.getFieldItems()) {
                            fi.setField(field);
                            fieldItemDao.createFieldItem(fi);
                        }
                    return field;
                }
            });
        } catch (Throwable throwable) {
            Logger.error("ApplicationService.editField error:  " + throwable.getMessage());
            throwable.printStackTrace();
        }
        return null;
    }

    public Field getFieldById(Integer id) {
        try {
            return JPA.withTransaction(new F.Function0<Field>() {
                @Override
                public Field apply() throws Throwable {
                    return fieldDao.getFieldById(id);
                }
            });
        } catch (Throwable throwable) {
            Logger.error("ApplicationService.getFieldById error:  " + throwable.getMessage());
            throwable.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<Field> getAllFields() {
        try {
            return (List<Field>)JPA.withTransaction((F.Function0) fieldDao::getAllFields);
        } catch (Throwable throwable) {
            Logger.error("ApplicationService.getAllFields error:  " + throwable.getMessage());
            throwable.printStackTrace();
        }
        return null;
    }

    public void deleteField(Integer fieldId) {
        JPA.withTransaction(new F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                fieldDao.deleteField(fieldId);
            }
        });
    }
}
