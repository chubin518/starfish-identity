package org.starfish.identity.admin.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class ConstantPropertyUtils {
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

    public Integer getLockMinutes() {
        return lockMinutes;
    }

    public Integer getIterations() {
        return Iterations;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }
}
