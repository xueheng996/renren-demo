package com.xiaoxue.common.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataFilter {

    /**表的别名**/
    String tableAlias() default "";

    /**true:拥有子部门数据权限*/
    boolean user() default true;

    /**true:拥有子部门的数据权限*/
    boolean subDept() default false;

    /**部门ID**/
    String deptId() default "dept_id";

    /**用户ID**/
    String userId() default "user_id";
}
