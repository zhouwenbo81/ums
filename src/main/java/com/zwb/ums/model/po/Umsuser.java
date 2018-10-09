package com.zwb.ums.model.po;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * <p>
 * Title: Relation
 * </p>
 * <p>
 * Description: 用户实体
 * </p>
 * <p>
 * Company: 都市放牛集团
 * </p>
 *
 * @author zhouwenbo
 * @date 2017年6月7日 下午4:07:11
 * @version 1.0
 */
public class Umsuser implements Serializable{

    /** 序列号 */
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 用户编号 */
    private String userNum;

    /** 人员类型（01普通用户 02会员 03内部用户  04管理员 05网厅用户 06哈尔滨用户） */
    private String userType;

    /** 用户名 */
    private String username;

    /** 手机号 */
    private String mobile;

    /** 邮箱 */
    private String email;

    /** 昵称 */
    private String nickname;

    /** 登录密码 */
    private String password;

    /** 动态密匙 */
    private String secretKey;

    /** 创建时间 */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    /** 创建时IP */
    private String createIp;

    /** 状态 00正常，01冻结，03待定 */
    private String status;

    /** 是否验证了手机号码，Y，已经验证，N未验证，默认N */
    private String isPhone;

    /** 是否验证了邮箱，Y已验证,N未验证，默认N */
    private String isEmail;

    /** 是否开启指纹登录，Y已开启，N未开启，默认N */
    private String isFingerprint;

    /** 用户来源 */
    private Integer reisteredApp;

    private String fingerprintLogin;

    /** 最近登录时间 */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date lastLogintime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum == null ? null : userNum.trim();
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey == null ? null : secretKey.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateIp() {
        return createIp;
    }

    public void setCreateIp(String createIp) {
        this.createIp = createIp == null ? null : createIp.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getIsPhone() {
        return isPhone;
    }

    public void setIsPhone(String isPhone) {
        this.isPhone = isPhone == null ? null : isPhone.trim();
    }

    public String getIsEmail() {
        return isEmail;
    }

    public void setIsEmail(String isEmail) {
        this.isEmail = isEmail == null ? null : isEmail.trim();
    }

    public String getIsFingerprint() {
        return isFingerprint;
    }

    public void setIsFingerprint(String isFingerprint) {
        this.isFingerprint = isFingerprint == null ? null : isFingerprint.trim();
    }

    public Integer getReisteredApp() {
        return reisteredApp;
    }

    public void setReisteredApp(Integer reisteredApp) {
        this.reisteredApp = reisteredApp;
    }

    public String getFingerprintLogin() {
        return fingerprintLogin;
    }

    public void setFingerprintLogin(String fingerprintLogin) {
        this.fingerprintLogin = fingerprintLogin == null ? null : fingerprintLogin.trim();
    }

    public Date getLastLogintime() {
        return lastLogintime;
    }

    public void setLastLogintime(Date lastLogintime) {
        this.lastLogintime = lastLogintime;
    }
}