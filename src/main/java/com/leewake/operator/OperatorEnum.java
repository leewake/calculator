package com.leewake.operator;

public enum OperatorEnum {

    ADD("+", "加"),
    SUB("-", "减"),
    MUL("*", "乘"),
    DIV("/", "除"),
    SQRT("sqrt", "开方"),
    UNDO("undo", "撤销"),
    CLEAR("clear", "清屏");

    private String code;
    private String desc;

    OperatorEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static boolean isInclude(String val){
        boolean include = false;
        for (OperatorEnum e: OperatorEnum.values()){
            if(val.equals(e.getCode())){
                include = true;
                break;
            }
        }
        return include;
    }

    public static OperatorEnum getOperatorEnum(String code) {
        for (OperatorEnum item : OperatorEnum.values()) {
            if (code.equals(item.getCode())) {
                return item;
            }
        }
        return null;
    }

}
