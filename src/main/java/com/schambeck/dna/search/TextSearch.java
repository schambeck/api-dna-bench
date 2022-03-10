package com.schambeck.dna.search;

import com.schambeck.dna.search.model.Match;

import java.util.Optional;

public interface TextSearch {

    boolean contains(String[] dna);

    Optional<Match> findFirst(String[] dna);

}
