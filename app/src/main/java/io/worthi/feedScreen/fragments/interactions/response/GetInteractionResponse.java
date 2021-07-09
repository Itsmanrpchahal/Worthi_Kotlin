package io.worthi.feedScreen.fragments.interactions.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetInteractionResponse {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("name")
@Expose
private String name;
@SerializedName("description")
@Expose
private String description;
@SerializedName("reach")
@Expose
private Integer reach;
@SerializedName("isPublished")
@Expose
private Boolean isPublished;
@SerializedName("isPaymentDone")
@Expose
private Boolean isPaymentDone;
@SerializedName("is_interacted")
@Expose
private Boolean isInteracted;
@SerializedName("created_at")
@Expose
private String createdAt;
@SerializedName("updated_at")
@Expose
private String updatedAt;
@SerializedName("deleted_at")
@Expose
private Object deletedAt;
@SerializedName("add_display")
@Expose
private AddDisplay addDisplay;

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

public String getDescription() {
return description;
}

public void setDescription(String description) {
this.description = description;
}

public Integer getReach() {
return reach;
}

public void setReach(Integer reach) {
this.reach = reach;
}

public Boolean getIsPublished() {
return isPublished;
}

public void setIsPublished(Boolean isPublished) {
this.isPublished = isPublished;
}

public Boolean getIsPaymentDone() {
return isPaymentDone;
}

public void setIsPaymentDone(Boolean isPaymentDone) {
this.isPaymentDone = isPaymentDone;
}

public Boolean getIsInteracted() {
return isInteracted;
}

public void setIsInteracted(Boolean isInteracted) {
this.isInteracted = isInteracted;
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

public AddDisplay getAddDisplay() {
return addDisplay;
}

public void setAddDisplay(AddDisplay addDisplay) {
this.addDisplay = addDisplay;
}

    public class AddDisplay {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("add_display_header")
        @Expose
        private String addDisplayHeader;
        @SerializedName("add_display_description")
        @Expose
        private String addDisplayDescription;
        @SerializedName("add_display_image")
        @Expose
        private String addDisplayImage;
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

        public String getAddDisplayHeader() {
            return addDisplayHeader;
        }

        public void setAddDisplayHeader(String addDisplayHeader) {
            this.addDisplayHeader = addDisplayHeader;
        }

        public String getAddDisplayDescription() {
            return addDisplayDescription;
        }

        public void setAddDisplayDescription(String addDisplayDescription) {
            this.addDisplayDescription = addDisplayDescription;
        }

        public String getAddDisplayImage() {
            return addDisplayImage;
        }

        public void setAddDisplayImage(String addDisplayImage) {
            this.addDisplayImage = addDisplayImage;
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
}