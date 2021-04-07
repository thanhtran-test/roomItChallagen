import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BarrenLandTest {
    @Test
    public void testBarrenLandAnalysis(){
        String[] test = {"0 292 399 307"};
        assertEquals("116800 116800", BarrenLand.barrenLandAnalysis(test));
    }


}
