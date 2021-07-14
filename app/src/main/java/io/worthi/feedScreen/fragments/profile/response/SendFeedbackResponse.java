package io.worthi.feedScreen.fragments.profile.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SendFeedbackResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

}