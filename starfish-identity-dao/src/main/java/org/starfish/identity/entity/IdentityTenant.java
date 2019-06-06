package org.starfish.identity.entity;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "identity_tenant")
public class IdentityTenant {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 租户名称
     */
    private String name;

    /**
     * 联系人
     */
    private String linkman;

    /**
     * 备注
     */
    private String remark;

    /**
     * 联系电话
     */
    @Column(name = "contact_number")
    private String contactNumber;

    /**
     * 联系地址
     */
    private String address;

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
     * 状态
     */
    private Byte status;

    /**
     * 是否已删除
     */
    @Column(name = "is_deleted")
    private Byte isDeleted;

    public static final String ID = "id";

    public static final String DB_ID = "id";

    public static final String NAME = "name";

    public static final String DB_NAME = "name";

    public static final String LINKMAN = "linkman";

    public static final String DB_LINKMAN = "linkman";

    public static final String REMARK = "remark";

    public static final String DB_REMARK = "remark";

    public static final String CONTACT_NUMBER = "contactNumber";

    public static final String DB_CONTACT_NUMBER = "contact_number";

    public static final String ADDRESS = "address";

    public static final String DB_ADDRESS = "address";

    public static final String CREATE_USER = "createUser";

    public static final String DB_CREATE_USER = "create_user";

    public static final String CREATE_TIME = "createTime";

    public static final String DB_CREATE_TIME = "create_time";

    public static final String UPDATE_USER = "updateUser";

    public static final String DB_UPDATE_USER = "update_user";

    public static final String UPDATE_TIME = "updateTime";

    public static final String DB_UPDATE_TIME = "update_time";

    public static final String STATUS = "status";

    public static final String DB_STATUS = "status";

    public static final String IS_DELETED = "isDeleted";

    public static final String DB_IS_DELETED = "is_deleted";
}