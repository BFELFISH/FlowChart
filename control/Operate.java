package control;
/**
 * 操作函数，里面包含复制、粘贴、剪切功能
 * @author suisui
 *
 */

import java.util.LinkedList;

import symbols.SpotLine;
import symbols.Symbol;

public class Operate {
	private LinkedList<Symbol> symbolList = new LinkedList<>();
	private LinkedList<Symbol> copyList = new LinkedList<>();

	public LinkedList<Symbol> getSymbolList() {
		return symbolList;
	}

	public LinkedList<Symbol> delete() {
		LinkedList<Symbol> buf = new LinkedList<>();
		for (Symbol symbol : symbolList) {
			if (symbol.isElected()) {
				buf.add(symbol);
			}
		}
		symbolList.removeAll(buf);
		return symbolList;
	}

	public void copy() {// 复制
		LinkedList<Symbol> buf = new LinkedList<>();
		int index = 0;
		for (Symbol symbol : symbolList) {
			if (symbol.isElected())
				buf.add(symbol);
			if (symbol instanceof SpotLine) {
				buf.add(symbolList.get(index + 1));
			}
			index++;
		}
		copyList = buf;
	}

	public LinkedList<Symbol> paste() {// 粘贴
		return copyList;
	}

	public void setSymbolList(LinkedList<Symbol> symbolList) {
		this.symbolList = symbolList;
	}

	public Operate(LinkedList<Symbol> symbolList) {
		this.symbolList = symbolList;
	}
}
