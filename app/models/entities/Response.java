package models.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * Created by Maryna Pashkouskaya on 2015-12-11.
 */
@Entity
@Table(name = "response")
public class Response implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "uuid")
    private String uuid;

    @OneToMany(mappedBy = "id.responseId", cascade = CascadeType.ALL)
    private List<FieldValue> fieldValueList;

    public Integer getId() {
        return id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<FieldValue> getFieldValueList() {
        return fieldValueList;
    }

    public void setFieldValueList(List<FieldValue> fieldValueList) {
        this.fieldValueList = fieldValueList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Response response = (Response) o;

        if (!uuid.equals(response.uuid)) return false;
        return !(fieldValueList != null ? !fieldValueList.equals(response.fieldValueList) : response.fieldValueList != null);

    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + (fieldValueList != null ? fieldValueList.hashCode() : 0);
        return result;
    }
}
