package application.grade;

import static application.config.scale.Parameter.*;
import graph.CDG;
import graph.VertexType;

public class ScaleMatcher {
    public static double funcDef          = FUNC_DEF;             /* function definition          */
    public static double declaration      = DECLARATION;          /* declaration                  */
    public static double block            = BLOCK;                /* block statement list         */
    public static double exprStat         = EXPR_STAT;            /* expression statement         */
    public static double iterStat         = ITER_STAT;            /* iteration statement          */
    public static double jumpStat         = JUMP_STAT;            /* jump statement               */
    public static double selStat          = SEL_STAT;             /* selection statement          */
    public static double selClause        = SEL_CLAUSE;           /* selection clause             */
    public static double structSpec       = STRUCT_SPEC;          /* struct or union specifier    */
    public static double structDecList    = STRUCT_DEC_LIST;      /* struct declaration list      */
    public static double structDec        = STRUCT_DEC;           /* struct declaration   */

    public static double grade(CDG tmpCDG, CDG stuCDG) {
        Scale tmpScale = new Scale(tmpCDG), stuScale = new Scale(stuCDG);
        return getAllDistance(tmpScale, stuScale);
    }

    private static double getAllDistance(Scale tmpScale, Scale stuScale) {
        reapportionParameters(tmpScale);
        double score = 0;
        score += funcDef * getOneTypeDistance(
                tmpScale.get(VertexType.FUNC_DEF),
                stuScale.get(VertexType.FUNC_DEF));
        score += declaration * getOneTypeDistance(
                tmpScale.get(VertexType.DECLARATION),
                stuScale.get(VertexType.DECLARATION));
        score += block * getOneTypeDistance(
                tmpScale.get(VertexType.BLOCK),
                stuScale.get(VertexType.BLOCK));
        score += exprStat * getOneTypeDistance(
                tmpScale.get(VertexType.EXPR_STAT),
                stuScale.get(VertexType.EXPR_STAT));
        score += iterStat * getOneTypeDistance(
                tmpScale.get(VertexType.ITER_STAT),
                stuScale.get(VertexType.ITER_STAT));
        score += jumpStat * getOneTypeDistance(
                tmpScale.get(VertexType.JUMP_STAT),
                stuScale.get(VertexType.JUMP_STAT));
        score += selStat * getOneTypeDistance(
                tmpScale.get(VertexType.SEL_STAT),
                stuScale.get(VertexType.SEL_STAT));
        score += selClause * getOneTypeDistance(
                tmpScale.get(VertexType.SEL_CLAUSE),
                stuScale.get(VertexType.SEL_CLAUSE));
        score += structSpec * getOneTypeDistance(
                tmpScale.get(VertexType.STRUCT_SPEC),
                stuScale.get(VertexType.STRUCT_SPEC));
        score += structDecList * getOneTypeDistance(
                tmpScale.get(VertexType.STRUCT_DEC_LIST),
                stuScale.get(VertexType.STRUCT_DEC_LIST));
        score += selClause * getOneTypeDistance(
                tmpScale.get(VertexType.STRUCT_DEC),
                stuScale.get(VertexType.STRUCT_DEC));
        return score;
    }

    private static void reapportionParameters(Scale tmpScale) {
        if (tmpScale.get(VertexType.FUNC_DEF) <= 0)
            funcDef = 0;
        if (tmpScale.get(VertexType.DECLARATION) <= 0)
            declaration = 0;
        if (tmpScale.get(VertexType.BLOCK) <= 0)
            block = 0;
        if (tmpScale.get(VertexType.EXPR_STAT) <= 0)
            exprStat = 0;
        if (tmpScale.get(VertexType.ITER_STAT) <= 0)
            iterStat = 0;
        if (tmpScale.get(VertexType.JUMP_STAT) <= 0)
            jumpStat = 0;
        if (tmpScale.get(VertexType.SEL_STAT) <= 0)
            selStat = 0;
        if (tmpScale.get(VertexType.SEL_CLAUSE) <= 0)
            selClause = 0;
        if (tmpScale.get(VertexType.STRUCT_SPEC) <= 0)
            structSpec = 0;
        if (tmpScale.get(VertexType.STRUCT_DEC_LIST) <= 0)
            structDecList = 0;
        if (tmpScale.get(VertexType.STRUCT_DEC) <= 0)
            structDec = 0;
        double newPortionBase = getPortionBase();
        funcDef /= newPortionBase;
        declaration /= newPortionBase;
        block /= newPortionBase;
        exprStat /= newPortionBase;
        iterStat /= newPortionBase;
        jumpStat /= newPortionBase;
        selStat /= newPortionBase;
        selClause /= newPortionBase;
        structSpec /= newPortionBase;
        structDecList /= newPortionBase;
        structDec /= newPortionBase;
    }

    private static double getPortionBase() {
        return funcDef + declaration + block + exprStat
                + iterStat + jumpStat + selStat + selClause
                + structSpec + structDecList + structDec;
    }

    private static double getOneTypeDistance(int numTmp, int numStu) {
        if (0 == numStu) return 0;
        /* the return value should always less than 1 */
        if (numTmp < numStu) {
            return SCALE_BASE * (double) numTmp / numStu;
        }
        return SCALE_BASE * (double) numStu / numTmp;
    }
}
