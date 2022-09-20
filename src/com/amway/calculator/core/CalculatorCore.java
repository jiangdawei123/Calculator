package com.amway.calculator.core;

import com.amway.calculator.common.ButtonEnum;
import com.amway.calculator.common.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 计算器逻辑核心类
 * @author: david.jiang
 * @date: 2022/9/20 3:35 下午
 **/
public class CalculatorCore {

	/**
	 * 根据输入表达式计算结果
	 * @param input 输入的表达式
	 * @return 计算结果
	 */
	public double calculate(String input) {
		String[] numberArray = input.split(Constant.OPERATOR_SYMBOL_REGEX);
		String[] symbolArray = input.split(Constant.NUMBER_REGEX);
		List<String> operatorSymbol = new ArrayList<>();
		for (String s : symbolArray) {
			if (!s.equals(ButtonEnum.POINT.getWord())) {
				operatorSymbol.add(s);
			}
		}
		double result = 0;
		for (int i = 0; i < numberArray.length; ++i) {
			if ( i == 0 ) {
				result = Double.parseDouble(numberArray[i]);
				continue;
			}
			switch (ButtonEnum.getByWord(operatorSymbol.get(i))) {
				case ADD:
					result += Double.parseDouble(numberArray[i]);
					break;
				case REDUCE:
					result -= Double.parseDouble(numberArray[i]);
					break;
				case MULTIPLY:
					result *= Double.parseDouble(numberArray[i]);
					break;
				case DIVIDE:
					result /= Double.parseDouble(numberArray[i]);
					break;
				default:break;
			}
		}
		return result;
	}

}

