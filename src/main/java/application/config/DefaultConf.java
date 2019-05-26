package application.config;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class DefaultConf {
    private static LANG LAN = LANG.PYTHON;              /* */

    private static InputStream defaultParametersInputStream = new ByteArrayInputStream((
            "Scale:\n" +
            "   scaleBase: 0.3\n" +
            "   funcDef: 10.0\n" +
            "   declaration: 10.0\n" +
            "   block: 10.0\n" +
            "   exprStat: 10.0\n" +
            "   iterStat: 10.0\n" +
            "   jumpStat: 10.0\n" +
            "   selectStat: 10.0\n" +
            "   selectClause: 10.0\n" +
            "   structSpec: 10.0\n" +
            "   structDeclList: 10.0\n" +
            "   structDecl: 10.0\n" +
            "Structure:\n" +
            "   structureBase: 0.7\n" +
            "   iteration: 10.0\n" +
            "   selection: 10.0\n" +
            "   funcDef: 10.0\n" +
            "   other: 1.0\n" +
            "Source:\n" +
            "   templateInput: /home/amazingrise/Coding/PAAATest/template/\n" +
            "   studentInput: /home/amazingrise/Coding/PAAATest/0430night/2/\n" +
            "   output: /home/amazingrise/Coding/PAAATest/0430night2.csv\n" +
            "Grading:\n" +
            "   language: PYTHON\n" +
            "   selector: MAX\n" +
            "   fullScore: 100.0\n" +
            "").getBytes());
    private static Parameters defaultParameters = new Parameters(defaultParametersInputStream);

    public static Parameters getDefaultParameters() {
        return defaultParameters;
    }

    @Deprecated
    public static void setLANG(LANG LAN) {
        DefaultConf.LAN = LAN;
    }

    public static LANG getDefaultLANG() {
        return LAN;
    }
}
