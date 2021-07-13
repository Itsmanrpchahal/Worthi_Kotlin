package io.worthi.submitQualifier.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AnswersResponse {

@SerializedName("campaign")
@Expose
private Campaign campaign;
@SerializedName("user")
@Expose
private User user;
@SerializedName("is_passed")
@Expose
private Boolean isPassed;
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

public Campaign getCampaign() {
return campaign;
}

public void setCampaign(Campaign campaign) {
this.campaign = campaign;
}

public User getUser() {
return user;
}

public void setUser(User user) {
this.user = user;
}

public Boolean getIsPassed() {
return isPassed;
}

public void setIsPassed(Boolean isPassed) {
this.isPassed = isPassed;
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

    public class Campaign {

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