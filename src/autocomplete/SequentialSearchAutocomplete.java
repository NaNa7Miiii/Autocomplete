package autocomplete;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Sequential search implementation of the {@link Autocomplete} interface.
 *
 * @see Autocomplete
 */
public class SequentialSearchAutocomplete implements Autocomplete {
    /**
     * {@link List} of added autocompletion terms.
     */
    private final List<CharSequence> terms;

    /**
     * Constructs an empty instance.
     */
    public SequentialSearchAutocomplete() {
        this.terms = new ArrayList<>();
    }

    @Override
    public void addAll(Collection<? extends CharSequence> terms) {
        this.terms.addAll(terms);
        if (terms.isEmpty()) {
            System.out.println("Detected Empty Terms");
        }
        // throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public List<CharSequence> allMatches(CharSequence prefix) {
        List<CharSequence> result = new ArrayList<>();
        if (prefix == null || prefix.length() == 0) {
            System.out.println("Detected Null Prefix ");
            return result;
        }
        for (CharSequence term: this.terms) {
            if (Autocomplete.isPrefixOf(prefix, term)) {
                result.add(term);
            }
        }
        return result;
        // throw new UnsupportedOperationException("Not implemented yet");
    }
}
