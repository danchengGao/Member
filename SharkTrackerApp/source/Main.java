
import controller.Controller;
import model.SharkModel;
import view.MainFrame;
import view.StatisticsFrame;

public class Main {

	public static void main (String[] args) {
		MainFrame frame = new MainFrame();
		Controller controller = new Controller(frame);
	}
}
