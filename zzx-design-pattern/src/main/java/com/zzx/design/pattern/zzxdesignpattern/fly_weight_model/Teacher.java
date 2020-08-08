package com.zzx.design.pattern.zzxdesignpattern.fly_weight_model;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class Teacher implements TeacherApi {

        private Long tNo;

        public Teacher(Long tNo) {
            this.tNo = tNo;
        }

        @Override
        public Long getTNo() {
            return tNo;
        }

        @Override
        public void handlePaper(Long stuNo) {
            log.info("批改作业：Teacher No = {}, Student No = {}", tNo, stuNo);
        }
    }