package view;

import javafx.geometry.Point2D;
import javafx.scene.effect.Light.Point;
import javafx.scene.layout.Pane;
import symbols.CCircle;
import symbols.CurveRectangle;
import symbols.Diamond;
import symbols.LLine;
import symbols.Parallelogram;
import symbols.RRectangle;
import symbols.RoundRectangle;
import symbols.Symbol;

public class DrawSymbol {
	private Pane pane = new Pane();

	public DrawSymbol(Pane pane) {
		this.pane = pane;
	}

	private RRectangle addRect(boolean isDoubleClicked, double mouseX, double mouseY) {
		double width = 100;
		double height = 40;
		double x = 0;
		double y = 0;
		if (isDoubleClicked) {// 双击直接出现在画板中央
			x = (pane.getPrefWidth() - width) / 2;
			y = (pane.getPrefHeight() - height) / 2;
		} else {
			x = mouseX - width / 2;
			y = mouseY - height / 2;
		}
		RRectangle rect = new RRectangle(x, y, width, height);
		return rect;
	}

	private RoundRectangle addRoundRect(boolean isDoubleClicked, double mouseX, double mouseY) {
		double width = 100;
		double height = 40;
		double x = 0;
		double y = 0;
		if (isDoubleClicked) {
			x = (pane.getPrefWidth() - width) / 2;
			y = (pane.getPrefHeight() - height) / 2;
		} else {
			x = mouseX - width / 2;
			y = mouseY - height / 2;
		}
		RoundRectangle roundRect = new RoundRectangle(x, y, width, height);
		return roundRect;
	}

	private Diamond addDiamond(boolean isDoubleClicked, double mouseX, double mouseY) {
		double width = 100.0;
		double height = 50.0;
		double x = 0;
		double y = 0;
		if (isDoubleClicked) {
			x = (pane.getPrefWidth() - width) / 2;
			y = (pane.getPrefHeight() - height) / 2;
		} else {
			x = mouseX - width / 2;
			y = mouseY;
		}
		Diamond diamond = new Diamond(x, y, width, height);
		return diamond;
	}

	private Parallelogram addParallelogram(boolean isDoubleClicked, double mouseX, double mouseY) {
		double width = 115.0;
		double height = 40.0;
		double x = 0;
		double y = 0;
		if (isDoubleClicked) {
			x = (pane.getPrefWidth() - width) / 2;
			y = (pane.getPrefHeight() - height) / 2;
		} else {
			x = mouseX - width / 2;
			y = mouseY + height / 2;
		}
		Parallelogram p = new Parallelogram(x, y, width, height);
		return p;
	}

	private CCircle addCircle(boolean isDoubleClicked, double mouseX, double mouseY) {
		double radius = 10;
		double centerX = 0;
		double centerY = 0;
		if (isDoubleClicked) {
			centerX = (pane.getPrefWidth() - radius) / 2;
			centerY = (pane.getPrefHeight() - radius) / 2;
		} else {
			centerX = mouseX;
			centerY = mouseY;
		}
		CCircle c = new CCircle(centerX, centerY, radius);
		return c;
	}

	private CurveRectangle addCurveRect(boolean isDoubleClicked, double mouseX, double mouseY) {
		double width = 120.0;
		double height = 50.0;
		double startX, startY;
		if (isDoubleClicked) {
			startX = (pane.getPrefWidth() - width) / 2;
			startY = (pane.getPrefHeight() - height) / 2;
		} else {
			startX = mouseX - width / 2;
			startY = mouseY + height / 2;
		}
		CurveRectangle cr = new CurveRectangle(startX, startY, width, height);
		return cr;
	}

	private LLine addLine(boolean isDoubleClicked, double mouseX, double mouseY) {
		double length = 150;
		double startX, startY, endX, endY;
		if (isDoubleClicked) {
			startX = (pane.getPrefWidth() - 100) / 2;
			startY = (pane.getPrefHeight() - 100) / 2;
			endX = startX + length;
			endY = startY;
		}else {
			startX = mouseX;
			startY = mouseY;
			endX = startX + length;
			endY = startY;
		}
		LLine l = new LLine(startX, startY, endX, endY);
		return l;
	}

	public Symbol addSymbol(Symbol symbol, boolean isDoubleClicked, double mouseX, double mouseY) {
		if (symbol instanceof RRectangle) {
			return addRect(isDoubleClicked, mouseX, mouseY);
		} else if (symbol instanceof RoundRectangle) {
			return addRoundRect(isDoubleClicked, mouseX, mouseY);
		} else if (symbol instanceof Diamond) {
			return addDiamond(isDoubleClicked, mouseX, mouseY);
		} else if (symbol instanceof Parallelogram) {
			return addParallelogram(isDoubleClicked, mouseX, mouseY);
		} else if (symbol instanceof CCircle) {
			return addCircle(isDoubleClicked, mouseX, mouseY);
		} else if (symbol instanceof CurveRectangle) {
			return addCurveRect(isDoubleClicked, mouseX, mouseY);
		}
		return addLine(isDoubleClicked, mouseX, mouseY);
	}
}
