package com.schambeck.dna.search;

import com.schambeck.dna.search.model.Match;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.schambeck.dna.util.DnaUtil.SEQUENCE_COUNT;
import static java.lang.String.format;
import static java.util.Optional.empty;
import static java.util.stream.Collectors.joining;

public class MatcherSearch implements TextSearch {

    private static final Pattern PATTERN = Pattern.compile("A{4}|T{4}|C{4}|G{4}");
//    private int iterations;

    @Override
    public boolean contains(String[] dna) {
        return findFirst(dna).isPresent();
    }

    @Override
    public Optional<Match> findFirst(String[] dna) {
//        iterations = 0;
        return matcherSearch(dna);
    }

//    @Override
//    public int getIterations() {
//        return 0;
//    }

    private Optional<Match> matcherSearch(String[] dna) {
        Optional<Match> horizontal = searchHorizontal(dna);
        if (horizontal.isPresent()) {
            return horizontal;
        }
        Optional<Match> vertical = searchVertical(dna);
        if (vertical.isPresent()) {
            return vertical;
        }
        return searchDiagonal(dna);
    }

    public Optional<Match> searchHorizontal(String text) {
        Matcher matcher = PATTERN.matcher(text);
        if (matcher.find()) {
            String group = matcher.group();
            char charHorizontal = group.charAt(0);
            int col = matcher.start();
            return Optional.of(new Match("horizontal", charHorizontal)
                    .addVertex(0, col)
                    .addVertex(0, col + 1)
                    .addVertex(0, col + 2)
                    .addVertex(0, col + 3));
        }
        return empty();
    }

    private Optional<Match> searchHorizontal(String[] dna) {
        for (int row = 0; row < dna.length; row++) {
            String text = dna[row];
            Matcher matcher = PATTERN.matcher(text);
            if (matcher.find()) {
                String group = matcher.group();
                char charHorizontal = group.charAt(0);
                int col = matcher.start();
//                iterations += col;
                return Optional.of(new Match("horizontal", charHorizontal)
                        .addVertex(row, col)
                        .addVertex(row, col + 1)
                        .addVertex(row, col + 2)
                        .addVertex(row, col + 3));
            }
//            iterations++;
        }
        return empty();
    }

    private Optional<Match> searchVertical(String[] dna) {
        String textFirstRow = dna[0];
        for (int col = 0; col < dna.length; col++) {
//            iterations++;
            int count = 0;
            char previous = textFirstRow.charAt(col);
            for (int row = 1; row < dna.length; row++) {
//                iterations++;
                String text = dna[row];
                char letter = text.charAt(col);
                if (letter != previous) {
                    count = 0;
                    previous = letter;
                    continue;
                }
                count++;
                if (count == SEQUENCE_COUNT - 1) {
                    return Optional.of(new Match("vertical", letter)
                            .addVertex(row - 3, col)
                            .addVertex(row - 2, col)
                            .addVertex(row - 1, col)
                            .addVertex(row, col));
                }
            }
        }
        return empty();
    }

    private Optional<Match> searchDiagonal(String[] dna) {
        Optional<Match> right = searchDiagonalRight(dna);
        if (right.isPresent()) {
            return right;
        }
        return searchDiagonalLeft(dna);
    }

    private Optional<Match> searchDiagonalRight(String[] dna) {
        Optional<Match> rightA = searchDiagonalRightA(dna);
        if (rightA.isPresent()) {
            return rightA;
        }
        return searchDiagonalRightB(dna);
    }

    private Optional<Match> searchDiagonalLeft(String[] dna) {
        Optional<Match> leftA = searchDiagonalLeftA(dna);
        if (leftA.isPresent()) {
            return leftA;
        }
        return searchDiagonalLeftB(dna);
    }

