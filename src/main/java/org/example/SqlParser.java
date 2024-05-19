package org.example;

import org.example.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SqlParser {

    private static final String SELECT_PATTERN = "(?i)SELECT\\s+(.*?)\\s+FROM";
    private static final String FROM_PATTERN = "(?i)FROM\\s+(.*?)(?:\\s+(?:JOIN|INNER JOIN|LEFT JOIN|RIGHT JOIN|FULL JOIN|WHERE|GROUP BY|ORDER BY|LIMIT|OFFSET)|$)";
    private static final String JOIN_PATTERN = "(?i)(JOIN|INNER JOIN|LEFT JOIN|RIGHT JOIN|FULL JOIN)\\s+(.*?)\\s+ON\\s+(.+?)(?:\\s+(?:JOIN|INNER JOIN|LEFT JOIN|RIGHT JOIN|FULL JOIN|WHERE|GROUP BY|ORDER BY|LIMIT|OFFSET)|$)";
    private static final String WHERE_PATTERN = "(?i)WHERE\\s+(.*?)(?:\\s+(?:GROUP BY|ORDER BY|LIMIT|OFFSET)|$)";
    private static final String GROUP_BY_PATTERN = "(?i)GROUP BY\\s+(.*?)(?:\\s+ORDER BY|\\s+LIMIT|\\s+OFFSET)";
    private static final String ORDER_BY_PATTERN = "(?i)ORDER BY\\s+(.*?)(?:\\s+LIMIT|\\s+OFFSET)";
    private static final String LIMIT_PATTERN = "(?i)LIMIT\\s+(\\d+)";
    private static final String OFFSET_PATTERN = "(?i)OFFSET\\s+(\\d+)";

    public static Query parseQuery(String sql) {
       Query query = new Query();

        addIfPatternMatches(SELECT_PATTERN, sql, query.getColumns());
        addIfPatternMatches(FROM_PATTERN, sql, query.getFromSources());

        Matcher joinsMatcher = Pattern.compile(JOIN_PATTERN).matcher(sql);
        while (joinsMatcher.find()) {
            JoinType joinType = JoinType.fromString(joinsMatcher.group(1));
            String tableName = joinsMatcher.group(2);
            String onCondition = joinsMatcher.group(3);
            Join join = new Join();
            join.setType(joinType);
            join.setSource(tableName.trim());
            join.setCondition(onCondition.trim());
            query.addJoin(join);
        }

        addIfPatternMatches(WHERE_PATTERN, sql, query.getWhereClauses());
        addIfPatternMatches(GROUP_BY_PATTERN, sql, query.getGroupByColumns());
        addIfPatternMatches(ORDER_BY_PATTERN, sql, query.getSortColumns());

        Matcher limitMatcher = Pattern.compile(LIMIT_PATTERN).matcher(sql);
        if (limitMatcher.find()) {
            query.setLimit(Integer.parseInt(limitMatcher.group(1)));
        }

        Matcher offsetMatcher = Pattern.compile(OFFSET_PATTERN).matcher(sql);
        if (offsetMatcher.find()) {
            query.setOffset(Integer.parseInt(offsetMatcher.group(1)));
        }

        return query;
    }

    private static void addIfPatternMatches(String pattern, String sql, List<String> columns) {
        Matcher fromMatcher = Pattern.compile(pattern).matcher(sql);
        if (fromMatcher.find()) {
            String fromSources = fromMatcher.group(1);
            columns.addAll(parseColumns(fromSources));
        }
    }

    private static List<String> parseColumns(String columns) {
        String[] columnArray = columns.split(",");
        List<String> columnList = new ArrayList<>();
        for (String column : columnArray) {
            columnList.add(column.trim());
        }
        return columnList;
    }

    public static void main(String[] args) {
        String sql = "";
        Scanner scanner = new Scanner(System.in);
        while (!sql.equals("0")) {
            System.out.println("Enter an SQL SELECT query, or enter 0 to end");
            sql = scanner.nextLine();

            Query query = parseQuery(sql);
            System.out.println("Columns: " + query.getColumns());
            System.out.println("From sources: " + query.getFromSources());
            System.out.println("Joins: " + query.getJoins());
            System.out.println("Where clauses: " + query.getWhereClauses());
            System.out.println("Group by columns: " + query.getGroupByColumns());
            System.out.println("Sort columns: " + query.getSortColumns());
            System.out.println("Limit: " + query.getLimit());
            System.out.println("Offset: " + query.getOffset());
        }
        scanner.close();

        System.out.println("ENDED");
    }
}