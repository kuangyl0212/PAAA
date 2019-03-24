package application.grade;

import graph.CDG;
import graph.Vertex;
import graph.VertexType;

import java.util.ArrayList;

import static graph.VertexType.*;
import static application.config.structure.Parameter.*;

class VertexTypeCount {
    int iterations = 0;
    int selections = 0;
    int functionDef = 0;
    int other = 0;

    @Override
    public String toString() {
        return "iterations: \t" + iterations + "\n"
                + "selections: \t" + selections + "\n"
                + "functionDef: \t" + functionDef + "\n"
                + "other: \t\t\t" + other + "\n";
    }
}

class Parameter {
    double ITERATION;
    double SELECTION;
    double FUNCTION_DEF;
    double OTHER;
}

public class StructureMatcher {

    private CDG paired;
    private int vertexMatched;
    private VertexTypeCount matchedTypeCount;
    private VertexTypeCount vertexTypeCountOfStd;
    private CDG CDG_NormalOfStd;
    private CDG CDG_NormalOfStu;
    private Parameter parameter;

    /**
     *
     * Please note that the order of these two param do matters the result!
     * @param CDG_OfStd The graph generated from standard answer
     * @param CDG_OfStu The graph generated from student's answer
     */
    public StructureMatcher(CDG CDG_OfStd, CDG CDG_OfStu) {

        StructureExtractor extractorOfStd = new StructureExtractor(CDG_OfStd);
        StructureExtractor extractorOfStu = new StructureExtractor(CDG_OfStu);

        CDG_NormalOfStd = extractorOfStd.get();
        CDG_NormalOfStu = extractorOfStu.get();

        vertexTypeCountOfStd = countVertexTypeOf(CDG_NormalOfStd);

        paired = new CDG();

        /*
         * the top recursion of matchStuWithStd does not add HEAD to `paired
         * while every CDG has a HEAD
        **/
        paired.addVertex(new Vertex(HEAD));
        Vertex headOfStd = CDG_NormalOfStd.getHead();
        Vertex headOfStu = CDG_NormalOfStu.getHead();
        vertexMatched = matchStuWithStd(headOfStu, headOfStd);

        matchedTypeCount = countVertexTypeOf(paired);

    }

    public double grade() {
        initParameter();

        double result = 0;
        if (vertexTypeCountOfStd.iterations > 0)
            result += parameter.ITERATION
                        * ((double) matchedTypeCount.iterations
                            / vertexTypeCountOfStd.iterations);
        if (vertexTypeCountOfStd.selections > 0)
            result += parameter.SELECTION
                    * ((double) matchedTypeCount.selections
                    / vertexTypeCountOfStd.selections);
        if (vertexTypeCountOfStd.functionDef > 0)
            result += parameter.FUNCTION_DEF
                    * ((double) matchedTypeCount.functionDef
                    / vertexTypeCountOfStd.functionDef);
        if (vertexTypeCountOfStd.other > 0)
            result += parameter.OTHER
                    * ((double) matchedTypeCount.other
                    / vertexTypeCountOfStd.other);

        return STRUCTURE_BASE * result;
    }

    int howManyVertexesMatched() {
        return vertexMatched;
    }

    CDG whichVertexesMatched() {
        return paired;
    }

    VertexTypeCount howManyMatchedOfEachType() {
        return matchedTypeCount;
    }

    private VertexTypeCount countVertexTypeOf(CDG normalCDG) {
        VertexTypeCount typeCount = new VertexTypeCount();
        for (Vertex v : normalCDG.vertexSet()) {
            VertexType type = v.getType();
            switch (type) {
                case ITER_STAT:
                    typeCount.iterations++;
                    break;
                case SEL_STAT:
                case SEL_CLAUSE:
                    typeCount.selections++;
                    break;
                case FUNC_DEF:
                    typeCount.functionDef++;
                    break;
                case NODE:
                    typeCount.other++;
                    break;
                default:
                    break;
            }
        }
        return typeCount;
    }

    private int matchStuWithStd(Vertex vertexStu, Vertex vertexStd) {

        /* dynamic programming */

        if (vertexStu.getType() != vertexStd.getType())     // base condition
            return 0;

        /* normal condition */
        ArrayList<Vertex> childrenOfStu = CDG_NormalOfStu.getChildrenOf(vertexStu);
        ArrayList<Vertex> childrenOfStd = CDG_NormalOfStd.getChildrenOf(vertexStd);
        int childrenNumOfStu = childrenOfStu.size();
        int childrenNumOfStd = childrenOfStd.size();
        int[][] M = new int[childrenNumOfStu+1][childrenNumOfStd+1];
        int[][] W = new int[childrenNumOfStu+1][childrenNumOfStd+1];

        for (int i = 0; i <= childrenNumOfStu; i++) M[i][0] = 0;
        for (int j = 0; j <= childrenNumOfStd; j++) M[0][j] = 0;

        for (int i = 1; i <= childrenNumOfStu; i++)
            for (int j = 1; j <= childrenNumOfStd; j++) {
                Vertex i_childOfStu = childrenOfStu.get(i-1);
                Vertex j_childOfStd = childrenOfStd.get(j-1);

                W[i][j] = matchStuWithStd(i_childOfStu, j_childOfStd);
                M[i][j] = max(M[i][j-1], M[i-1][j], M[i-1][j-1]+W[i][j]);

                if (M[i][j] > M[i - 1][j - 1]
                        && M[i][j] > M[i - 1][j]
                        && M[i][j] > M[i][j - 1])
                    paired.addVertex(j_childOfStd);
            }

        return M[childrenNumOfStu][childrenNumOfStd]+1;

    }

    private int max(int a, int b, int c) {
        return max(a, max(b, c));
    }

    private int max(int a, int b) {
        return a >= b ? a : b;
    }

    private void initParameter() {
        parameter = new Parameter();
        parameter.ITERATION =
                vertexTypeCountOfStd.iterations == 0 ? 0 : ITERATION;
        parameter.SELECTION =
                vertexTypeCountOfStd.selections == 0 ? 0 : SELECTION;
        parameter.FUNCTION_DEF =
                vertexTypeCountOfStd.functionDef == 0 ? 0 : FUNCTION_DEF;
        parameter.OTHER =
                vertexTypeCountOfStd.other == 0 ? 0 : OTHER;

        double parameterSum = parameter.ITERATION
                                + parameter.SELECTION
                                + parameter.FUNCTION_DEF
                                + parameter.OTHER;
        parameter.ITERATION /= parameterSum;
        parameter.SELECTION /= parameterSum;
        parameter.FUNCTION_DEF /= parameterSum;
        parameter.OTHER /= parameterSum;
    }
}
