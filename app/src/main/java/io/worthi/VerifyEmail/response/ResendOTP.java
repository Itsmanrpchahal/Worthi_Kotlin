package io.worthi.VerifyEmail.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResendOTP {

@SerializedName("message")
@Expose
private Boolean message;
@SerializedName("status")
@Expose
private String status;
@SerializedName("to")
@Expose
private String to;

public Boolean getMessage() {
return message;
}

public void setMessage(Boolean message) {
this.message = message;
}

public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

public String getTo() {
return to;
}

public void setTo(String to) {
this.to = to;
}

}