package at.cgsit.train.functional_zwei;

import at.cgsit.train.java.objects.Buch;

@FunctionalInterface
public interface BuchVergleichInterface {
    boolean isSame(Buch b1, Buch b2);
}