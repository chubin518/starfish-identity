package org.starfish.identity.entity;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "identity_user")
public class IdentityUser {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 租户ID
     */
    @Column(name = "tenant_id")
    private Integer tenantId;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String name;

    /**
     * 盐值
     */
    private String salt;

    /**
     * 真名
     */
    @Column(name = "real_name")
    private String realName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机
     */
    private String phone;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 性别
     */
    private Byte sex;

    /**
     * 部门id
     */
    @Column(name = "dept_id")
    private String deptId;

    /**
     * 创建人
     */
    @Column(name = "create_user")
    private Integer createUser;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private LocalDateTime createTime;

    /**
     * 修改人
     */
    @Column(name = "update_user")
    private Integer updateUser;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    /**
     * 是否锁定：0-否；1-是
     */
    @Column(name = "is_locked")
    private Byte isLocked;

    /**
     * 是否删除：0-否；1-是
     */
    @Column(name = "is_deleted")
    private Byte isDeleted;

    /**
     * 锁定时间
     */
    @Column(name = "lock_time")
    private LocalDateTime lockTime;

    /**
     * 最后登录时间
     */
    @Column(name = "login_time")
    private LocalDateTime loginTime;

    /**
     * 最后登录地址
     */
    @Column(name = "login_address")
    private String loginAddress;

    public static final String ID = "id";

    public static final String DB_ID = "id";

    public static final String TENANT_ID = "tenantId";

    public static final String DB_TENANT_ID = "tenant_id";

    public static final String ACCOUNT = "account";

    public static final String DB_ACCOUNT = "account";

    public static final String PASSWORD = "password";

    public static final String DB_PASSWORD = "password";

    public static final String NAME = "name";

    public static final String DB_NAME = "name";

    public static final String SALT = "salt";

    public static final String DB_SALT = "salt";

    public static final String REAL_NAME = "realName";

    public static final String DB_REAL_NAME = "real_name";

    public static final String EMAIL = "email";

    public static final String DB_EMAIL = "email";

    public static final String PHONE = "phone";

    public static final String DB_PHONE = "phone";

    public static final String BIRTHDAY = "birthday";

    public static final String DB_BIRTHDAY = "birthday";

    public static final String SEX = "sex";

    public static final String DB_SEX = "sex";

    public static final String DEPT_ID = "deptId";

    public static final String DB_DEPT_ID = "dept_id";

    public static final String CREATE_USER = "createUser";

    public static final String DB_CREATE_USER = "create_user";

    public static final String CREATE_TIME = "createTime";

    public static final String DB_CREATE_TIME = "create_time";

    public static final String UPDATE_USER = "updateUser";

    public static final String DB_UPDATE_USER = "update_user";

    public static final String UPDATE_TIME = "updateTime";

    public static final String DB_UPDATE_TIME = "update_time";

    public static final String IS_LOCKED = "isLocked";

    public static final String DB_IS_LOCKED = "is_locked";

    public static final String IS_DELETED = "isDeleted";

    public static final String DB_IS_DELETED = "is_deleted";

    public static final String LOCK_TIME = "lockTime";

    public static final String DB_LOCK_TIME = "lock_time";

    public static final String LOGIN_TIME = "loginTime";

    public static final String DB_LOGIN_TIME = "login_time";

    public static final String LOGIN_ADDRESS = "loginAddress";

    public static final String DB_LOGIN_ADDRESS = "login_address";
}