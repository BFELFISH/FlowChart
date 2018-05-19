package view;

import java.awt.RenderingHints.Key;
import java.lang.reflect.Array;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

import control.DrawSymbol;
import control.MyUtil;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Path;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import memento.Caretaker;
import memento.Memento;
import symbols.CCircle;
import symbols.CurveRectangle;
import symbols.Diamond;
import symbols.LLine;
import symbols.Parallelogram;
import symbols.RRectangle;
import symbols.RoundRectangle;
import symbols.SpotLine;
import symbols.Symbol;
import javafx.scene.Node;
import javafx.scene.ParallelCamera;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.Light.Point;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class BorderPaneController {
	@FXML
	private Pane pane;
	@FXML
	private Rectangle rectangle; // 矩形
	@FXML
	private Rectangle roundRect; // 圆角矩形
	@FXML
	private Path diamond; // 菱形
	@FXML
	private Path parallelogram; // 平行四边形
	@FXML
	private Line line; // 实线
	@FXML
	private Circle circle; // 圆
	@FXML
	private Path curveRect; // 曲线矩形

	private boolean isElectedSymbols = false;// 当前是否有被选中的元素
	private boolean isControlDown = false;
	private boolean isDrawLine = false;
	private boolean isDoubleClicked = false;// 是否双击左侧图形栏中的图形
	private TextField textBox = new TextField("");
	private Symbol bufferSymbol;
	private LinkedList<Symbol> symbolList = new LinkedList<Symbol>();
	private LinkedList<LinkedList<Shape>> boxList = new LinkedList<LinkedList<Shape>>();
	private LinkedList<LLine> lineList = new LinkedList<LLine>();
	private LinkedList<LinkedList<Symbol>> caretaker = new LinkedList<>();
	private LinkedList<Point2D> linePoints = new LinkedList<>();// 点线集的点集合
	// private Caretaker caretaker = new Caretaker();
	private LLine bufLine=new LLine();//仅用于画直线时的缓冲直线，无用
	@FXML
	private void drawRect(MouseEvent e) {
		textBox.setVisible(false);
		DrawSymbol draw = new DrawSymbol(pane);
		RRectangle rect = new RRectangle();
		bufferSymbol = rect;
		if (e.getClickCount() == 1) {
			isDoubleClicked = false;
		} else {
			isDoubleClicked = true;
			symbolList.add(draw.addSymbol(bufferSymbol, isDoubleClicked, e.getX(), e.getY()));
			repaint();
		}
	}

	@FXML
	private void drawRoundRect(MouseEvent e) {
		textBox.setVisible(false);
		DrawSymbol draw = new DrawSymbol(pane);
		RoundRectangle roundRect = new RoundRectangle();
		bufferSymbol = roundRect;
		if (e.getClickCount() == 1) {
			isDoubleClicked = false;
		} else {
			isDoubleClicked = true;
			symbolList.add(draw.addSymbol(bufferSymbol, isDoubleClicked, e.getX(), e.getY()));
			repaint();
		}
	}

	@FXML
	private void drawDiamond(MouseEvent e) {
		textBox.setVisible(false);
		DrawSymbol draw = new DrawSymbol(pane);
		Diamond diamond = new Diamond();
		bufferSymbol = diamond;
		if (e.getClickCount() == 1) {
			isDoubleClicked = false;
		} else {
			isDoubleClicked = true;
			symbolList.add(draw.addSymbol(bufferSymbol, isDoubleClicked, e.getX(), e.getY()));
			repaint();
		}
	}

	@FXML
	private void drawParallelogram(MouseEvent e) {
		textBox.setVisible(false);
		DrawSymbol draw = new DrawSymbol(pane);
		Parallelogram p = new Parallelogram();
		bufferSymbol = p;
		if (e.getClickCount() == 1) {
			isDoubleClicked = false;
		} else {
			isDoubleClicked = true;
			symbolList.add(draw.addSymbol(bufferSymbol, isDoubleClicked, e.getX(), e.getY()));
			repaint();
		}
	}

	@FXML
	private void drawLine1(MouseEvent e) {
		textBox.setVisible(false);
		isDrawLine = true;
		DrawSymbol draw = new DrawSymbol(pane);
		SpotLine spotLine = new SpotLine();
		bufferSymbol = spotLine;
		if (e.getClickCount() == 1) {
			isDoubleClicked = false;
		} else {
			isDoubleClicked = true;
			symbolList.add(draw.addSymbol(bufferSymbol, isDoubleClicked, e.getX(), e.getY()));
			repaint();
		}
	}

	@FXML
	private void drawCircle(MouseEvent e) {
		textBox.setVisible(false);
		DrawSymbol draw = new DrawSymbol(pane);
		CCircle circle = new CCircle();
		bufferSymbol = circle;
		if (e.getClickCount() == 1) {
			isDoubleClicked = false;
		} else {
			isDoubleClicked = true;
			symbolList.add(draw.addSymbol(bufferSymbol, isDoubleClicked, e.getX(), e.getY()));
			repaint();
		}
	}

	@FXML
	private void drawCurveRect(MouseEvent e) {
		textBox.setVisible(false);
		DrawSymbol draw = new DrawSymbol(pane);
		CurveRectangle cr = new CurveRectangle();
		bufferSymbol = cr;
		if (e.getClickCount() == 1) {
			isDoubleClicked = false;
		} else {
			isDoubleClicked = true;
			symbolList.add(draw.addSymbol(bufferSymbol, isDoubleClicked, e.getX(), e.getY()));
			repaint();
		}
	}

	@FXML
	private void paneMouseMove(MouseEvent e) {
		if(isDrawLine) {
			bufLine.setEndX(e.getX());
			bufLine.setEndY(e.getY());
			bufLine.updatePath();
		}
	}

	@FXML
	private void paneClicked(MouseEvent e) {
		pane.requestFocus();
		bufLine=new LLine(e.getX(), e.getY(), e.getX(), e.getY());
		bufLine.setWithArrow(true);
		DrawSymbol draw = new DrawSymbol(pane);
		textBox.setVisible(false);
		while (!isDoubleClicked) {// 单击
			if (isDrawLine) {
				if (e.getClickCount() == 1) {// 鼠标单击
					System.out.println("鼠标单击");
					linePoints.add(new Point2D(e.getX(), e.getY()));
					pane.getChildren().add(bufLine);
					break;
				} else {// 鼠标双击结束
					System.out.println("鼠标双击结束");
					SpotLine spotLine = new SpotLine(linePoints);
					symbolList.add(spotLine);
					pane.getChildren().add(bufLine);
					isDoubleClicked = true;
					isDrawLine = false;
					repaint();
					break;
				}
			} else {// 当前不处于画直线操作
				symbolList.add(draw.addSymbol(bufferSymbol, isDoubleClicked, e.getX(), e.getY()));
				isDoubleClicked = true;
				isDrawLine = false;
				repaint();
				break;
			}
		}

		if (!isDrawLine) {
			double x = e.getX();
			double y = e.getY();
			int index = findClickedElement(new Point2D(x, y));
			if (index == -1) {// 没有被选中的图形
				isElectedSymbols = false; // 当前没有选中任何图形
				boxList.clear(); // 清除所有操作框
				textBox.setVisible(false);
				for (Symbol symbol : symbolList) {
					symbol.setElected(false);
					symbol.setTextFieldIsEleted(false);
				}
				isControlDown = false;
			} else if (index == -2) {// 选中了操作框，当前处于操作阶段
				textBox.setVisible(false);
				isElectedSymbols = true;
			} else {// 选中了图形
				System.out.println("isControlDown:" + isControlDown);
				if (isControlDown) {// 多选
					boxList.clear();
					symbolList.get(index).setElected(true);// 当前图形节点被选中，设为true
					for (Symbol symbol : symbolList) {
						if (symbol.isElected())
							drawBox(symbol);
					}
				} else {// 单选
					boxList.clear();
					for (Symbol symbol : symbolList) {
						symbol.setElected(false);// 全部选中状态设为false
					}
					symbolList.get(index).setElected(true);// 当前图形节点选中设为true
					drawBox(symbolList.get(index));
				}
				if (e.getClickCount() == 2) {// 双击出现文本框
					textBox.setVisible(true);
					symbolList.get(index).setTextFieldIsEleted(true);
					drawTextField(symbolList.get(index));
					// symbolList.get(index).setTextFieldIsEleted(false);
				}
			}
			// caretaker.add(new Memento(symbolList));
			repaint();
		}
	}

	private void drawBox(Symbol symbol) {
		LinkedList<Shape> buff = new LinkedList<Shape>();
		// if (symbol.isElected() && symbol instanceof LLine) {
		// Circle circles[] = symbol.getCircles();
		// for (Circle c : circles) {
		// buff.add(c);
		// }
		// }
		if (symbol.isElected() && symbol instanceof SpotLine) {
			Circle circles[] = symbol.getCircles();
			for (Circle c : circles) {
				buff.add(c);
			}
		} else if (symbol.isElected()) {
			Line line[] = symbol.getcBox().getcBox();
			for (Line l : line) {
				buff.add(l);
			}
			Circle circles[] = symbol.getcBox().getCircles();
			for (Circle c : circles) {
				buff.add(c);
			}
		}
		boxList.add(buff);
	}

	// 画出TextField
	private void drawTextField(Symbol symbol) {
		if (symbol.isTextFieldIsEleted()) {
			// 初始化
			textBox.setPrefHeight(symbol.getHeight());
			if (symbol instanceof RRectangle || symbol instanceof RoundRectangle) {
				textBox.setLayoutX(symbol.getX() + symbol.getWidth() / 40);
				textBox.setLayoutY(symbol.getY());
				textBox.setPrefWidth(symbol.getWidth() - 2 * symbol.getWidth() / 40);

			} else if (symbol instanceof Parallelogram || symbol instanceof CurveRectangle) {
				textBox.setLayoutX(symbol.getX() + symbol.getWidth() / 10);
				textBox.setLayoutY(symbol.getY() - symbol.getHeight());
				textBox.setPrefWidth(symbol.getWidth() - 2 * symbol.getWidth() / 10);

			} else if (symbol instanceof Diamond) {
				textBox.setLayoutX(symbol.getX() + symbol.getWidth() / 10);
				textBox.setLayoutY(symbol.getY() - symbol.getHeight() / 2);
				textBox.setPrefWidth(symbol.getWidth() - 2 * symbol.getWidth() / 10);
			} else if (symbol instanceof LLine) {
				LLine line = (LLine) symbol;
				textBox.setLayoutX((line.getStartX() + line.getEndX()) / 2);
				textBox.setLayoutY((line.getStartY() + line.getEndY()) / 2);
				textBox.setPrefWidth(30);
			} else {
				textBox.setVisible(false);
			}

			textBox.setText("");
			if (symbol.getText().getText() != "") {// 使得新出现的TextField依旧是原来的文本
				textBox.setText(symbol.getText().getText());
			} else {
				textBox.setPromptText("请输入文字");
			}

			textBox.setOnKeyPressed(e -> {
				if (e.getCode() == KeyCode.ENTER) {
					textBox.setVisible(false);
				}
				Text buf = new Text(textBox.getText());
				symbol.setText(buf);
				repaint();
			});
		}
	}

	// 寻找鼠标所在位置是否点中某个图形节点
	private int findClickedElement(Point2D p) {
		// 判断是否处于操作框
		for (int i = 0; i < boxList.size(); i++) {
			for (int j = 0; j < boxList.get(i).size(); j++) {
				if (boxList.get(i).get(j).contains(p)) {
					return -2;
				}
			}
		}
		// 判断是否处于图形内
		for (int i = 0; i < symbolList.size(); i++) {
			if (symbolList.get(i).contains(p)) {
				return i;
			}
		}
		return -1;

	}

	private LinkedList<Symbol> symbolListClone(LinkedList<Symbol> symbols) {
		LinkedList<Symbol> buf = new LinkedList<Symbol>();
		int index = 0;
		for (Symbol symbol : symbols) {
			// System.out.println("symbolListClone方法");
			// System.out.println(symbol);
			// System.out.println(symbol.clone());
			buf.add(index, symbol.clone());
			index++;
		}
		return buf;
	}

	/**
	 * 撤销
	 * 
	 * @throws Exception
	 */
	private void undo() {
		// // for (Symbol symbol : symbolList) {
		// // System.out.println(symbol);
		// // }
		// // System.out.println(symbolList);
		// LinkedList<Symbol> buf = symbolListClone(caretaker.getUndo());
		// symbolList = buf;
		//
		// // System.out.println("撤销后：");
		// // for (Symbol symbol : symbolList) {
		// // System.out.println(symbol);
		// // }
		// isElectedSymbols = false;
		// boxList.clear();
		// elctedSymbol = null;
		//
		// repaint();
	}

	/**
	 * 前进
	 */
	private void redo() {
		// LinkedList<Symbol> buf = caretaker.getRedo();
		// symbolList.clear();
		// int index = 0;
		// for (Symbol symbol : buf) {
		// symbolList.set(index, symbol.clone());
		// index++;
		// }
		// // for (Symbol symbol : symbolList) {
		// // System.out.println(symbol);
		// // }
		// // System.out.println(symbolList);
		// isElectedSymbols = false;
		// boxList.clear();
		// elctedSymbol = null;
		// repaint();
	}

	/**
	 * 删除
	 * 
	 * @param KeyEvent
	 * 
	 */
	@FXML
	private void paneKeyPressed(KeyEvent k) {

	}

	@FXML
	private void delete(KeyEvent k) {
		ArrayList<KeyCode> list = new ArrayList<>();

		if (k.getCode() == KeyCode.CONTROL) {
			isControlDown = true;
			if (k.getCode() == KeyCode.A) {// 全选
				boxList.clear();
				for (Symbol symbol : symbolList) {
					symbol.setElected(true);
					drawBox(symbol);
				}
				repaint();
			}

		}
		if (k.getCode() == KeyCode.BACK_SPACE) {
			boxList.clear();
			LinkedList<Symbol> buf = new LinkedList<>();
			for (Symbol symbol : symbolList) {
				if (symbol.isElected())
					buf.add(symbol);
			}
			symbolList.removeAll(buf);
			repaint();
		}
	}

	private void repaint() {
		// caretaker.add(new Memento(symbolListClone(symbolList)));
		pane.getChildren().clear();
		pane.getChildren().addAll(lineList);
		for (Symbol symbol : symbolList) {
			pane.getChildren().add((Shape) symbol);
			if (symbol.getText() != null)
				pane.getChildren().add(symbol.getText());
		}
		for (LinkedList<Shape> linkedList : boxList) {
			pane.getChildren().addAll(linkedList);
		}
		if (textBox != null)
			pane.getChildren().add(textBox);
	}
}
