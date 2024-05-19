package org.example;

class Join {
    private JoinType type;
    private String source;
    private String condition;

    @Override
    public String toString() {
        return "Join{" +
                "type=" + type +
                ", source='" + source + '\'' +
                ", condition='" + condition + '\'' +
                '}';
    }

    public JoinType getType() {
        return type;
    }

    public void setType(JoinType type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
