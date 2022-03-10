package com.schambeck.dna.search;

public abstract class TextSearchAdapter implements TextSearch {

    @Override
    public boolean contains(String[] dna) {
        return findFirst(dna).isPresent();
    }

    public abstract int getIterations();

}
