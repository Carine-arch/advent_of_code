package year_15.day_9;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class City {

    private String name;
    private final Map<String, Integer> adjacentCities = new HashMap<>();

    public void addAdjacentCity(String city, Integer distance) {
        adjacentCities.put(city, distance);
    }

    public Map<String, Integer> getAdjacentCities() {
        return adjacentCities;
    }

    public City(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getDistanceByCityName(String cityName) {
        return adjacentCities.entrySet().stream()
                .filter(entry -> entry.getKey().equals(cityName))
                .findFirst()
                .map(Map.Entry::getValue)
                .orElseThrow();
    }

    public Map<String, Integer> filterAdjacentCitiesByCityNames(List<String> cityNames) {
        return adjacentCities.entrySet().stream()
                .filter(entry -> cityNames.contains(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public String getCloserCityFromListOfCities(List<String> cityNames) {
        return filterAdjacentCitiesByCityNames(cityNames).entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public String getFurtherCityFromListOfCities(List<String> cityNames) {
        return filterAdjacentCitiesByCityNames(cityNames).entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

}
