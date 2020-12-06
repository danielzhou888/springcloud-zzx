package com.zzx.sentinel.order.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhouzhixiang
 * @Date 2020-09-30
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResult implements Serializable {

    private static final long serialVersionUID = 5989824943201111722L;
    private String orderId;
    private boolean executeResult;
}
