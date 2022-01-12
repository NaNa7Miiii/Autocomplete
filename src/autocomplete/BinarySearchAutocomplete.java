package autocomplete;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Binary search implementation of the {@link Autocomplete} interface.
 *
 * @see Autocomplete
 */
public class BinarySearchAutocomplete implements Autocomplete {
    /**
     * {@link List} of added autocompletion terms.
     */
    private final List<CharSequence> terms;

    /**
     * Constructs an empty instance.
     */
    public BinarySearchAutocomplete() {
        this.terms = new ArrayList<>();
    }

    @Override
    public void addAll(Collection<? extends CharSequence> terms) {
        this.terms.addAll(terms);

        if (terms.isEmpty()) {
            System.out.println("Detected Empty Terms");
        } else {
            this.terms.sort(CharSequence::compare);
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
        return binarySearch(result, prefix, 0, terms.size()-1);

    }

    public List<CharSequence> binarySearch(List<CharSequence> result, CharSequence prefix, int low, int high) {
        if (low > high) {
            return result;
        }
        int mid = low + (high - low) / 2;
        CharSequence term = terms.get(mid); // Obtain the middle term
//        System.out.println(term);
        if (term.length() >= prefix.length()) { // We assume that the length of prefix is always less than or equal to that of terms
            term = term.subSequence(0, prefix.length());
        }
        if (CharSequence.compare(term, prefix) > 0) { // the index of the term is larger than that of the prefix
            return binarySearch(result, prefix, low, mid-1);
        } else if (CharSequence.compare(term, prefix) < 0) { // the index of the term is smaller than that of the prefix
            return binarySearch(result, prefix, mid + 1, high);
        } else {
            // add terms follow the pattern to the result and remove it from the original CharSequence,
            // then reiterate through the new CharSequence
            result.add(terms.get(mid));
            terms.remove(mid);
//            System.out.println(terms);
            return binarySearch(result, prefix, 0, terms.size()-1);
        }
    }
}
