package dao;

import models.entities.FieldItem;

import java.util.List;

/**
 * Created by Maryna Pashkouskaya on 2015-12-19.
 */
public interface FieldItemDao {

    FieldItem createFieldItem(FieldItem fieldItem);
    FieldItem editFieldItem(FieldItem fieldItem);
    List<FieldItem> getFieldItemsByFieldId(Integer fieldId);
    void deleteFieldItemByFieldId(Integer fieldId);

}
