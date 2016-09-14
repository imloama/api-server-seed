package seed.consts;

/**
 * 返回结果常量
 *
 * 常见错误标识
 *
 * 尽量保持和http code含义一致
 *
 * @author 马兆永
 *
 */
public interface CodeConsts {

	public static final String CODE_KEY = "code";
	public static final String MSG_KEY = "msg";
	

    /**
     * 处理成功
     */
    public static final int CODE_SUCCESS = 200;

    /**
     * 请求错误
     */
    public static final int CODE_ERR = 400;

    /**
     * 请求未授权
     */
    public static final int CODE_UNAUTHORIZED = 401;

    /**
     * 服务器错误
     */
    public static final int CODE_SERVER_ERR = 500;

    /**
     * 需要重定向
     */
    public static final int CODE_REDIRECT = 303;
    /**
     * 访问受限，授权过期
     */
    public  static final int CODE_UNLOGIN = 403;

    /**
     * 未找到资源
     */
    public static final int CODE_404 = 404;


    /**
     * 处理成功的默认信息
     */
    public static final String MSG_OK = "success";
}
