package dao.impl;

import dao.FieldDao;
import models.entities.Field;
import play.db.jpa.JPA;

import javax.persistence.EntityManager;
import java.util.List;


/**
 * Created by Maryna Pashkouskaya on 2015-12-11.
 */
public class FieldDaoImpl implements FieldDao {

    @Override
    public Field createField(Field field) {
        EntityManager em = JPA.em();
        em.persist(field);
        em.flush();
        return field;
    }

    public static final String SQL_GET_FIELD_BY_ID = "from Field where id = :id";

    @Override
    public Field getFieldById(Integer id) {
        return (Field) JPA.em().createQuery(SQL_GET_FIELD_BY_ID).setParameter("id", id).getSingleResult();
    }

    public static final String SQL_GET_ALL_FIELDS = "from Field";

    @Override
    @SuppressWarnings("unchecked")
    public List<Field> getAllFields() {
        return JPA.em().createQuery(SQL_GET_ALL_FIELDS).getResultList();
    }

    @Override
    public Field editField(Field field) {
        return JPA.em().merge(field);
    }

    public static final String SQL_DELETE_FIELD = "delete from Field where id = :id";
    @Override
    public void deleteField(Integer fieldId) {
        JPA.em().createQuery(SQL_DELETE_FIELD).setParameter("id", fieldId).executeUpdate();
    }

}
