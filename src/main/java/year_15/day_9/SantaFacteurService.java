package year_15.day_9;

import utils.PuzzleInputUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SantaFacteurService {

    public int part1(String filename) {
        List<City> cities = prepareData(filename);

        List<Integer> results = new ArrayList<>();
        cities.forEach(city -> {
            List<City> unVisitedCities = new ArrayList<>(cities);
            unVisitedCities.remove(city);
            results.add(calculateMinDistance(city, unVisitedCities, 0));
        });
        System.out.println("results " + results);
        return results.stream().reduce(Integer::min).orElseThrow();
    }

    public int part2(String filename) {
        List<City> cities = prepareData(filename);

        List<Integer> results = new ArrayList<>();
        cities.forEach(city -> {
            List<City> unVisitedCities = new ArrayList<>(cities);
            unVisitedCities.remove(city);
            results.add(calculateMaxDistance(city, unVisitedCities, 0));
        });
        System.out.println("results " + results);
        return results.stream().reduce(Integer::max).orElseThrow();
    }

    private List<City> prepareData(String filename) {
        List<String> puzzleInputTest = PuzzleInputUtils.getLinesFromFile(filename);

        List<City> cities = new ArrayList<>();
        //récupérer chaque nom de ville avec la distance vers la seconde ville
        puzzleInputTest.forEach(input -> {
            String[] values = input.split(" to | = ");
            addInCities(cities, values[0], values[1], values[2]);
            addInCities(cities, values[1], values[0], values[2]);
        });

        cities.forEach(city -> System.out.println(city.getName() + " : " + city.getAdjacentCities()));

        return cities;
    }


    private static void addInCities(List<City> cities, String city1, String city2, String distance) {
        City actualCity = cities.stream().filter(city -> city.getName().equals(city1)).findFirst().orElse(null);
        if (actualCity != null) {
            actualCity.addAdjacentCity(city2, Integer.parseInt(distance));
        } else {
            City city = new City(city1);
            city.addAdjacentCity(city2, Integer.parseInt(distance));
            cities.add(city);
        }
    }

    private Integer calculateMinDistance(City actualCity, List<City> unVisitedCities, Integer distance) {
        System.out.println("calculate distance for " + actualCity.getName());
        if (unVisitedCities.isEmpty()) {
            return distance;
        }

        //tant que on ne trouve pas la ville la plus proche dans la liste:
        // on cherche la ville la plus proche
        City closerCity = null;
        Integer minDistance = 0;
        while (closerCity == null) {
            // on cherche la ville la plus proche
            String nameCloserCity = actualCity.getCloserCityFromListOfCities(unVisitedCities.stream().map(City::getName).toList());
            // on la cherche dans nos unVisitedCities
            closerCity = unVisitedCities.stream().filter(city -> city.getName().equals(nameCloserCity)).findFirst().orElse(null);
        }
        System.out.println("closerCity " + closerCity.getName());

        minDistance = actualCity.getDistanceByCityName(closerCity.getName());
        distance = distance + minDistance;
        unVisitedCities.remove(closerCity);

        return calculateMinDistance(closerCity, unVisitedCities, distance);
    }

    private Integer calculateMaxDistance(City actualCity, List<City> unVisitedCities, Integer distance) {
        System.out.println("calculate distance for " + actualCity.getName());
        if (unVisitedCities.isEmpty()) {
            return distance;
        }

        //tant que on ne trouve pas la ville la plus proche dans la liste:
        // on cherche la ville la plus proche
        City furtherCity = null;
        Integer maxDistance = 0;
        while (furtherCity == null) {
            // on cherche la ville la plus proche
            String nameCloserCity = actualCity.getFurtherCityFromListOfCities(unVisitedCities.stream().map(City::getName).toList());
            // on la cherche dans nos unVisitedCities
            furtherCity = unVisitedCities.stream().filter(city -> city.getName().equals(nameCloserCity)).findFirst().orElse(null);
        }
        System.out.println("furtherCity " + furtherCity.getName());

        maxDistance = actualCity.getDistanceByCityName(furtherCity.getName());
        distance = distance + maxDistance;
        unVisitedCities.remove(furtherCity);

        return calculateMaxDistance(furtherCity, unVisitedCities, distance);
    }

    public int calculateClusteredMinDistance(String filename) {
        List<City> allCities = prepareData(filename);

        // Créer les clusters
        Map<String, Map<String, Integer>> distances = new HashMap<>();
        allCities.forEach(city -> {
            distances.put(city.getName(), city.getAdjacentCities());
        });

        // Diviser en clusters
        List<String> cluster1Names = new ArrayList<>();
        List<String> cluster2Names = new ArrayList<>();
        String centroid1 = allCities.get(0).getName();
        cluster1Names.add(centroid1);

        List<String> allCityNames = allCities.stream().map(City::getName).toList();
        for (String cityName : allCityNames) {
            if (cityName.equals(centroid1)) continue;
            if (cluster1Names.size() <= cluster2Names.size()) {
                cluster1Names.add(cityName);
            } else {
                cluster2Names.add(cityName);
            }
        }

        // Trouver la ville pont
        String bridgeCityName = findBridgeCity(distances, cluster1Names, cluster2Names);
        System.out.println("Ville pont: " + bridgeCityName);

        // Calculer les distances minimales pour chaque cluster
        List<City> cluster1 = allCities.stream()
                .filter(city -> cluster1Names.contains(city.getName()))
                .collect(Collectors.toList());
        List<City> cluster2 = allCities.stream()
                .filter(city -> cluster2Names.contains(city.getName()))
                .collect(Collectors.toList());
        City bridgeCity = allCities.stream()
                .filter(city -> city.getName().equals(bridgeCityName))
                .findFirst()
                .orElseThrow();

        // Calculer le chemin minimal pour cluster1 jusqu'au pont
        int distanceCluster1 = calculateMinDistance(cluster1.get(0),
                new ArrayList<>(cluster1.subList(1, cluster1.size())), 0);

        // Calculer le chemin minimal pour cluster2 jusqu'au pont
        int distanceCluster2 = calculateMinDistance(cluster2.get(0),
                new ArrayList<>(cluster2.subList(1, cluster2.size())), 0);

        // Ajouter les distances vers la ville pont
        int distanceToBridge1 = cluster1.get(0).getDistanceByCityName(bridgeCityName);
        int distanceToBridge2 = cluster2.get(0).getDistanceByCityName(bridgeCityName);

        int totalDistance = distanceCluster1 + distanceCluster2 + distanceToBridge1 + distanceToBridge2;

        System.out.println("Distance cluster 1: " + distanceCluster1);
        System.out.println("Distance cluster 2: " + distanceCluster2);
        System.out.println("Distance totale: " + totalDistance);

        return totalDistance;
    }

    private String findBridgeCity(Map<String, Map<String, Integer>> distances,
                                  List<String> cluster1,
                                  List<String> cluster2) {
        String bridgeCity = null;
        int minTotalDistance = Integer.MAX_VALUE;

        for (String city : distances.keySet()) {
            int totalDistance = 0;
            for (String c1 : cluster1) {
                totalDistance += distances.get(city).getOrDefault(c1, 0);
            }
            for (String c2 : cluster2) {
                totalDistance += distances.get(city).getOrDefault(c2, 0);
            }

            if (totalDistance < minTotalDistance) {
                minTotalDistance = totalDistance;
                bridgeCity = city;
            }
        }

        return bridgeCity;
    }

}
