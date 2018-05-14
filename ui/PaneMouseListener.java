package ui;

import javafx.event.EventType;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;

public class PaneMouseListener extends MouseEvent{

	
	public PaneMouseListener(EventType<? extends MouseEvent> eventType, double x, double y, double screenX,
			double screenY, MouseButton button, int clickCount, boolean shiftDown, boolean controlDown, boolean altDown,
			boolean metaDown, boolean primaryButtonDown, boolean middleButtonDown, boolean secondaryButtonDown,
			boolean synthesized, boolean popupTrigger, boolean stillSincePress, PickResult pickResult) {
		super(eventType, x, y, screenX, screenY, button, clickCount, shiftDown, controlDown, altDown, metaDown,
				primaryButtonDown, middleButtonDown, secondaryButtonDown, synthesized, popupTrigger, stillSincePress,
				pickResult);
	}

}
