import static org.junit.jupiter.api.Assertions.assertEquals;
import javafx.scene.control.Button;

import org.FullProject.HelloController;
import org.junit.jupiter.api.Test;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;
import java.util.Objects;

public class InterfaceTest {

    @Test
    public void testSwitchButton() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
        HelloController controller = new HelloController();
        controller.initialize();
        Button switchButton = (Button) root.lookup("#switchButton");
        switchButton.fire();
        assertEquals("Switch to Login", switchButton.getText());
    }
}

