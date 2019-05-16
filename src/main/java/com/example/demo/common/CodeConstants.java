package com.example.demo.common;


/**
 * 数据字典code存储
 */
public interface CodeConstants {

    /**
     * role
     */
    class Role extends BaseConstant<Integer> {

        public static final String CATEGORY_TYPE = "role";

        private Role(Integer codeValue, String codeName) {
            super(codeValue, codeName);
        }

        public static final Role monitor = new Role(0, "班长");
        public static final Role classCadre = new Role(1, "班干部");
        public static final Role student = new Role(2, "学生");

        @Override
        public Integer getCodeValue() {
            return super.getCodeValue();
        }
    }
}
