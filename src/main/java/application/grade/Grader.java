package application.grade;

import application.builder.CDGBuilder;
import application.config.DefaultConf;
import application.config.LANG;
import application.config.Parameters;
import application.config.SELECTOR;
import graph.CDG;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static application.util.file.Misc.getFileList;
import static application.util.file.Misc.readStringFrom;

public class Grader {

    private LANG currentLanguage = DefaultConf.getDefaultLANG();
    private SELECTOR currentSelector = SELECTOR.valueOf(DefaultConf.getDefaultParameters().getParameterOfGrading("selector"));
    private String templateInput = DefaultConf.getDefaultParameters().getParameterOfSource("templateInput");
    private String studentInput = DefaultConf.getDefaultParameters().getParameterOfSource("studentInput");
    //Not needed now. Output is used in main function.
    //private String output = DefaultConf.getDefaultParameters().getParameterOfSource("output");
    private Parameters parameters;
    private double fullScore = DefaultConf.getDefaultParameters().getDoubleParameter("Grading", "fullScore");

    public Grader(Parameters parametersOfCurrentSession) {
        //TODO: Exception process here
        parameters = parametersOfCurrentSession;
        String currentLanguageString = parametersOfCurrentSession.getParameterOfGrading("language");
        currentLanguage = LANG.valueOf(currentLanguageString);
        currentSelector = SELECTOR.valueOf(parametersOfCurrentSession.getParameterOfGrading("selector"));
        templateInput = parametersOfCurrentSession.getParameterOfSource("templateInput");
        studentInput = parametersOfCurrentSession.getParameterOfSource("studentInput");
        //output = parametersOfCurrentSession.getParameterOfSource("output");
        fullScore = parametersOfCurrentSession.getDoubleParameter("Grading", "fullScore");
    }

    /**
     * Grader.assess (Entrance of Grader)
     * @return the result of grading
     * @throws FileNotFoundException throws when source code is not found.
     */
    public Map<String, Double> assess() throws FileNotFoundException {
        HashMap<String, Double> result = new HashMap<>();
        //Generate template CDG
        ArrayList<CDG> tCDGs = new ArrayList<>();
        for (File tFile : getFileList(templateInput)) {
            tCDGs.add(CDGBuilder.buildFrom(currentLanguage,readStringFrom(tFile)));
        }
        //Generate student's CDG and assess them.
        for (File sFile : getFileList(studentInput)) {
            CDG sCDG = CDGBuilder.buildFrom(currentLanguage,readStringFrom(sFile));
            //Assess each student's code, then put it into result(HashMap).
            result.put(sFile.getName().split("\\.")[0],grade(sCDG, tCDGs));
        }
        return result;
    }

    /**
     * Score selector.
     * Select a score from arrayOfScore, according to the parameter "selector".
     * @param arrayOfScore source array of score.
     * @return the final score
     */
    private double scoreSelector(Double[] arrayOfScore) {
        double finalScore;
        finalScore = arrayOfScore[0];
        switch (currentSelector) {
            case MAX:
                for (double eachScore : arrayOfScore) {
                    if (eachScore > finalScore) {
                        finalScore=eachScore;
                    }
                }
                return finalScore;
            case MIN:
                for (double eachScore : arrayOfScore) {
                    if (eachScore < finalScore) {
                        finalScore=eachScore;
                    }
                }
                return finalScore;
            case MEAN:
                finalScore = 0;
                for (double eachScore : arrayOfScore) {
                    finalScore+=eachScore;
                }
                finalScore /= arrayOfScore.length;
                return finalScore;
        }
        return 0.0;
    }

    /**
     * Assess one student's code with several template code files.
     * @param sCDG CDG generated from student's code
     * @param tCDGs CDGs generated from template code
     * @return the score of student
     */
    private double grade(CDG sCDG, ArrayList<CDG> tCDGs) {
        ArrayList<Double> candidateScore = new ArrayList<Double>();
        for (CDG tCDG : tCDGs) {
            double scaleScore = ScaleMatcher.grade(sCDG, tCDG, parameters);
            StructureMatcher structureMatcher = new StructureMatcher(sCDG, tCDG);
            double structureScore = structureMatcher.grade(parameters);
            candidateScore.add(scaleScore + structureScore);
        }
        Double[] scores = candidateScore.toArray(new Double[0]);
        return scoreSelector(scores)*fullScore;
    }

    /**
     * Original entrance of grader, deprecated now.
     */
    @Deprecated
    public static double grade(File sourceFile, CDG ansCDG) throws FileNotFoundException {
        CDGBuilder builder = new CDGBuilder();
        CDG thisCDG = CDGBuilder.buildFrom(sourceFile);
        double scaleScore = ScaleMatcher.grade(ansCDG, thisCDG);
        StructureMatcher structureMatcher = new StructureMatcher(ansCDG, thisCDG);
        //double structureScore = structureMatcher.grade(parameters);
        return scaleScore ;//+ structureScore;
    }
}
