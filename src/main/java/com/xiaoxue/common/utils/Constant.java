package com.xiaoxue.common.utils;

import sun.security.krb5.internal.PAForUserEnc;

public class Constant {

    /**超级管理员ID**/
    public static final int SUPER_ADMIN=1;

    /**数据权限过滤**/
    public static final String SQL_FILTER="sql_filter";

    /**
     * 菜单类型
     */
    public enum MenuType{
        /**
         * 目录
         */
        CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        MenuType(int value){this.value=value;}

        public Integer getValue() {
            return value;
        }
    }

    /**
     * 定时任务状态
     */

    public enum ScheduleStatue {

        NORMAL(0),

        PAUSE(1);

        private int value;

        ScheduleStatue(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }


    }




}
