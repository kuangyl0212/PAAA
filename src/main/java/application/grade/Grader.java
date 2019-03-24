package application.grade;

import application.builder.CDGBuilder;
import graph.CDG;

import java.io.File;
import java.io.FileNotFoundException;

public class Grader {
    public static double grade(File sourceFile, CDG ansCDG) throws FileNotFoundException {
        CDGBuilder builder = new CDGBuilder();
        CDG thisCDG = builder.buildFrom(sourceFile);
        double scaleScore = ScaleMatcher.grade(ansCDG, thisCDG);
        StructureMatcher structureMatcher = new StructureMatcher(ansCDG, thisCDG);
        double structureScore = structureMatcher.grade();
        return scaleScore + structureScore;
    }
}
