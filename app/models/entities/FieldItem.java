package models.entities;

import models.Type;

import javax.persistence.*;
import java.io.Serializable;


/**
 * Created by Maryna Pashkouskaya on 2015-12-11.
 */
@Entity
@Table(name = "field_item")
public class FieldItem implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name="field_id")
    private Field field;

    @Column(name="item")
    private String item;

    public Integer getId() {
        return id;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FieldItem fieldItem = (FieldItem) o;

        if (!field.equals(fieldItem.field)) return false;
        return item.equals(fieldItem.item);

    }

    @Override
    public int hashCode() {
        int result = field.hashCode();
        result = 31 * result + item.hashCode();
        return result;
    }
}
