package io.worthi.feedScreen.fragments.profile.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SendFeedbackResponse {

@SerializedName("statusCode")
@Expose
private Integer statusCode;
@SerializedName("body")
@Expose
private String body;
@SerializedName("headers")
@Expose
private Headers headers;

public Integer getStatusCode() {
return statusCode;
}

public void setStatusCode(Integer statusCode) {
this.statusCode = statusCode;
}

public String getBody() {
return body;
}

public void setBody(String body) {
this.body = body;
}

public Headers getHeaders() {
return headers;
}

public void setHeaders(Headers headers) {
this.headers = headers;
}

    public class Headers {

        @SerializedName("server")
        @Expose
        private String server;
        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("content-length")
        @Expose
        private String contentLength;
        @SerializedName("connection")
        @Expose
        private String connection;
        @SerializedName("x-message-id")
        @Expose
        private String xMessageId;
        @SerializedName("access-control-allow-origin")
        @Expose
        private String accessControlAllowOrigin;
        @SerializedName("access-control-allow-methods")
        @Expose
        private String accessControlAllowMethods;
        @SerializedName("access-control-allow-headers")
        @Expose
        private String accessControlAllowHeaders;
        @SerializedName("access-control-max-age")
        @Expose
        private String accessControlMaxAge;
        @SerializedName("x-no-cors-reason")
        @Expose
        private String xNoCorsReason;
        @SerializedName("strict-transport-security")
        @Expose
        private String strictTransportSecurity;

        public String getServer() {
            return server;
        }

        public void setServer(String server) {
            this.server = server;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getContentLength() {
            return contentLength;
        }

        public void setContentLength(String contentLength) {
            this.contentLength = contentLength;
        }

        public String getConnection() {
            return connection;
        }

        public void setConnection(String connection) {
            this.connection = connection;
        }

        public String getxMessageId() {
            return xMessageId;
        }

        public void setxMessageId(String xMessageId) {
            this.xMessageId = xMessageId;
        }

        public String getAccessControlAllowOrigin() {
            return accessControlAllowOrigin;
        }

        public void setAccessControlAllowOrigin(String accessControlAllowOrigin) {
            this.accessControlAllowOrigin = accessControlAllowOrigin;
        }

        public String getAccessControlAllowMethods() {
            return accessControlAllowMethods;
        }

        public void setAccessControlAllowMethods(String accessControlAllowMethods) {
            this.accessControlAllowMethods = accessControlAllowMethods;
        }

        public String getAccessControlAllowHeaders() {
            return accessControlAllowHeaders;
        }

        public void setAccessControlAllowHeaders(String accessControlAllowHeaders) {
            this.accessControlAllowHeaders = accessControlAllowHeaders;
        }

        public String getAccessControlMaxAge() {
            return accessControlMaxAge;
        }

        public void setAccessControlMaxAge(String accessControlMaxAge) {
            this.accessControlMaxAge = accessControlMaxAge;
        }

        public String getxNoCorsReason() {
            return xNoCorsReason;
        }

        public void setxNoCorsReason(String xNoCorsReason) {
            this.xNoCorsReason = xNoCorsReason;
        }

        public String getStrictTransportSecurity() {
            return strictTransportSecurity;
        }

        public void setStrictTransportSecurity(String strictTransportSecurity) {
            this.strictTransportSecurity = strictTransportSecurity;
        }

    }
}