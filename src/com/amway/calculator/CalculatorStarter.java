package com.amway.calculator;

import com.amway.calculator.core.CalculatorDisplay;

/**
 * @description: 计算器启动类
 * @author: david.jiang
 * @date: 2022/9/20 3:35 下午
 **/
public class CalculatorStarter {

    public static void main(String[] args) {
        // 直接运行main方法，启动计算器可视化界面
        new CalculatorDisplay().start();
    }

}
