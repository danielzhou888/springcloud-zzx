package com.zzx.sentinel.voucher.po;

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
public class Promotion implements Serializable {

    private static final long serialVersionUID = 5989824943201111722L;
    private Long id;
    private Date sendTime;
    private String receiverMobile;
    private String courierMobile;
    private String receiverName;
    private String courierName;

}
