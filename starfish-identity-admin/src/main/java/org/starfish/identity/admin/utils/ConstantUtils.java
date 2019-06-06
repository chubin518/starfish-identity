package org.starfish.identity.admin.utils;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ConstantUtils {
    /**
     * 加密次数
     */
    @Value("${constant.security.iterations}")
    private Integer Iterations;

    /**
     * 加密方式
     */
    @Value("${constant.security.algorithm-name}")
    private String algorithmName;

    /**
     * 锁定分钟&重试次数
     */
    @Value("${constant.security.lock-minute}")
    private Integer lockMinutes;

}
