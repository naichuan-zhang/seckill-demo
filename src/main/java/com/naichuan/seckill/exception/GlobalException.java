package com.naichuan.seckill.exception;

import com.naichuan.seckill.vo.RespBeanEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 张乃川
 * @date 2021/10/27 15:48
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GlobalException extends RuntimeException {

    private static final long serialVersionUID = -3948801954518535602L;
    private RespBeanEnum respBeanEnum;
}
