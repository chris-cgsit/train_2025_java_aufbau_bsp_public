package at.cgsit.train.functional_basic;

import at.cgsit.train.java.objects.Buch;

public class BuchFIImpl implements BuchFilterInterface{
    @Override
    public boolean isTrueFor(Buch a) {
        return false;
    }
}
