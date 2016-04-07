package orm.consts;

public interface DBConsts {
	/** DB标准字段-主键 */
	public static final String FIELD_ID = "id";

	/** DB标准字段-状态 */
	public static final String FIELD_STATUS = "status";

	/** DB标准字段-逻辑状态 */
	public static final String FIELD_DR = "dr";

	/** DB标准字段-时间戳 */
	public static final String FIELD_TS = "ts";

	/** 数据逻辑状态 - 正常态 */
	public static final Short DR_NORMAL = 0;

	/** 数据逻辑状态 - 删除态 */
	public static final Short DR_DELETE = 1;

	/** 启用标识 - 启用 */
	public static final Short STATUS_ENABLE = 0;

	/** 启用标识 - 停用 */
	public static final Short STATUS_DISABLE = 1;

	/** 性别 - 男 */
	public static final Short SEX_MALE = 0;

	/** 启用标识 - 停用 */
	public static final Short SEX_FEMALE = 1;

	/** 节点类型 - 虚拟节点 */
	public static final Short NODE_TYPE_DUMMY = 0;

	/** 节点类型 - 功能节点 */
	public static final Short NODE_TYPE_FUNC = 1;

	/** 客户类型 - 内部单位 */
	public static final Short CUSTPROP_IN = 1;

	/** 客户类型 - 外部单位 */
	public static final Short CUSTPROP_OUT = 0;

	/** 是否是散户 - 是 */
	public static final Short ISFREECUST_YES = 0;

	/** 是否是散户 - 不是 */
	public static final Short ISFREECUST_NO = 1;

	/** 是否是零售门店 - 是 */
	public static final Short ISRETAILSTORE_YES = 0;

	/** 是否是零售门店 - 不是 */
	public static final Short ISRETAILSTORE_NO = 1;

	/** 是否供应商 - 是 */
	public static final Short ISSUPPLIER_YES = 0;

	/** 是否供应商 - 不是 */
	public static final Short ISSUPPLIER_NO = 1;

}
