package dao.impl;

import dao.FieldItemDao;
import models.entities.FieldItem;
import play.db.jpa.JPA;

import java.util.List;

/**
 * Created by Maryna Pashkouskaya on 2015-12-19.
 */
public class FieldItemDaoImpl implements FieldItemDao {

    @Override
    public FieldItem createFieldItem(FieldItem fieldItem) {
        JPA.em().persist(fieldItem);
        JPA.em().flush();
        return fieldItem;
    }

    @Override
    public FieldItem editFieldItem(FieldItem fieldItem) {
        JPA.em().merge(fieldItem);
        JPA.em().flush();
        return fieldItem;
    }

    public static final String SQL_GET_FIELD_ITEMS_BY_FIELD_ID = "from FieldItem where field_id = :fieldId";

    @SuppressWarnings("unchecked")
    @Override
    public List<FieldItem> getFieldItemsByFieldId(Integer fieldId) {
        return JPA.em().createQuery(SQL_GET_FIELD_ITEMS_BY_FIELD_ID).setParameter("fieldId", fieldId).getResultList();
    }

    public static final String SQL_DELETE_FIELD_ITEMS_BY_FIELD_ID = "delete from FieldItem where field_id = :fieldId";

    @Override
    public void deleteFieldItemByFieldId(Integer fieldId) {
        JPA.em().createQuery(SQL_DELETE_FIELD_ITEMS_BY_FIELD_ID).setParameter("fieldId", fieldId).executeUpdate();
    }
}
