package models.entities;

import models.Type;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Maryna Pashkouskaya on 2015-12-11.
 */
@Entity
@Table(name = "field")
public class Field implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name="label")
    private String label;

    @Column(name="type")
    private Type type;

    @Column(name = "required")
    private boolean required;

    @Column(name = "is_alive")
    private boolean alive;

    @OneToMany(mappedBy = "field", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<FieldItem> fieldItems;

    @Transient
    private String fieldItemString;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public List<FieldItem> getFieldItems() {
        return fieldItems;
    }

    public void setFieldItems(List<FieldItem> fieldItems) {
        this.fieldItems = fieldItems;
    }

    public String getFieldItemString() {
        return fieldItemString;
    }

    public void setFieldItemString(String fieldItemString) {
        this.fieldItemString = fieldItemString;
    }

    public Map<String, String> itemMap(){
        Map<String, String> values = new HashMap<>();
        for (FieldItem fieldItem : fieldItems) {
            values.put(fieldItem.getItem() + "_" + fieldItem.getId().toString(), fieldItem.getItem());
        }
        return values;
    }

    public Map<String, String> radioItemMap(){
        Map<String, String> values = new HashMap<>();
        for (FieldItem fieldItem : fieldItems) {
            values.put("_" + fieldItem.getId().toString(), fieldItem.getItem());
        }
        return values;
    }

    public String getItemsString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fieldItems.size(); i++) {
            sb.append(fieldItems.get(i));
            if (i != fieldItems.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Field field = (Field) o;

        if (required != field.required) return false;
        if (alive != field.alive) return false;
        if (!label.equals(field.label)) return false;
        if (type != field.type) return false;
        return !(fieldItems != null ? !fieldItems.equals(field.fieldItems) : field.fieldItems != null);

    }

    @Override
    public int hashCode() {
        int result = label.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + (required ? 1 : 0);
        result = 31 * result + (alive ? 1 : 0);
        result = 31 * result + (fieldItems != null ? fieldItems.hashCode() : 0);
        return result;
    }
}
