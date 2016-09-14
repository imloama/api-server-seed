package seed.config.jwt;

/**
 * JWT异常信息
 * Created by mazhyb on 2016-01-26.
 */
public class JWTException extends Exception {


    public JWTException(String msg) {
        super(msg);
    }

    public JWTException(Exception e) {
        super(e);
    }

    public JWTException(String msg,Exception e){
        super(msg,e);
    }

}

