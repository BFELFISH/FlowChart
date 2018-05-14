package symbols;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.activation.UnsupportedDataTypeException;
import javax.naming.InitialContext;

import control.ChooseBox;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class SpotLine implements Symbol, Serializable {

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

	private LinkedList<CCircle> circles = new LinkedList<CCircle>();

	private void initialize() {

	}

	private void updateLines() {

	}

	private void add(Point2D point2d) {
		CCircle circle = new CCircle(point2d.getX(), point2d.getY(), 10);
		circles.add(circle);

	}

	public LinkedList<CCircle> getCCircles() {
		return this.circles;
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
		// TODO Auto-generated method stub
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

		return null;
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

	@Override
	public boolean contains(Point2D p) {
		return false;
	}

	@Override
	public void setStroke(Paint Color) {

	}

	@Override
	public void setStrokeWidth(double width) {
		// TODO Auto-generated method stub

	}

}
