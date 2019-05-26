package application.config;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class ScaleParameterConfigurationTest {
    @Test
    public void YamlTester(){
        String testYamlConfiguration = "Scale:\n" +
                "   Func: 1.0\n" +
                "   Expr: 2.0\n";
        InputStream input = new ByteArrayInputStream(testYamlConfiguration.getBytes(StandardCharsets.UTF_8));
        Parameters ScaleParameter = new Parameters(input);
        System.out.println(ScaleParameter.toString());
    }
}
