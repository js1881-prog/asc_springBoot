package asc.portfolio.ascSb.commonenum.product;

public enum ProductNameType {
    TODAY_FIXED_TERM_TICKET("당일권", 10000, "TFTT"),
    WEEK_FIXED_TERM_TICKET("7일권", 15000, "WFTT"),
    TWO_WEEK_FIXED_TERM_TICKET("14일권", 30000, "TWFTT"),
    THREE_WEEK_FIXED_TERM_TICKET("21일권", 40000, "TWFTT"),
    FOUR_WEEK_FIXED_TERM_TICKET("28일권", 50000, "FWFTT"),
    ONE_HOUR_PART_TIME_TICKET("1시간권", 1000, "OHPTT"),
    FOUR_HOUR_PART_TIME_TICKET("4시간권", 7000, "FHPTT"),
    TEN_HOUR_PART_TIME_TICKET("10시간권", 10000, "THPTT"),
    FIFTY_HOUR_PART_TIME_TICKET("50시간권", 35000, "FHPTT"),
    HUNDRED_HOUR_PART_TIME_TICKET("100시간권", 50000, "HHPTT");

    private final String value;
    private final int price;
    private final String label;
    ProductNameType(String value, int price, String label) {
        this.value = value;
        this.price = price;
        this.label = label;
    }
    public String getValue() { return value; }
    public int getPrice() { return price; }
    public String getLabel() { return label; }

}
