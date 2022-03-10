package com.schambeck.dna.search;

public abstract class TextSearchPrinter implements TextSearch {

//    private static final String YELLOW = "\033[0;33m";
//    private static final String BLUE = "\033[0;34m";
//    private static final String RESET = "\033[0m";
//
//    @Override
//    public boolean contains(String[] dna) {
//        return findFirst(dna).isPresent();
//    }
//
//    public Optional<Match> searchAndPrint(String[] dna) {
//        Optional<Match> match = findFirst(dna);
//        match.ifPresent(m -> printAndHighlight(dna, m));
//        return match;
//    }
//
//    private void printAndHighlight(String[] dna, Match match) {
//        System.out.print("   ");
//        for (int col = 0; col < dna.length; col++) {
//            System.out.printf("%s%2d%s ", YELLOW, col, RESET);
//        }
//        System.out.println();
//        for (int row = 0; row < dna.length; row++) {
//            String text = dna[row];
//            System.out.printf("%s%2d%s ", YELLOW, row, RESET);
//            for (int col = 0; col < text.length(); col++) {
//                char charAt = text.charAt(col);
//                if (hasMatch(match.getVertices(), row, col)) {
//                    System.out.printf(" %s%s%s ", BLUE, charAt, RESET);
//                } else {
//                    System.out.printf(" %s ", charAt);
//                }
//            }
//            System.out.println();
//        }
//    }
//
//    private boolean hasMatch(List<Vertex> vertices, int row, int col) {
//        return vertices.stream().anyMatch(p -> p.getRow() == row && p.getCol() == col);
//    }

}
