package org.starfish.identity.entity;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "identity_dict")
public class IdentityDict {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 父主键
     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 租户ID
     */
    @Column(name = "tenant_id")
    private Integer tenantId;

    /**
     * 字典码
     */
    private String code;

    /**
     * 字典值
     */
    @Column(name = "dict_key")
    private Integer dictKey;

    /**
     * 字典名称
     */
    @Column(name = "dict_value")
    private String dictValue;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 字典备注
     */
    private String remark;

    /**
     * 是否已删除
     */
    @Column(name = "is_deleted")
    private Byte isDeleted;

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

    public static final String ID = "id";

    public static final String DB_ID = "id";

    public static final String PARENT_ID = "parentId";

    public static final String DB_PARENT_ID = "parent_id";

    public static final String TENANT_ID = "tenantId";

    public static final String DB_TENANT_ID = "tenant_id";

    public static final String CODE = "code";

    public static final String DB_CODE = "code";

    public static final String DICT_KEY = "dictKey";

    public static final String DB_DICT_KEY = "dict_key";

    public static final String DICT_VALUE = "dictValue";

    public static final String DB_DICT_VALUE = "dict_value";

    public static final String SORT = "sort";

    public static final String DB_SORT = "sort";

    public static final String REMARK = "remark";

    public static final String DB_REMARK = "remark";

    public static final String IS_DELETED = "isDeleted";

    public static final String DB_IS_DELETED = "is_deleted";

    public static final String CREATE_USER = "createUser";

    public static final String DB_CREATE_USER = "create_user";

    public static final String CREATE_TIME = "createTime";

    public static final String DB_CREATE_TIME = "create_time";

    public static final String UPDATE_USER = "updateUser";

    public static final String DB_UPDATE_USER = "update_user";

    public static final String UPDATE_TIME = "updateTime";

    public static final String DB_UPDATE_TIME = "update_time";
}