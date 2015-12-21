package models.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Maryna Pashkouskaya on 2015-12-11.
 */
@Entity
@Table(name = "field_value")
public class FieldValue implements Serializable {

    @EmbeddedId
    private FieldValueId id;

    @Column(name = "value", columnDefinition = "varchar(255)")
    private String value;

    public FieldValueId getId() {
        return id;
    }

    public void setId(FieldValueId id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FieldValue that = (FieldValue) o;

        if (!id.equals(that.id)) return false;
        return !(value != null ? !value.equals(that.value) : that.value != null);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
