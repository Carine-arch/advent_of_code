package year_15.day_10;

public class LookAndSayService {

    public int part1(String input, int steps) {
        String result = input;

        // Fait l'opération "look and say" le nombre de fois spécifié par steps
        for (int i = 0; i < steps; i++) {
            result = lookAndSay(result);
        }

        // Retourne la longueur de la chaîne résultante de la dernière opération
        return result.length();
    }

    private String lookAndSay(String input) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            // Le caractère actuel
            char currentChar = input.charAt(i);
            // Le nombre de fois où il apparait
            int count = 1;
            // Compte les occurrences consécutives du caractère actuel
            while (i + 1 < input.length() && input.charAt(i + 1) == currentChar) {
                count++;
                i++;
            }
            // Ajoute le nombre d'apparences du caractère et le caractère au résultat
            result.append(count).append(currentChar);
        }
        return result.toString();
    }
}
