package asc.portfolio.ascSb.domain.commonenum;

public enum ProductNameType {
    TODAY_FIXED_TERM_TICKET("당일권"),
    WEEK_FIXED_TERM_TICKET("7일권"),
    TWO_WEEK_FIXED_TERM_TICKET("14일권"),
    THREE_WEEK_FIXED_TERM_TICKET("21일권"),
    FOUR_WEEK_FIXED_TERM_TICKET("28일권"),
    ONE_HOUR_PART_TIME_TICKET("1시간권"),
    FOUR_HOUR_PART_TIME_TICKET("4시간권"),
    TEN_HOUR_PART_TIME_TICKET("10시간권"),
    FIFTY_HOUR_PART_TIME_TICKET("50시간권"),
    HUNDRED_HOUR_PART_TIME_TICKET("100시간권");

    private final String value;

    ProductNameType(String value) { this.value = value; }

    public String getValue() { return value; }

}
