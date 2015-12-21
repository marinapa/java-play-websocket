package dao.impl;

import dao.FieldDao;
import dao.FieldValueDao;
import models.entities.FieldValue;
import play.db.jpa.JPA;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by Maryna Pashkouskaya on 2015-12-18.
 */
public class FieldValueDaoImpl implements FieldValueDao {

    @Override
    public void createFieldValue(FieldValue value) {
        JPA.em().persist(value);
    }

    public static final String SQL_GET_ALL_VALUES = "from FieldValue";

    @SuppressWarnings("unchecked")
    @Override
    public List<FieldValue> getAllValues() {
        return  JPA.em().createQuery(SQL_GET_ALL_VALUES).getResultList();
    }

}
