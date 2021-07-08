package io.worthi.VerifyEmail.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerifyResponse {

@SerializedName("status")
@Expose
private String status;
@SerializedName("valid")
@Expose
private Boolean valid;

public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

public Boolean getValid() {
return valid;
}

public void setValid(Boolean valid) {
this.valid = valid;
}

}