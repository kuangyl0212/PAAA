package application.grade.pygrade;

import application.builder.CDGBuilder;
import application.config.DefaultConf;
import application.config.LANG;
import application.grade.Grader;
import application.util.file.Misc;
import graph.CDG;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class Score {
    String ID;
    double score;

    Score(String ID, double score) {
        this.ID = ID;
        this.score = score;
    }

    Score(String ID) {
        this(ID, 0);
    }

    @Override
    public String toString() {
        return ID + "\t" + score;
    }
}

public class AllInOneTest {
    private CDG[] cdgAns;
    private File filesRoot;
    private String fileRootName;
    private ArrayList<Score> scoresMachine;
    private TreeMap<String, Double> thisScore;

    final static int numOfQuest = 2;
    final static int[] totalScore = new int[]{5, 10};

    AllInOneTest() throws FileNotFoundException {
        DefaultConf.setLANG(LANG.PYTHON);

        fileRootName = "src/test/resources/py-source-a/";
        File ans1File = new File(
                fileRootName + "answer1a.py");
        File ans2File = new File(
                fileRootName +"answer2a.py");

        CDGBuilder builder = new CDGBuilder();
        cdgAns = new CDG[numOfQuest];
        cdgAns[0] = builder.buildFrom(ans1File);
        cdgAns[1] = builder.buildFrom(ans2File);
        filesRoot = new File(fileRootName);
        scoresMachine = new ArrayList<>();
        thisScore = new TreeMap<>();
    }

    void readScore() throws FileNotFoundException {
        String scoreString = Misc.readStringFrom(
                fileRootName + "scores_machine.txt");
        String[] lines = scoreString.split("\r\n|\r|\n");
        for (String line :
                lines) {
            String[] scoreSplit = line.split("\t");
            Score score = new Score(scoreSplit[0], Double.parseDouble(scoreSplit[1]));
            scoresMachine.add(score);
        }
        for (Score score :
                scoresMachine) {
            System.out.println(score);
        }
    }

    void gradeAll() throws FileNotFoundException {
        File[] files = filesRoot.listFiles();
        for (File file : files) {
            if (isSourceFile(file)) {
                String ID = getID(file);
                int questionNo = getQuestionNo(file);
                double score = totalScore[questionNo - 1]
                        * Grader.grade(file, cdgAns[questionNo - 1]);
                if (thisScore.containsKey(ID))
                    thisScore.replace(ID, thisScore.get(ID) + score);
                else
                    thisScore.put(ID, score);
            }
        }

    }
    boolean isSourceFile(File file) {
        return file.isFile() && file.getName().startsWith("0");
    }

    int getQuestionNo(File file) {
        return Integer.parseInt(file.getName().substring(17,18));
    }

    String getID(File file) {
        return file.getName().substring(0, 3);
    }

    String printThisScore() {
        StringBuilder scores = new StringBuilder();
        for (String key : thisScore.keySet()) {
            String ID = String.valueOf(Integer.parseInt(key));
            String score = String.format("%.2f", thisScore.get(key));
            scores.append(ID).append("\t").append(score).append("\n");
        }
        return scores.toString();
    }

    @Test
    void testAll() throws FileNotFoundException {
        File sourceFile = new File(
                "src/test/resources/py-source-a/006py_source_file1.py");
        final File filesRoot = new File(
                "src/test/resources/py-source-a/");
        final File[] files = filesRoot.listFiles();
//        readScore();
        gradeAll();
        String actual = printThisScore();
        String expected = Misc.readStringFrom(
                fileRootName + "scores_machine.txt");
        assertEquals(expected, actual);
    }

}
