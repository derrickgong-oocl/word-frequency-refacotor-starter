import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    public String getResult(String inputStr){

        if (inputStr.split("\\s+").length==1) {
            return inputStr + " 1";
        } else {

            try {

                //split the input string with 1 to n pieces of spaces
                String[] arr = inputStr.split("\\s+");

                List<Input> inputList;

                //get the map for the next step of sizing the same word

                inputList = getInputs(arr);

                inputList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner joiner = getStringJoiner(inputList);

                return joiner.toString();
            } catch (Exception e) {

                return "Calculate Error";
            }
        }
    }

    private StringJoiner getStringJoiner(List<Input> inputList) {
        StringJoiner joiner = new StringJoiner("\n");
        inputList.stream()
                .map(w -> w.getValue() + " " + w.getWordCount())
                .forEach(joiner::add);
        return joiner;
    }

    private List<Input> getInputs(String[] arr) {
        List<Input> inputList;
        inputList = getListMap(Arrays.stream(arr)
                .map(s -> new Input(s, 1))
                .collect(Collectors.toList())).entrySet().stream()
                .map(entry -> new Input(entry.getKey(), entry.getValue().size()))
                .collect(Collectors.toList());
        return inputList;
    }


    private Map<String,List<Input>> getListMap(List<Input> inputList) {
        Map<String, List<Input>> map = inputList.stream()
                .collect(Collectors.groupingBy(Input::getValue));
        return map;
    }


}