package org.starfish.identity.entity;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "identity_client")
public class IdentityClient {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 客户端id
     */
    @Column(name = "client_id")
    private String clientId;

    /**
     * 客户端密钥
     */
    @Column(name = "client_secret")
    private String clientSecret;

    /**
     * 授权范围
     */
    private String scope;

    /**
     * 授权类型
     */
    @Column(name = "grant_types")
    private String grantTypes;

    /**
     * 回调地址
     */
    @Column(name = "callback_uri")
    private String callbackUri;

    /**
     * 权限
     */
    private String authorities;

    /**
     * 令牌过期秒数
     */
    @Column(name = "expire_seconds")
    private Integer expireSeconds;

    /**
     * 刷新令牌过期秒数
     */
    @Column(name = "refresh_seconds")
    private Integer refreshSeconds;

    /**
     * 附加说明
     */
    private String remark;

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
     * 是否已锁定：0-否；1-是
     */
    @Column(name = "is_locked")
    private Byte isLocked;

    /**
     * 是否已删除
     */
    @Column(name = "is_deleted")
    private Byte isDeleted;

    /**
     * 锁定时间
     */
    @Column(name = "lock_time")
    private LocalDateTime lockTime;

    public static final String ID = "id";

    public static final String DB_ID = "id";

    public static final String CLIENT_ID = "clientId";

    public static final String DB_CLIENT_ID = "client_id";

    public static final String CLIENT_SECRET = "clientSecret";

    public static final String DB_CLIENT_SECRET = "client_secret";

    public static final String SCOPE = "scope";

    public static final String DB_SCOPE = "scope";

    public static final String GRANT_TYPES = "grantTypes";

    public static final String DB_GRANT_TYPES = "grant_types";

    public static final String CALLBACK_URI = "callbackUri";

    public static final String DB_CALLBACK_URI = "callback_uri";

    public static final String AUTHORITIES = "authorities";

    public static final String DB_AUTHORITIES = "authorities";

    public static final String EXPIRE_SECONDS = "expireSeconds";

    public static final String DB_EXPIRE_SECONDS = "expire_seconds";

    public static final String REFRESH_SECONDS = "refreshSeconds";

    public static final String DB_REFRESH_SECONDS = "refresh_seconds";

    public static final String REMARK = "remark";

    public static final String DB_REMARK = "remark";

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
}