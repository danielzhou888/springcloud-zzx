package com.zzx.design.pattern.zzxdesignpattern.example.zhifubao_weixin_pay;

import com.zzx.design.pattern.zzxdesignpattern.example.zhifubao_weixin_pay.core.Pay;
import com.zzx.design.pattern.zzxdesignpattern.example.zhifubao_weixin_pay.core.WxPay;
import com.zzx.design.pattern.zzxdesignpattern.example.zhifubao_weixin_pay.mode.PayCypherMode;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author zhouzhixiang
 * @Date 2020-11-26
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class Main {

    @Test
    public void test() throws Exception {
        Pay pay = new WxPay(new PayCypherMode());
        String transfer = pay.transfer("1", "2", new BigDecimal(100));
    }


    @Test
    public void testFirstNotRepeatingChar() {
        String str = "askdjfklsdahfhbfiuohasjkdfhsjdahfkhagjkfshdkfjhdsakfksadhgfjkfadsfklashjfahsdjuehrwtiueqwiofhjksdafhkjhj2j34hk23h4kj2239sjfjwidsjklqp";
        Map<String, Integer> map = new HashMap<>();
        Map<String, Integer> minIndexMap = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (map.containsKey(String.valueOf(c))) {
                Integer count = map.get(String.valueOf(c));
                count++;
                map.put(String.valueOf(c), count);
            } else {
                map.put(String.valueOf(c), 1);
                minIndexMap.put(String.valueOf(c), i);
            }
        }
        int minIndex = -1;
        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> next = iterator.next();
            Integer value = next.getValue();
            if (value == 1) {
                Integer tempIndex = minIndexMap.get(String.valueOf(next.getKey()));
                if (minIndex == -1 || tempIndex < minIndex) {
                    minIndex = tempIndex;
                }
            }
        }
        log.info("minIndex = {}", minIndex);
    }
}
