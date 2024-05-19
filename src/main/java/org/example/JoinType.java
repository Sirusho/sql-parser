package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

enum JoinType {
    JOIN, INNER_JOIN, LEFT_JOIN, RIGHT_JOIN, FULL_JOIN;

    private static final Pattern pattern = Pattern.compile("(?i)\\b(JOIN|INNER JOIN|LEFT JOIN|RIGHT JOIN|FULL JOIN)\\b");

    public static JoinType fromString(String str) {
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            String match = matcher.group(1).toUpperCase().replace(" ", "_");
            return valueOf(match);
        }
        return null;
    }
}