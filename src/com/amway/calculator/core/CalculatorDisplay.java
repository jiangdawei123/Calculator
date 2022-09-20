package com.amway.calculator.core;

import com.amway.calculator.common.ButtonEnum;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.util.Stack;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @description: 计算器展示层
 * @author: david.jiang
 * @date: 2022/9/20 3:35 下午
 **/
public class CalculatorDisplay {

	/**
	 * 显示框显示文本
	 */
	private String text = "";

	/**
	 * 文本长度
	 */
	private final int COLUMNS = 30;

	/**
	 * 显示框对象
	 */
	private final TextField textField = new TextField(COLUMNS);

	/**
	 * 回滚栈
	 */
	private final Stack<String> undoChar = new Stack<>();

	/**
	 * 启动计算器界面
	 */
	public void start() {
		JFrame jFrame = new JFrame("计算器");
		jFrame.setLayout(new FlowLayout(FlowLayout.CENTER));
		jFrame.setSize(500,500);
		jFrame.setLocation(700,300);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel jPanel1 = new JPanel(new GridLayout(1,4,5,5));
		jPanel1.add(getActionButton(ButtonEnum.SEVEN.getWord()));
		jPanel1.add(getActionButton(ButtonEnum.EIGHT.getWord()));
		jPanel1.add(getActionButton(ButtonEnum.NINE.getWord()));
		jPanel1.add(getActionButton(ButtonEnum.ADD.getWord()));

		JPanel jPanel2 = new JPanel(new GridLayout(1,4,5,5));
		jPanel2.add(getActionButton(ButtonEnum.FOUR.getWord()));
		jPanel2.add(getActionButton(ButtonEnum.FIVE.getWord()));
		jPanel2.add(getActionButton(ButtonEnum.SIX.getWord()));
		jPanel2.add(getActionButton(ButtonEnum.REDUCE.getWord()));

		JPanel jPanel3 = new JPanel(new GridLayout(1,4,5,5));
		jPanel3.add(getActionButton(ButtonEnum.ONE.getWord()));
		jPanel3.add(getActionButton(ButtonEnum.TWO.getWord()));
		jPanel3.add(getActionButton(ButtonEnum.THREE.getWord()));
		jPanel3.add(getActionButton(ButtonEnum.MULTIPLY.getWord()));

		JPanel jPanel4 = new JPanel(new GridLayout(1,4,5,5));
		jPanel4.add(getActionButton(ButtonEnum.POINT.getWord()));
		jPanel4.add(getActionButton(ButtonEnum.ZERO.getWord()));
		jPanel4.add(getActionButton(ButtonEnum.DIVIDE.getWord()));
		jPanel4.add(getEqualButton(ButtonEnum.EQUAL.getWord()));

		JPanel jPanel5 = new JPanel(new GridLayout(1,4,5,5));
		jPanel5.add(getClearButton(ButtonEnum.CLEAR.getWord()));
		jPanel5.add(getRedoButton(ButtonEnum.REDO.getWord()));
		jPanel5.add(getUndoButton(ButtonEnum.UNDO.getWord()));
		jPanel5.add(getBlankButton());

		Box box = Box.createVerticalBox();
		box.add(textField);
		box.add(jPanel1);
		box.add(jPanel2);
		box.add(jPanel3);
		box.add(jPanel4);
		box.add(jPanel5);

		jFrame.setContentPane(box);
		jFrame.setVisible(true);
	}

	/**
	 * 获取操作按钮
	 * @param buttonName 按钮文本
	 * @return 按钮
	 */
	public JButton getActionButton(String buttonName) {
		JButton button = new JButton(buttonName);
		button.addActionListener(e -> {
			if( text.length() > 0
					&& ButtonEnum.OPERATOR_BUTTON_WORDS.contains(String.valueOf(text.charAt(text.length() - 1)))
					&& ButtonEnum.OPERATOR_BUTTON_WORDS.contains(buttonName) ) {
				return;
			}
			if( text.length() == 0 && ButtonEnum.OPERATOR_BUTTON_WORDS.contains(buttonName) ) {
				return;
			}
			text = text + buttonName;
			textField.setText(text);
		});
		return button;
	}

	/**
	 * 获取等于号按钮
	 * @param buttonName 按钮文本
	 * @return 按钮
	 */
	public JButton getEqualButton(String buttonName) {
		JButton button = new JButton(buttonName);
		button.addActionListener(arg -> {
			if( !"".equals(text.trim()) ) {
				textField.setText(String.valueOf(new CalculatorCore().calculate(text)));
			}
			text = "";
		});
		return button;
	}

	/**
	 * 获取清除按钮
	 * @param buttonName 按钮文本
	 * @return 按钮
	 */
	public JButton getClearButton(String buttonName) {
		JButton button = new JButton(buttonName);
		button.addActionListener(arg -> {
			text = "";
			textField.setText(text);
		});
		return button;
	}

	/**
	 * 获取前滚按钮
	 * @param buttonName 按钮文本
	 * @return 按钮
	 */
	public JButton getRedoButton(String buttonName) {
		JButton button = new JButton(buttonName);
		button.addActionListener(arg -> {
			if( undoChar.size() <= 0 ) {
				return;
			}
			text = text + undoChar.pop();
			textField.setText(text);
		});
		return button;
	}

	/**
	 * 获取回滚按钮
	 * @param buttonName 按钮文本
	 * @return 按钮
	 */
	public JButton getUndoButton(String buttonName) {
		JButton button = new JButton(buttonName);
		button.addActionListener(arg -> {
			if( text.length() <= 0 ) {
				return;
			}
			undoChar.push(String.valueOf(text.charAt(text.length() - 1)));
			text = text.substring(0, text.length() - 1);
			textField.setText(text);
		});
		return button;
	}

	/**
	 * 获取一个空格按钮
	 * @return 按钮
	 */
	public JButton getBlankButton() {
		return new JButton("");
	}

}

