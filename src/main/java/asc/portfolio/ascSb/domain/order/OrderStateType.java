package asc.portfolio.ascSb.domain.order;

public enum OrderStateType {
    ORDER, // 결제중
    COMP, // 결제완료
    CANCEL, // 결제취소
    FAIL // 결제오류
}
