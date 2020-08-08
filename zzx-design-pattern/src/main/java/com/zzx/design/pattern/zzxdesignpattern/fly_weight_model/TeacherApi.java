package com.zzx.design.pattern.zzxdesignpattern.fly_weight_model;

interface TeacherApi {

        /**
         * 获取教师编号
         * @return
         */
        Long getTNo();

        /**
         * 批改试卷
         */
        void handlePaper(Long stuNo);
    }