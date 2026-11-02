import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public String destCity(List<List<String>> paths) {
        Set<String> outgoingCities = new HashSet<>();
        
        for (List<String> path : paths) {
            outgoingCities.add(path.get(0));
        }
        
        for (List<String> path : paths) {
            String destination = path.get(1);
            if (!outgoingCities.contains(destination)) {
                return destination;
            }
        }
        
        return "";
    }
}
