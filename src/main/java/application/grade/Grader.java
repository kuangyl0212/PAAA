package application.grade;

import application.builder.CDGBuilder;
import graph.CDG;

import java.io.File;
import java.io.FileNotFoundException;

public class Grader {

    /**
     * 将答案源程序和模板程序的程序依赖图对比对答案进行评分
     * @param sourceFile    源程序文件
     * @param ansCDG        模板程序对应的程序依赖图
     * @return
     * @throws FileNotFoundException    文件不存在时抛出异常
     */
    public static double grade(File sourceFile, CDG ansCDG) throws FileNotFoundException {
        CDGBuilder builder = new CDGBuilder();
        CDG thisCDG = builder.buildFrom(sourceFile);
        double scaleScore = ScaleMatcher.grade(ansCDG, thisCDG);
        StructureMatcher structureMatcher = new StructureMatcher(ansCDG, thisCDG);
        double structureScore = structureMatcher.grade();
        return scaleScore + structureScore;
    }
}
