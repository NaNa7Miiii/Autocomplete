package autocomplete;

import java.util.*;

/**
 * {@link TreeSet} implementation of the {@link Autocomplete} interface.
 *
 * @see Autocomplete
 */
public class TreeSetAutocomplete implements Autocomplete {
    /**
     * {@link NavigableSet} of added autocompletion terms.
     */
    private final NavigableSet<CharSequence> terms;

    /**
     * Constructs an empty instance.
     */

    public TreeSetAutocomplete() {
        this.terms = new TreeSet<>(CharSequence::compare);
    }

    @Override
    public void addAll(Collection<? extends CharSequence> terms) {
        this.terms.addAll(terms);
    }

    @Override
    public List<CharSequence> allMatches(CharSequence prefix) {
        List<CharSequence> result = new ArrayList<>();
        if (prefix == null || prefix.length() == 0) {
            return result;
        }
        //  finds the first matching term (the matching term that comes first alphabetically)
        CharSequence start = terms.ceiling(prefix);
        if (start == null) {
            return result;
        }
        // The remaining matches (later alphabetically) are collected by iterating over the TreeSet.tailSet until we reach a term that no longer matches the prefix.
        for (CharSequence term : terms.tailSet(start)) {
            if (Autocomplete.isPrefixOf(prefix, term)) {
                result.add(term);
            } else {
                return result;
            }
        }
        return result;
    }
}
