package org.register.exception;

/**
 * Created by sunshiwang on 16/5/18.
 * 重复秒杀异常(运行期异常)
 */
public class RepeatKillException extends SeckillException {

    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
