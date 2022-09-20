package com.amway.calculator.common;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @description: 计算器按钮枚举类
 * @author: david.jiang
 * @date: 2022/9/20 3:43 下午
 **/
public enum ButtonEnum {
    /**
     * 数字按钮
     */
    ZERO("0"),
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),

    /**
     * 加减乘除按钮
     */
    ADD("+"),
    REDUCE("-"),
    MULTIPLY("*"),
    DIVIDE("/"),

    /**
     * 小数点按钮
     */
    POINT("."),

    /**
     * 等于按钮
     */
    EQUAL("="),

    /**
     * 清除按钮
     */
    CLEAR("clear"),

    /**
     * 操作前滚和后滚按钮
     */
    REDO("redo"),
    UNDO("undo"),
    ;

    public static Set<String> OPERATOR_BUTTON_WORDS;

    static {
        OPERATOR_BUTTON_WORDS = new HashSet<>();
        OPERATOR_BUTTON_WORDS.add(ADD.getWord());
        OPERATOR_BUTTON_WORDS.add(REDUCE.getWord());
        OPERATOR_BUTTON_WORDS.add(MULTIPLY.getWord());
        OPERATOR_BUTTON_WORDS.add(DIVIDE.getWord());
        OPERATOR_BUTTON_WORDS.add(POINT.getWord());
    }

    private final String word;

    public String getWord() {
        return word;
    }

    public static ButtonEnum getByWord(String word) {
        return Arrays.stream(ButtonEnum.values()).filter(p -> p.getWord().equals(word)).findFirst().orElse(null);
    }

    ButtonEnum(String word) {
        this.word = word;
    }
}
