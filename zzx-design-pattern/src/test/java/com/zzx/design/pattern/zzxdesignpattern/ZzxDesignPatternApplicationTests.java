package com.zzx.design.pattern.zzxdesignpattern;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZzxDesignPatternApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void test() {
        Set<Long> syList = new HashSet<>();
        syList.add(1L);
        syList.add(2L);
        syList.add(3L);
        syList.add(4L);
        syList.add(5L);
        syList.add(6L);
        String fromSymptomIdList = getFromSymptomIdList(syList);
        System.out.println(fromSymptomIdList);
    }

    private String getFromSymptomIdList(Set<Long> symptomIdList) {
        StringBuilder sb = new StringBuilder();
        if (symptomIdList != null && symptomIdList.size() > 0) {
            symptomIdList.stream().forEach(s -> sb.append(s).append(","));
            String sympId = sb.toString().trim();
            if (StringUtils.isNotEmpty(sympId) && sympId.length() > 0) {
                return sympId.substring(0, sympId.lastIndexOf(","));
            }
        }
        return sb.toString();
    }


}
