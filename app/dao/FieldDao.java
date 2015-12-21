package dao;

import models.entities.Field;

import java.util.List;

/**
 * Created by Maryna Pashkouskaya on 2015-12-11.
 */
public interface FieldDao {

    Field createField(Field field);
    Field editField(Field field);
    Field getFieldById(Integer id);
    List<Field> getAllFields();
    void deleteField(Integer fieldId);

}
