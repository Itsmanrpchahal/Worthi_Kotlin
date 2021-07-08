package io.worthi.chooseInterest.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetInterestsResponse {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("name")
@Expose
private String name;
@SerializedName("value")
@Expose
private String value;
@SerializedName("created_at")
@Expose
private String createdAt;
@SerializedName("updated_at")
@Expose
private String updatedAt;
@SerializedName("deleted_at")
@Expose
private Object deletedAt;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getValue() {
return value;
}

public void setValue(String value) {
this.value = value;
}

public String getCreatedAt() {
return createdAt;
}

public void setCreatedAt(String createdAt) {
this.createdAt = createdAt;
}

public String getUpdatedAt() {
return updatedAt;
}

public void setUpdatedAt(String updatedAt) {
this.updatedAt = updatedAt;
}

public Object getDeletedAt() {
return deletedAt;
}

public void setDeletedAt(Object deletedAt) {
this.deletedAt = deletedAt;
}

}