package application.config;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class Parameters {
    private Map<String, Double> ScaleParameters;
    private Map<String, Double> StructureParameters;
    private Map<String, String> SourceParameters;
    private Map<String, String> GradingParameters;

    private Map AllParameters;

    @SuppressWarnings("unchecked")
    public Parameters(InputStream inputStream) {
        Yaml yaml = new Yaml();
        AllParameters = yaml.loadAs(inputStream, Map.class);
        //TODO: ADD EXCEPTION PROCESS
        ScaleParameters = (Map<String,Double>)AllParameters.get("Scale");
        StructureParameters = (Map<String,Double>)AllParameters.get("Structure");
        SourceParameters = (Map<String,String>)AllParameters.get("Source");
        GradingParameters = (Map<String,String>)AllParameters.get("Grading");
    }

    public double getParameterOfScale(String itemName) {
        return ScaleParameters.get(itemName);
    }

    public double getParameterOfStructure(String itemName) {
        return StructureParameters.get(itemName);
    }

    public String getParameterOfGrading(String itemName) {
        return GradingParameters.get(itemName);
    }

    public String getParameterOfSource(String itemName) {
        return SourceParameters.get(itemName);
    }


    public double getDoubleParameter(String sectionName, String itemName) {
        Map<String, Double> sectionParameter= (Map<String, Double>)getDoubleSection(sectionName);
        return sectionParameter.get(itemName);
    }

    public String getStringParameter(String sectionName, String itemName) {
        Map<String, String> sectionParameter= getStringSection(sectionName);
        return sectionParameter.get(itemName);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        try{
            result.append("Scale:\n");
            for (Map.Entry<String, Double> entry : ScaleParameters.entrySet()) {
                result.append("\t"+entry.getKey()+" "+entry.getValue()+"\n");
            }
            result.append("Structure:\n");
            for (Map.Entry<String, Double> entry : StructureParameters.entrySet()) {
                result.append("\t"+entry.getKey()+" "+entry.getValue()+"\n");
            }
            result.append("Source:\n");
            for (Map.Entry<String, String> entry : SourceParameters.entrySet()) {
                result.append("\t"+entry.getKey()+" "+entry.getValue()+"\n");
            }
            result.append("Grading:\n");
            for (Map.Entry<String, String> entry : GradingParameters.entrySet()) {
                result.append("\t"+entry.getKey()+" "+entry.getValue()+"\n");
            }
        }catch (NullPointerException e)
        {
            //Ignore
        }
        return result.toString();
    }

    @SuppressWarnings("unchecked")
    private Map<String, String> getStringSection(String sectionName) {
        return (Map<String, String>)AllParameters.get(sectionName);
    }

    @SuppressWarnings("unchecked")
    private Map<String, Double> getDoubleSection(String sectionName) {
        return (Map<String, Double>)AllParameters.get(sectionName);
    }

}
