package dao;

import models.entities.FieldValue;

import java.util.List;

/**
 * Created by Maryna Pashkouskaya on 2015-12-18.
 */
public interface FieldValueDao {

    void createFieldValue(FieldValue value);
    List<FieldValue> getAllValues();

}
