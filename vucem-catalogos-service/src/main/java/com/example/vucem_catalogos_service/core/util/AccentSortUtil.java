package com.example.vucem_catalogos_service.core.util;

import java.text.Normalizer;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class AccentSortUtil {

    private AccentSortUtil() {}

    public static String normalize(String input) {
        if (input == null) return "";
        return Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .toLowerCase();
    }

    public static <T> List<T> sort(
            List<T> list,
            String sortBy,
            String sortDir,
            Map<String, Function<T, String>> sortMap
    ) {

        if (sortBy == null || !sortMap.containsKey(sortBy)) {
            return list;
        }

        Function<T, String> extractor = sortMap.get(sortBy);

        list.sort(Comparator.comparing(e -> normalize(extractor.apply(e))));

        if ("desc".equalsIgnoreCase(sortDir)) {
            Collections.reverse(list);
        }

        return list;
    }

    public static <T> List<T> paginate(List<T> list, int page, int size) {
        int start = page * size;
        int end = Math.min(start + size, list.size());

        if (start > list.size()) {
            return Collections.emptyList();
        }

        return list.subList(start, end);
    }
}
