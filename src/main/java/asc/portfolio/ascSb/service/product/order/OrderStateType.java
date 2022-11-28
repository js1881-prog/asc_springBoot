package asc.portfolio.ascSb.service.product.order;

public enum OrderStateType {
    ORDER, // 결제중
    COMP, // 결제완료
    CANCEL, // 결제취소
    FAIL // 결제오류
}
