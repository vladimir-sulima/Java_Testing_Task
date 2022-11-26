import java.util.*;

public class Main {
    public static void main(String[] args) {
        String result = getCharOccurrences("teste", "teae");
    }
    private static String getCharOccurrences(String templateString, String stringToAnalyse) {

        if (templateString == null || templateString.isEmpty() || templateString.trim().isEmpty()){
            return "Template string is null or empty. It is not possible to compare with undefined value";
        }
        if (stringToAnalyse == null || stringToAnalyse.isEmpty() || stringToAnalyse.trim().isEmpty()){
            return "String to analyse is null or empty. It is not possible to compare with undefined value";
        }
        else {
            char[] templateCharArray = templateString.toCharArray();
            char[] charArrayToAnalyse = stringToAnalyse.toCharArray();

            List<Character> uniqueTemplateCharValues= getUniqueCharArray(templateCharArray);
            HashMap<Character, Integer> unsortedHashMapResult = getNumberOfOccurrence(uniqueTemplateCharValues, charArrayToAnalyse);

            HashMap<Character, Integer> sortedHashMap = sortHashMapByValue(unsortedHashMapResult);

            return combineResultString(sortedHashMap);
        }
    }
    private static List<Character> getUniqueCharArray(char[] templateCharArray) {

        List<Character> uniqueTemplateCharValues = new ArrayList<>();

        for (char character : templateCharArray) {
            if (!uniqueTemplateCharValues.contains(character)) {
                uniqueTemplateCharValues.add(character);
            }
        }

        return uniqueTemplateCharValues;
    }
    private static HashMap<Character, Integer> getNumberOfOccurrence(List<Character> uniqueTemplateCharValues, char[] charArrayToAnalyse) {
        HashMap<Character, Integer> unsortedHashMapResult = new HashMap<Character, Integer>();
        for (char valueToCompareWith : uniqueTemplateCharValues) {
            for (char valueToAnalyse : charArrayToAnalyse) {
                if (valueToCompareWith == valueToAnalyse) {
                    if (unsortedHashMapResult.containsKey(valueToAnalyse)) {
                        unsortedHashMapResult.put(valueToCompareWith, unsortedHashMapResult.get(valueToCompareWith) + 1);
                    } else {
                        unsortedHashMapResult.put(valueToCompareWith, 1);
                    }
                }
            }
        }

        return unsortedHashMapResult;
    }
    public static HashMap<Character, Integer> sortHashMapByValue(HashMap<Character, Integer> hashMapToSort)
    {
        List<Map.Entry<Character, Integer> > list =
                new LinkedList<Map.Entry<Character, Integer> >(hashMapToSort.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<Character, Integer> >() {
            public int compare(Map.Entry<Character, Integer> o1,
                               Map.Entry<Character, Integer> o2)
            {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        HashMap<Character, Integer> sortedHashMap = new LinkedHashMap<Character, Integer>();
        for (Map.Entry<Character, Integer> entry : list) {
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }

        return sortedHashMap;
    }
    private static String combineResultString(HashMap<Character, Integer> sortedHashMap) {
        String result = "";

        for (Map.Entry<Character, Integer> entry : sortedHashMap.entrySet()) {
            result = result + entry.getKey() + entry.getValue();
        }

        return result;
    }
}