package application.grade;

import application.config.Parameters;
import graph.CDG;
import graph.VertexType;

import static application.config.DefaultConf.getDefaultParameters;

public class ScaleMatcher {
    private double scaleBase;
    private double funcDef;             /* function definition          */
    private double declaration;          /* declaration                  */
    private double block;                /* block statement list         */
    private double exprStat;            /* expression statement         */
    private double iterStat;            /* iteration statement          */
    private double jumpStat;            /* jump statement               */
    private double selectStat;             /* selection statement          */
    private double selectClause;           /* selection clause             */
    private double structSpec;          /* struct or union specifier    */
    private double structDeclList;      /* struct declaration list      */
    private double structDecl;           /* struct declaration   */

    /**
     * Scale Matcher Constructor
     * Set every parameter of PAAA Scale Matcher.
     * You can create different sessions for different parameters.
     * @param parametersOfCurrentSession Parameter of Current Session
     */
    private ScaleMatcher(Parameters parametersOfCurrentSession) {
        scaleBase = parametersOfCurrentSession.getParameterOfScale("scaleBase");

        funcDef = parametersOfCurrentSession.getParameterOfScale("funcDef");
        declaration = parametersOfCurrentSession.getParameterOfScale("declaration");
        block = parametersOfCurrentSession.getParameterOfScale("block");
        exprStat = parametersOfCurrentSession.getParameterOfScale("exprStat");
        iterStat = parametersOfCurrentSession.getParameterOfScale("iterStat");
        jumpStat = parametersOfCurrentSession.getParameterOfScale("jumpStat");
        selectStat = parametersOfCurrentSession.getParameterOfScale("selectStat");
        selectClause = parametersOfCurrentSession.getParameterOfScale("selectClause");
        structSpec = parametersOfCurrentSession.getParameterOfScale("structSpec");
        structDeclList = parametersOfCurrentSession.getParameterOfScale("structDeclList");
        structDecl = parametersOfCurrentSession.getParameterOfScale("structDecl");
    }

    @Deprecated
    public static double grade(CDG tmpCDG, CDG stuCDG) {
        Scale tmpScale = new Scale(tmpCDG), stuScale = new Scale(stuCDG);
        ScaleMatcher matcher = new ScaleMatcher(getDefaultParameters());
        return matcher.getAllDistance(tmpScale, stuScale);
    }

    /**
     * Scale Grading Result
     * @param tCDG template code dependency graph
     * @param sCDG students' code dependency graph
     * @param parametersOfCurrentSession Parameters of Current Session
     * @return Scale Grading Result
     */
    public static double grade(CDG tCDG, CDG sCDG, Parameters parametersOfCurrentSession) {
        Scale tmpScale = new Scale(tCDG), stuScale = new Scale(sCDG);
        ScaleMatcher matcher = new ScaleMatcher(parametersOfCurrentSession);
        return matcher.getAllDistance(tmpScale, stuScale);
    }

    private double getAllDistance(Scale tmpScale, Scale stuScale) {
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
        score += selectStat * getOneTypeDistance(
                tmpScale.get(VertexType.SEL_STAT),
                stuScale.get(VertexType.SEL_STAT));
        score += selectClause * getOneTypeDistance(
                tmpScale.get(VertexType.SEL_CLAUSE),
                stuScale.get(VertexType.SEL_CLAUSE));
        score += structSpec * getOneTypeDistance(
                tmpScale.get(VertexType.STRUCT_SPEC),
                stuScale.get(VertexType.STRUCT_SPEC));
        score += structDeclList * getOneTypeDistance(
                tmpScale.get(VertexType.STRUCT_DEC_LIST),
                stuScale.get(VertexType.STRUCT_DEC_LIST));
        score += selectClause * getOneTypeDistance(
                tmpScale.get(VertexType.STRUCT_DEC),
                stuScale.get(VertexType.STRUCT_DEC));
        return score;
    }

    private void reapportionParameters(Scale tmpScale) {
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
            selectStat = 0;
        if (tmpScale.get(VertexType.SEL_CLAUSE) <= 0)
            selectClause = 0;
        if (tmpScale.get(VertexType.STRUCT_SPEC) <= 0)
            structSpec = 0;
        if (tmpScale.get(VertexType.STRUCT_DEC_LIST) <= 0)
            structDeclList = 0;
        if (tmpScale.get(VertexType.STRUCT_DEC) <= 0)
            structDecl = 0;
        double newPortionBase = getPortionBase();
        funcDef /= newPortionBase;
        declaration /= newPortionBase;
        block /= newPortionBase;
        exprStat /= newPortionBase;
        iterStat /= newPortionBase;
        jumpStat /= newPortionBase;
        selectStat /= newPortionBase;
        selectClause /= newPortionBase;
        structSpec /= newPortionBase;
        structDeclList /= newPortionBase;
        structDecl /= newPortionBase;
    }

    private double getPortionBase() {
        return funcDef + declaration + block + exprStat
                + iterStat + jumpStat + selectStat + selectClause
                + structSpec + structDeclList + structDecl;
    }

    private  double getOneTypeDistance(int numTmp, int numStu) {
        if (0 == numStu) return 0;
        /* the return value should always less than 1 */
        if (numTmp < numStu) {
            return scaleBase * (double) numTmp / numStu;
        }
        return scaleBase * (double) numStu / numTmp;
    }
}
