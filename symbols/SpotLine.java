package symbols;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

import org.omg.IOP.ENCODING_CDR_ENCAPS;

import control.ChooseBox;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.control.TextField;
import javafx.scene.effect.Light.Spot;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Text;

public class SpotLine extends Polyline implements Symbol, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 755365895642003924L;
	private boolean isElected = false; // 选中状态，默认没被选中
	transient private Text text = new Text(""); // 文本框
	private boolean TextFieldIsEleted = false;
	private double startX = 0;
	private double startY = 0;
	private double endX = 0;
	private double endY = 0;
	private double length;
	private boolean withArrow = false;// 直线是否带箭头，默认为false；
	private Symbol startSymbol; // 线所指的开始图形节点
	private Symbol endSymbol; // 线所指的结束图形节点
//	private LinkedList<Circle> cBox = new LinkedList<Circle>();
	private Circle[] cBox;
	private Path arrow= new Path();
	private LinkedList<Point2D> linePoints = new LinkedList<>();
	private final double OFFSET = 10;

	private void initialize() {
		
		//将传入的linePoints变成Double数组
		cBox=new Circle[linePoints.size()];
		Double[] points = new Double[linePoints.size() * 2];
		for (int i = 0; i < linePoints.size(); i++) {
			points[2 * i] = linePoints.get(i).getX();
			points[2 * i + 1] = linePoints.get(i).getY();
			Circle circle = new Circle(linePoints.get(i).getX(), linePoints.get(i).getY(),OFFSET / 2);
			cBox[i]=circle;
			cBox[i].setCursor(Cursor.HAND);
			cBox[i].setFill(Color.WHITE);
			cBox[i].setStroke(Color.BLACK);
		}
		this.getPoints().addAll(points);
		
		this.setStrokeWidth(1.3);
		this.setCursor(Cursor.HAND);
		this.setStroke(Color.BLACK);
		this.setFill(null);

		this.showSymbolBorder();
		this.setOnMouseClicked(e -> {
			this.drawElectBox();
		});
		this.setOnMouseDragged(e -> {
			this.drawElectBox();
		});
	}

	/**
	 * 鼠标进入图形内边框变红
	 */
	private void showSymbolBorder() {
		this.setOnMouseEntered(e -> {
			this.setStroke(Color.DARKRED);
			this.setStrokeWidth(2.0);
		});
		this.setOnMouseExited(e -> {
			this.setStroke(Color.BLACK);
			this.setStrokeWidth(1.3);
		});
	}

	//更新点集
	private void updatePoints() {
		this.getPoints().clear();
		for (int i = 0; i < linePoints.size(); i++) {
			Point2D point = linePoints.get(i);
			point = new Point2D(cBox[i].getCenterX(), cBox[i].getCenterY());
			linePoints.set(i, point);
		}
		
		Double[] points = new Double[linePoints.size() * 2];
		for (int i = 0; i < linePoints.size(); i++) {
			points[2 * i] = linePoints.get(i).getX();
			points[2 * i + 1] = linePoints.get(i).getY();
		}
		drawLineArrow();
		this.getPoints().addAll(points);
	}

	//画出操作框
	private void drawElectBox() {
		this.isElected = true;
		for (Circle circle : cBox) {
			circle.setOnMouseDragged(e -> {
				circle.setCenterX(e.getX());
				circle.setCenterY(e.getY());
				updatePoints();
			});
		}
	}

	private void drawLineArrow() {
		double H = 10; // 箭头高度
		double L = 4; // 底边的一半
		int x3 = 0;
		int y3 = 0;
		int x4 = 0;
		int y4 = 0;
		double awrad = Math.atan(L / H); // 箭头角度
		double awraLen = Math.sqrt(L * L + H * H); // 箭头的长度
		double[] arrXY_1 = rotateVec(endX - startX, endY - startY, awrad, true, awraLen);
		double[] arrXY_2 = rotateVec(endX - startX, endY - startY, -awrad, true, awraLen);
		double x_3 = endX - arrXY_1[0]; // (x3,y3)是第一端点
		double y_3 = endY - arrXY_1[1];
		double x_4 = endX - arrXY_2[0]; // (x4,y4)是第二端点
		double y_4 = endY - arrXY_2[1];
		Double X3 = new Double(x_3);
		x3 = X3.intValue();
		Double Y3 = new Double(y_3);
		y3 = Y3.intValue();
		Double X4 = new Double(x_4);
		x4 = X4.intValue();
		Double Y4 = new Double(y_4);
		y4 = Y4.intValue();
		MoveTo start = new MoveTo(endX, endY);
		LineTo line1 = new LineTo(x3, y3);
		LineTo line2 = new LineTo(x4, y4);
		LineTo line3 = new LineTo(endX, endY);
		arrow.getElements().addAll(start, line1, line2, line3);
	}

	// 计算

	private double[] rotateVec(double px, double py, double angle, boolean isChLen, double newLen) {

		// 矢量旋转函数，参数含义分别是x分量、y分量、旋转角、是否改变长度、新长度

		double mathstr[] = new double[2];

		// 计算完成后的(vx,vy)

		double vx = px * Math.cos(angle) - py * Math.sin(angle);

		double vy = px * Math.sin(angle) + py * Math.cos(angle);

		if (isChLen) {

			double d = Math.sqrt(vx * vx + vy * vy);

			vx = vx / d * newLen;

			vy = vy / d * newLen;

			mathstr[0] = vx;

			mathstr[1] = vy;

		}
		return mathstr;

	}
	
	
	public SpotLine() {
		initialize();
	}

	public SpotLine(LinkedList<Point2D> linePoints) {
		this.linePoints = linePoints;
		initialize();
	}

	@Override
	public boolean isElected() {
		return isElected;
	}

	@Override
	public void setElected(boolean isElected) {
		this.isElected = isElected;
	}

	@Override
	public boolean isTextFieldIsEleted() {
		// TODO Auto-generated method stub
		return TextFieldIsEleted;
	}

	@Override
	public void setTextFieldIsEleted(boolean textFieldIsEleted) {
		this.TextFieldIsEleted = textFieldIsEleted;
	}

	@Override
	public TextField getT() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Text getText() {
		return text;
	}

	@Override
	public void setText(Text text) {
		this.text = text;
	}

	@Override
	public boolean add(Symbol symbol) {

		return false;
	}

	@Override
	public boolean remove(Symbol symbol) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Symbol getSymbol(int index) {

		return null;
	}

	@Override
	public double getX() {

		return 0;
	}

	@Override
	public void setX(double x) {
		// TODO Auto-generated method stub

	}

	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setY(double y) {
		// TODO Auto-generated method stub

	}

	@Override
	public double getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setWidth(double width) {
		// TODO Auto-generated method stub

	}

	@Override
	public double getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setHeight(double height) {
		// TODO Auto-generated method stub

	}

	@Override
	public ChooseBox getcBox() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Circle[] getCircles() {
		return cBox;
	}

	@Override
	public Symbol clone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<LLine> getLines() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLines(ArrayList<LLine> lines) {

	}

	@Override
	public ArrayList<LLine> getInLine() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInLine(LLine line) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<LLine> getOutLine() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setOutLine(LLine line) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean canAddInLine() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canAddOutLine() {
		// TODO Auto-generated method stub
		return false;
	}

}
