package org.starfish.identity.entity;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Data;

@Data
@Table(name = "identity_resource")
public class IdentityResource {
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
     * 父级菜单
     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 菜单编号
     */
    private String code;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单别名
     */
    private String alias;

    /**
     * 请求地址
     */
    private String path;

    /**
     * 菜单资源
     */
    private String source;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 菜单类型
     */
    private Integer category;

    /**
     * 操作按钮类型
     */
    private Integer action;

    /**
     * 是否打开新页面
     */
    @Column(name = "is_open")
    private Byte isOpen;

    /**
     * 备注
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

    public static final String TENANT_ID = "tenantId";

    public static final String DB_TENANT_ID = "tenant_id";

    public static final String PARENT_ID = "parentId";

    public static final String DB_PARENT_ID = "parent_id";

    public static final String CODE = "code";

    public static final String DB_CODE = "code";

    public static final String NAME = "name";

    public static final String DB_NAME = "name";

    public static final String ALIAS = "alias";

    public static final String DB_ALIAS = "alias";

    public static final String PATH = "path";

    public static final String DB_PATH = "path";

    public static final String SOURCE = "source";

    public static final String DB_SOURCE = "source";

    public static final String SORT = "sort";

    public static final String DB_SORT = "sort";

    public static final String CATEGORY = "category";

    public static final String DB_CATEGORY = "category";

    public static final String ACTION = "action";

    public static final String DB_ACTION = "action";

    public static final String IS_OPEN = "isOpen";

    public static final String DB_IS_OPEN = "is_open";

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