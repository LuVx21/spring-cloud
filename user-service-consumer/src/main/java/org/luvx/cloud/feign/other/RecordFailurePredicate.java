package org.luvx.cloud.feign.other;

import java.util.function.Predicate;

public class RecordFailurePredicate implements Predicate<Throwable> {
    @Override
    public boolean test(Throwable o) {
        return true;
    }
}