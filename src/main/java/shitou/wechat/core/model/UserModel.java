package shitou.wechat.core.model;

import java.io.Serializable;
import java.sql.Timestamp;

import org.springframework.stereotype.Component;


/**
 * Created in Intellij IDEA 13 Ultimate
 * User: shitou
 * Date: 2014/10/29
 * Time: 00:05
 */

@Component
public class UserModel implements Serializable{
    private Long id;
    private String name;
    private String age;
    private String city;
    private String email;
    private String openId;
    private String gender;
    private String mobilePhone;
    private Timestamp subscribeTime;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public Timestamp getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(Timestamp subscribeTime) {
        this.subscribeTime = subscribeTime;
    }
}
