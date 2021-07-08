package io.worthi.yourInfo.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class YourInfoResponse {

@SerializedName("age")
@Expose
private String age;
@SerializedName("gender")
@Expose
private String gender;
@SerializedName("location")
@Expose
private String location;
@SerializedName("user")
@Expose
private User user;
@SerializedName("deleted_at")
@Expose
private Object deletedAt;
@SerializedName("id")
@Expose
private Integer id;
@SerializedName("created_at")
@Expose
private String createdAt;
@SerializedName("updated_at")
@Expose
private String updatedAt;

public String getAge() {
return age;
}

public void setAge(String age) {
this.age = age;
}

public String getGender() {
return gender;
}

public void setGender(String gender) {
this.gender = gender;
}

public String getLocation() {
return location;
}

public void setLocation(String location) {
this.location = location;
}

public User getUser() {
return user;
}

public void setUser(User user) {
this.user = user;
}

public Object getDeletedAt() {
return deletedAt;
}

public void setDeletedAt(Object deletedAt) {
this.deletedAt = deletedAt;
}

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
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

    public class User {

        @SerializedName("id")
        @Expose
        private Integer id;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

    }
}