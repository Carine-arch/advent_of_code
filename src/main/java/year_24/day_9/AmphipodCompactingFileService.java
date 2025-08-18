package year_24.day_9;

import utils.PuzzleInputUtils;

import java.util.ArrayList;
import java.util.List;

public class AmphipodCompactingFileService {

    static final char FREE_SPACE = '.';
    static final String FREE_SPACE_STRING = ".";

    // Recommence avec liste
    // doit bouger un 10 sur un seul free space
    // doit calculer avec 10 et non 1 puis 0
    public long part1(String filename) {
        String line = PuzzleInputUtils.getStringFromFile(filename);

        // List de String correspondant à nidNumber , nfree space, nidNumber,.....
        List<String> intermediateBuilder = new ArrayList<>();
        int idNumber = 0; // Compteur pour les id des amphipodes
        for (int i = 0; i <= line.length() - 1; i = i + 2) { // On incrémente de 2 pour regarder les amphipodes ET les free spaces
            // récupère l'idNumber de l'amphipode et le répète autant de fois que le chiffre indiqué dans la ligne
            for (int j = 0; j < Character.getNumericValue(line.charAt(i)); j++) {
                intermediateBuilder.add(String.valueOf(idNumber));
            }
            if (i + 1 < line.length()) {
                // créé autant de free space que le chiffre indiqué dans la ligne
                for (int j = 0; j < Character.getNumericValue(line.charAt(i + 1)); j++) {
                    intermediateBuilder.add(FREE_SPACE_STRING);
                }
            }
            //System.out.println(intermediateBuilder);
            idNumber++;
        }

        // Parcours de la liste pour remplacer les FREE_SPACE_STRING par le dernier élément de la liste
        // si dernier élément 10 ou 100 alors remplace 1 seul free space
        for (int i = 0; i < intermediateBuilder.size(); i++) {
            // supprime tous les free space à la fin de la liste
            while (intermediateBuilder.get(intermediateBuilder.size() - 1).equals(FREE_SPACE_STRING)) {
                // Supprime le dernier élément s'il est un FREE_SPACE_STRING
                intermediateBuilder.remove(intermediateBuilder.size() - 1);
            }

            if (i < intermediateBuilder.size() && intermediateBuilder.get(i).equals(FREE_SPACE_STRING)) {
                // Récupérer le dernier élément de la liste
                // TODO: Essayer plutot de récupérer le dernier élément d'une liste intermédiaire filtrée de ses FREE_SPACE
                String lastElement = intermediateBuilder.get(intermediateBuilder.size() - 1);

                // Remplacer le FREE_SPACE par le dernier élément
                intermediateBuilder.set(i, lastElement);

                // Supprimer le dernier élément de la liste
                intermediateBuilder.remove(intermediateBuilder.size() - 1);
            }
        }

        //System.out.println(intermediateBuilder);

        return computeWeightedSum2(intermediateBuilder);
    }


    public Long computeWeightedSum2(List<String> results) {
        List<Long> resultsLong = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            resultsLong.add(Long.parseLong(results.get(i)) * i);
        }
        return resultsLong.stream().reduce(0L, Long::sum);
        //TODO: essayer avec IntStream avec un index sur la taille des results
        //return results.stream().map(result -> Long.parseLong(result) * results.indexOf(result)).reduce(0L, Long::sum);
    }


    // ---------------------- TENTATIVE 1 -----------------------
    public long part1Old(String filename) {
        String line = PuzzleInputUtils.getStringFromFile(filename);

        // instance de StringBuilder pour construire la chaîne intermédiaire avec les free spaces
        StringBuilder intermediateBuilder = new StringBuilder();
        int idNumber = 0; // Compteur pour les amphipodes
        List<Integer> idNumbers = new ArrayList<>(); // Liste pour stocker les id numbers des amphipodes
        for (int i = 0; i <= line.length() - 1; i = i + 2) { // On incrémente de 2 pour regarder les amphipodes ET les free spaces
            // récupère l'idNumber de l'amphipode et le répète autant de fois que le chiffre indiqué dans la ligne
            intermediateBuilder.append(String.valueOf(idNumber).repeat(Math.max(0, Character.getNumericValue(line.charAt(i)))));
            if (i + 1 < line.length()) {
                // créé autant de free space que le chiffre indiqué dans la ligne
                intermediateBuilder.append(String.valueOf(FREE_SPACE).repeat(Math.max(0, Character.getNumericValue(line.charAt(i + 1)))));
            }
            //System.out.println(intermediateBuilder);
            idNumbers.add(idNumber);
            idNumber++;
        }

        StringBuilder resultBuilder = new StringBuilder();
        List<String> resultList = new ArrayList<>();
        String intermediateString = intermediateBuilder.toString();
        // Récupère la chaîne intermédiaire sans les free spaces pour avoir une liste des id number
        String intermediateStringNoFreeSpace = intermediateString.replace(String.valueOf(FREE_SPACE), "");
        int resultStringLength = intermediateStringNoFreeSpace.length();
        int indexLast = resultStringLength - 1;
        int indexLastIdNumber = idNumbers.size() - 1;
        int indexCurrentIdNumber = 0;

        for (int i = 0; i < intermediateBuilder.length(); i++) {
            if (intermediateString.charAt(i) == FREE_SPACE) {
                //resultBuilder.append(intermediateStringNoFreeSpace.charAt(indexLast));
                //indexLast--;

                indexLastIdNumber = getIndexLastIdNumber(intermediateStringNoFreeSpace, idNumbers, indexLastIdNumber);
                resultBuilder.append(idNumbers.get(indexLastIdNumber));
                resultList.add(idNumbers.get(indexLastIdNumber).toString());
                intermediateStringNoFreeSpace = intermediateStringNoFreeSpace.substring(0, intermediateStringNoFreeSpace.length() - idNumbers.get(indexLastIdNumber).toString().length());

            } else {
                resultBuilder.append(intermediateString.charAt(i));
                resultList.add(String.valueOf(intermediateString.charAt(i)));
            }
            //System.out.println(resultBuilder);
        }

        String result = resultBuilder.substring(0, intermediateString.replace(String.valueOf(FREE_SPACE), "").length());
        System.out.println(result);

        return computeWeightedSum(result);
    }

    private int getIndexLastIdNumber(String intermediateStringNoFreeSpace, List<Integer> idNumbers, int indexLastIdNumber) {
        String idNumber = idNumbers.get(indexLastIdNumber).toString();
        boolean isIdNumberFound = intermediateStringNoFreeSpace.endsWith(idNumber);

        if (isIdNumberFound) {
            return indexLastIdNumber;
        }

        indexLastIdNumber--;
        return getIndexLastIdNumber(intermediateStringNoFreeSpace, idNumbers, indexLastIdNumber);
    }


    public Long computeWeightedSum(String result) {
        long sum = 0L;
        for (int i = 0; i < result.length(); i++) {
            long digit = Character.getNumericValue(result.charAt(i)); // Convertit le caractère en entier
            sum += digit * i; // Multiplie la valeur par sa position
        }
        return sum;
    }

}
