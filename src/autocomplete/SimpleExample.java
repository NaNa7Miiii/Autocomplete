package autocomplete;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimpleExample {
    public static void main(String[] args) {
        List<CharSequence> terms = new ArrayList<>();
        terms.add("alpha");
        terms.add("delta");
        terms.add("do");
        terms.add("cats");
        terms.add("dodgy");
        terms.add("pilot");
        terms.add("dog");

        terms.add("ilasi");
        terms.add("Il'bak");



// Choose your Autocomplete implementation.
        Autocomplete autocomplete = new TernarySearchTreeAutocomplete();
        autocomplete.addAll(terms);

// Choose your prefix string.
        CharSequence prefix = "il";
        // the comparison made by binary search, while it cannot be conducted in ternary search because it uses character
        // instead of CharSequence.
        System.out.println(CharSequence.compare("il", "IL"));
        List<CharSequence> matches = autocomplete.allMatches(prefix);

        for (CharSequence match : matches) {
            System.out.println(match);
        }
    }
}
