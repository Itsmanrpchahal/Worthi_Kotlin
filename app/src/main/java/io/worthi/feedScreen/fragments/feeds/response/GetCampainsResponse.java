package io.worthi.feedScreen.fragments.feeds.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetCampainsResponse {

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
@SerializedName("user")
@Expose
private User user;
@SerializedName("audience")
@Expose
private Audience audience;
@SerializedName("questions")
@Expose
private List<Question> questions = null;
@SerializedName("add_display")
@Expose
private AddDisplay addDisplay;
@SerializedName("ad_goal")
@Expose
private AdGoal adGoal;
@SerializedName("call_to_action")
@Expose
private CallToAction callToAction;

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

public User getUser() {
return user;
}

public void setUser(User user) {
this.user = user;
}

public Audience getAudience() {
return audience;
}

public void setAudience(Audience audience) {
this.audience = audience;
}

public List<Question> getQuestions() {
return questions;
}

public void setQuestions(List<Question> questions) {
this.questions = questions;
}

public AddDisplay getAddDisplay() {
return addDisplay;
}

public void setAddDisplay(AddDisplay addDisplay) {
this.addDisplay = addDisplay;
}

public AdGoal getAdGoal() {
return adGoal;
}

public void setAdGoal(AdGoal adGoal) {
this.adGoal = adGoal;
}

public CallToAction getCallToAction() {
return callToAction;
}

public void setCallToAction(CallToAction callToAction) {
this.callToAction = callToAction;
}

    public class User {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("phone_number")
        @Expose
        private String phoneNumber;
        @SerializedName("isVerified")
        @Expose
        private Boolean isVerified;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("balance")
        @Expose
        private Integer balance;
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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public Boolean getIsVerified() {
            return isVerified;
        }

        public void setIsVerified(Boolean isVerified) {
            this.isVerified = isVerified;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Integer getBalance() {
            return balance;
        }

        public void setBalance(Integer balance) {
            this.balance = balance;
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

    public class Audience {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("audience_age")
        @Expose
        private Integer audienceAge;
        @SerializedName("audience_gender")
        @Expose
        private String audienceGender;
        @SerializedName("audience_interest")
        @Expose
        private String audienceInterest;
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

        public Integer getAudienceAge() {
            return audienceAge;
        }

        public void setAudienceAge(Integer audienceAge) {
            this.audienceAge = audienceAge;
        }

        public String getAudienceGender() {
            return audienceGender;
        }

        public void setAudienceGender(String audienceGender) {
            this.audienceGender = audienceGender;
        }

        public String getAudienceInterest() {
            return audienceInterest;
        }

        public void setAudienceInterest(String audienceInterest) {
            this.audienceInterest = audienceInterest;
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

    public class Question {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("qualifier_question")
        @Expose
        private String qualifierQuestion;
        @SerializedName("qualifier_answer")
        @Expose
        private String qualifierAnswer;
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

        public String getQualifierQuestion() {
            return qualifierQuestion;
        }

        public void setQualifierQuestion(String qualifierQuestion) {
            this.qualifierQuestion = qualifierQuestion;
        }

        public String getQualifierAnswer() {
            return qualifierAnswer;
        }

        public void setQualifierAnswer(String qualifierAnswer) {
            this.qualifierAnswer = qualifierAnswer;
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

    public class AdGoal {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("ad_goal")
        @Expose
        private String adGoal;
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

        public String getAdGoal() {
            return adGoal;
        }

        public void setAdGoal(String adGoal) {
            this.adGoal = adGoal;
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

    public class CallToAction {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("call_to_action_image")
        @Expose
        private String callToActionImage;
        @SerializedName("proposition_value")
        @Expose
        private String propositionValue;
        @SerializedName("button_title")
        @Expose
        private String buttonTitle;
        @SerializedName("button_link")
        @Expose
        private String buttonLink;
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

        public String getCallToActionImage() {
            return callToActionImage;
        }

        public void setCallToActionImage(String callToActionImage) {
            this.callToActionImage = callToActionImage;
        }

        public String getPropositionValue() {
            return propositionValue;
        }

        public void setPropositionValue(String propositionValue) {
            this.propositionValue = propositionValue;
        }

        public String getButtonTitle() {
            return buttonTitle;
        }

        public void setButtonTitle(String buttonTitle) {
            this.buttonTitle = buttonTitle;
        }

        public String getButtonLink() {
            return buttonLink;
        }

        public void setButtonLink(String buttonLink) {
            this.buttonLink = buttonLink;
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