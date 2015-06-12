package shitou.wechat.core.model;

import org.springframework.stereotype.Component;

import java.util.Date;
import javax.persistence.*;
import java.io.Serializable;


/**
 * Created in Intellij IDEA 13 Ultimate
 * User: shitou
 * Date: 2014/10/29
 * Time: 00:05
 */
@Entity
@Component
@Table(name = "XH_FOLLOWER")
public class UserModel implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_sex")
    private int userSex;
    @Column(name = "public_id")
    private String publicId;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "user_city")
    private String userCity;
    @Column(name = "user_remark")
    private String userRemark;
    @Column(name = "user_country")
    private String userCountry;
    @Column(name = "user_union_id")
    private String userUnionId;
    @Column(name = "user_group_id")
    private String userGroupId;
    @Column(name = "user_language")
    private String userLanguage;
    @Column(name = "user_province")
    private String userProvince;
    @Column(name = "user_avaliable")
    private boolean userAvailable;
    @Column(name = "user_head_image_url")
    private String userHeadImageUrl;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserSex() {
        return userSex;
    }

    public void setUserSex(int userSex) {
        this.userSex = userSex;
    }

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public String getUserRemark() {
        return userRemark;
    }

    public void setUserRemark(String userRemark) {
        this.userRemark = userRemark;
    }

    public String getUserCountry() {
        return userCountry;
    }

    public void setUserCountry(String userCountry) {
        this.userCountry = userCountry;
    }

    public String getUserUnionId() {
        return userUnionId;
    }

    public void setUserUnionId(String userUnionId) {
        this.userUnionId = userUnionId;
    }

    public String getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(String userGroupId) {
        this.userGroupId = userGroupId;
    }

    public String getUserLanguage() {
        return userLanguage;
    }

    public void setUserLanguage(String userLanguage) {
        this.userLanguage = userLanguage;
    }

    public String getUserProvince() {
        return userProvince;
    }

    public void setUserProvince(String userProvince) {
        this.userProvince = userProvince;
    }

    public boolean isUserAvailable() {
        return userAvailable;
    }

    public void setUserAvailable(boolean userAvailable) {
        this.userAvailable = userAvailable;
    }

    public String getUserHeadImageUrl() {
        return userHeadImageUrl;
    }

    public void setUserHeadImageUrl(String userHeadImageUrl) {
        this.userHeadImageUrl = userHeadImageUrl;
    }
}
