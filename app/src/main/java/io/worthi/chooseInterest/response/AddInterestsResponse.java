package io.worthi.chooseInterest.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddInterestsResponse {

@SerializedName("interests")
@Expose
private List<String> interests = null;
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

public List<String> getInterests() {
return interests;
}

public void setInterests(List<String> interests) {
this.interests = interests;
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