    private Optional<Match> searchDiagonalRightA(String[] dna) {
        int dnaSize = dna.length;
        for (int row = SEQUENCE_COUNT - 1; row < dnaSize; row++) {
//            iterations++;
            int count = 0;
            char previous = dna[row].charAt(0);
            for (int innerRow = row - 1, col = 1; innerRow >= 0 && col < dnaSize; innerRow--, col++) {
//                iterations++;
                String text = dna[innerRow];
                char letter = text.charAt(col);
                if (letter != previous) {
                    count = 0;
                    previous = letter;
                    continue;
                }
                count++;
                if (count == SEQUENCE_COUNT - 1) {
                    return Optional.of(new Match("diagonalRightA", letter)
                            .addVertex(innerRow + 3, col - 3)
                            .addVertex(innerRow + 2, col - 2)
                            .addVertex(innerRow + 1, col - 1)
                            .addVertex(innerRow, col));
                }
            }
        }
        return empty();
    }

    private Optional<Match> searchDiagonalRightB(String[] dna) {
        int dnaSize = dna.length;
        int lastRow = dnaSize - 1;
        String textLastRow = dna[lastRow];
        for (int col = 1; col <= dnaSize - SEQUENCE_COUNT; col++) {
//            iterations++;
            char previous = textLastRow.charAt(col);
            int count = 0;
            for (int innerCol = col + 1, innerRow = lastRow - 1; innerRow >= 0 && innerCol < dnaSize; innerCol++, innerRow--) {
//                iterations++;
                String text = dna[innerRow];
                char letter = text.charAt(innerCol);
                if (letter != previous) {
                    count = 0;
                    previous = letter;
                    continue;
                }
                count++;
                if (count == SEQUENCE_COUNT - 1) {
                    return Optional.of(new Match("diagonalRightB", letter)
                            .addVertex(innerRow + 3, col - 3)
                            .addVertex(innerRow + 2, col - 2)
                            .addVertex(innerRow + 1, col - 1)
                            .addVertex(innerRow, col));
                }
            }
        }
        return empty();
    }

    private Optional<Match> searchDiagonalLeftA(String[] dna) {
        int dnaSize = dna.length;
        for (int row = dnaSize - 1; row >= 0; row--) {
//            iterations++;
            int count = 0;
            char previous = 0;
            for (int col = 0, innerRow = row; innerRow < dnaSize && col < dnaSize; col++, innerRow++) {
//                iterations++;
                String text = dna[innerRow];
                char letter = text.charAt(col);
                if (col == 0 || letter != previous) {
                    count = 0;
                    previous = letter;
                    continue;
                }
                count++;
                if (count == SEQUENCE_COUNT - 1) {
                    return Optional.of(new Match("diagonalLeftA", letter)
                            .addVertex(innerRow - 3, col - 3)
                            .addVertex(innerRow - 2, col - 2)
                            .addVertex(innerRow - 1, col - 1)
                            .addVertex(innerRow, col));
                }
            }
        }
        return empty();
    }

    private Optional<Match> searchDiagonalLeftB(String[] dna) {
        int dnaSize = dna.length;
        int firstRow = 0;
        String textFirstRow = dna[firstRow];
        for (int col = 1; col <= dnaSize - SEQUENCE_COUNT; col++) {
//            iterations++;
            int count = 0;
            char previous = textFirstRow.charAt(col);
            for (int row = firstRow + 1, innerCol = col + 1; row < dnaSize && innerCol < dnaSize; row++, innerCol++) {
//                iterations++;
                String text = dna[row];
                char letter = text.charAt(innerCol);
                if (innerCol == col || letter != previous) {
                    count = 0;
                    previous = letter;
                    continue;
                }
                count++;
                if (count == SEQUENCE_COUNT - 1) {
                    return Optional.of(new Match("diagonalLeftB", letter)
                            .addVertex(row - 3, col - 3)
                            .addVertex(row - 2, col - 2)
                            .addVertex(row - 1, col - 1)
                            .addVertex(row, col));
                }
            }
        }
        return empty();
    }

    public static void main(String[] args) {
        String[] dna = new String[]{"TTGAGA", "CTGAGC", "CATTGT", "CGAGGG", "CCTCTA", "TCACTG"};
        Optional<Match> first = new MatcherSearch().findFirst(dna);
        if (!first.isPresent()) {
            System.out.println("Pattern not found");
        } else {
            Match match = first.get();
            String positions = match.getVertices().stream()
                    .map(p -> format("[%d,%d]", p.getRow(), p.getCol()))
                    .collect(joining(" "));
            System.out.printf("Pattern found '%s' at positions: %s%n", match.getOrientation(), positions);
        }
    }

}
