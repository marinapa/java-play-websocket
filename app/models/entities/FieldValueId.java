package models.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Maryna Pashkouskaya on 2015-12-11.
 */
@Embeddable
public class FieldValueId implements Serializable {

    @Column(name="response_id")
    private Integer responseId;

    @Column(name="field_id")
    private Integer fieldId;

    public FieldValueId() {
    }

    public FieldValueId(Integer responseId, Integer fieldId) {
        this.responseId = responseId;
        this.fieldId = fieldId;
    }

    public Integer getResponseId() {
        return responseId;
    }

    public Integer getFieldId() {
        return fieldId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FieldValueId that = (FieldValueId) o;

        if (!responseId.equals(that.responseId)) return false;
        return fieldId.equals(that.fieldId);

    }

    @Override
    public int hashCode() {
        int result = responseId.hashCode();
        result = 31 * result + fieldId.hashCode();
        return result;
    }
}
