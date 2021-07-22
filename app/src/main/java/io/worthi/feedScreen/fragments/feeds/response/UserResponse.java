package io.worthi.feedScreen.fragments.feeds.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponse {

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
@SerializedName("balance")
@Expose
private Double balance;
@SerializedName("totalEarnedBalance")
@Expose
private Double totalEarnedBalance;
@SerializedName("interactedFeedsCount")
@Expose
private String interactedFeedsCount;

    public Boolean getVerified() {
        return isVerified;
    }

    public void setVerified(Boolean verified) {
        isVerified = verified;
    }

    public Double getTotalEarnedBalance() {
        return totalEarnedBalance;
    }

    public void setTotalEarnedBalance(Double totalEarnedBalance) {
        this.totalEarnedBalance = totalEarnedBalance;
    }

    public String getInteractedFeedsCount() {
        return interactedFeedsCount;
    }

    public void setInteractedFeedsCount(String interactedFeedsCount) {
        this.interactedFeedsCount = interactedFeedsCount;
    }

    @SerializedName("created_at")
@Expose
private String createdAt;
@SerializedName("updated_at")
@Expose
private String updatedAt;
@SerializedName("deleted_at")
@Expose
private Object deletedAt;
@SerializedName("role")
@Expose
private Role role;
@SerializedName("company")
@Expose
private Object company;
@SerializedName("campaign")
@Expose
private List<Object> campaign = null;
@SerializedName("interests")
@Expose
private Interests interests;
@SerializedName("profile")
@Expose
private Profile profile;

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

public Double getBalance() {
return balance;
}

public void setBalance(Double balance) {
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

public Role getRole() {
return role;
}

public void setRole(Role role) {
this.role = role;
}

public Object getCompany() {
return company;
}

public void setCompany(Object company) {
this.company = company;
}

public List<Object> getCampaign() {
return campaign;
}

public void setCampaign(List<Object> campaign) {
this.campaign = campaign;
}

public Interests getInterests() {
return interests;
}

public void setInterests(Interests interests) {
this.interests = interests;
}

public Profile getProfile() {
return profile;
}

public void setProfile(Profile profile) {
this.profile = profile;
}

    public class Role {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;

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

    }

    public class Profile {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("age")
        @Expose
        private String age;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("location")
        @Expose
        private String location;
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

    public class Interests {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("interests")
        @Expose
        private List<String> interests = null;
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

        public List<String> getInterests() {
            return interests;
        }

        public void setInterests(List<String> interests) {
            this.interests = interests;
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