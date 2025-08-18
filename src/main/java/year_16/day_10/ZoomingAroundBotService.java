package year_16.day_10;

import utils.PuzzleInputUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZoomingAroundBotService {

    public int part1(String filename, int lowMicroship, int highMicroship) {
        // Récupérer toutes les lignes du fichier
        List<String> lines = PuzzleInputUtils.getLinesFromFile(filename);
        // Expression régulière pour capturer seulement les nombres
        String regexNumber = "\\d+";
        Pattern pattern = Pattern.compile(regexNumber);

        // Map Numéro du bot, Bot
        Map<Integer, ZoomingAroundBot> zoomingAroundBots = new HashMap<>();

        // Parcourir les lignes et initialiser les bots
        for (String line : lines) {
            // Si la ligne est une ligne qui donne une value à un bot
            if (line.startsWith("value")) {
                // récupère value et numéro du bot
                List<String> numbers = new ArrayList<>();
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    numbers.add(matcher.group());
                }
                initBotWithMicroship(zoomingAroundBots, numbers);
                //System.out.println(numbers);
            }
            // Si la ligne est une ligne qui donne une règle
            if (line.startsWith("bot")) {
                List<String> numbers = new ArrayList<>();
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    numbers.add(matcher.group());
                }
                Rule rule = new Rule(Integer.parseInt(numbers.get(1)), Integer.parseInt(numbers.get(2)));
                initBotWithRule(zoomingAroundBots, numbers, rule);
            }
        }

        // On va chercher le bot qui a déjà 2 microships
        ZoomingAroundBot firstBot = zoomingAroundBots.values().stream()
                .filter(bot -> bot.getMicroships().size() == 2)
                .findFirst().orElseThrow();

        return recursiveApplyRule(lowMicroship, highMicroship, List.of(firstBot), zoomingAroundBots);


    }

    private static Integer recursiveApplyRule(int lowMicroship, int highMicroship, List<ZoomingAroundBot> botsWithTwoValue, Map<Integer, ZoomingAroundBot> zoomingAroundBots) {
        Optional<ZoomingAroundBot> result = botsWithTwoValue.stream()
                .filter(bot -> bot.getLowestMicroship() == lowMicroship && bot.getHighestMicroship() == highMicroship)
                .findFirst();
        if (result.isPresent()) {
            return result.get().getNumber();
        }

        // Sinon, on va simuler le fonctionnement des bots et construire une nouvelle liste de bots avec deux microships
        List<ZoomingAroundBot> newBotsWithTwoValue = new ArrayList<>();
        botsWithTwoValue.forEach(bot -> {
            ZoomingAroundBot lowBot = zoomingAroundBots.get(bot.getRule().lowBotNumber());
            lowBot.getMicroships().add(bot.getLowestMicroship());
            newBotsWithTwoValue.add(lowBot);
            ZoomingAroundBot highBot = zoomingAroundBots.get(bot.getRule().highBotNumber());
            highBot.getMicroships().add(bot.getHighestMicroship());
            newBotsWithTwoValue.add(highBot);
        });

        return recursiveApplyRule(lowMicroship, highMicroship, newBotsWithTwoValue, zoomingAroundBots);
    }

    // PEUT ETRE essayer de chercher les values qui m'intéressent directement et de suivre leur cheminement
    // jusqu'à ce que je trouve le bot qui les a toutes les deux


    private static void initBotWithMicroship(Map<Integer, ZoomingAroundBot> zoomingAroundBots, List<String> numbers) {
        if (zoomingAroundBots.containsKey(Integer.parseInt(numbers.get(1)))) {
            // Si le bot existe déjà, on ajoute la microship
            zoomingAroundBots.get(Integer.parseInt(numbers.get(1))).getMicroships().add(Integer.parseInt(numbers.get(0)));
        } else {
            // Sinon, on crée un nouveau bot
            ZoomingAroundBot zoomingAroundBot = new ZoomingAroundBot();
            zoomingAroundBot.setNumber(Integer.parseInt(numbers.get(1)));
            zoomingAroundBot.getMicroships().add(Integer.parseInt(numbers.get(0)));
            zoomingAroundBots.put(zoomingAroundBot.getNumber(), zoomingAroundBot);
        }
    }

    private static void initBotWithRule(Map<Integer, ZoomingAroundBot> zoomingAroundBots, List<String> numbers, Rule rule) {
        if (zoomingAroundBots.containsKey(Integer.parseInt(numbers.get(0)))) {
            // Si le bot existe déjà, on ajoute la rule
            zoomingAroundBots.get(Integer.parseInt(numbers.get(0))).setRule(rule);
        } else {
            // Sinon, on crée un nouveau bot
            ZoomingAroundBot zoomingAroundBot = new ZoomingAroundBot();
            zoomingAroundBot.setNumber(Integer.parseInt(numbers.get(0)));
            zoomingAroundBot.setRule(rule);
            zoomingAroundBots.put(zoomingAroundBot.getNumber(), zoomingAroundBot);
        }
    }
}
