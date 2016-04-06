package seed.pub.exception;

/**
 * 业务异常
 * Created by liuhm on 2015/8/13
 */
public class BusinessException extends Exception{

	private static final long serialVersionUID = 7760867055452954659L;


	public BusinessException() {
        super();
    }


    public BusinessException(String message) {
        super(message);
    }


    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }


    public BusinessException(Throwable cause) {
        super(cause);
    }


    protected BusinessException(String message, Throwable cause,
                        boolean enableSuppression,
                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
