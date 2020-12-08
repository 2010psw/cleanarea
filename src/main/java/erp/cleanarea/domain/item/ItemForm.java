package erp.cleanarea.domain.item;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter @Setter
@NoArgsConstructor
public class ItemForm {

    //약품명
    private String name;

    //약품 관리(탑스원, 거륭)
    private String manage;

    //약품 구분
    private String division;

    //약품 수량
    private int count;

    //약품 단위(ml, L, Kg, 정, ~~~)
    private String countSub;

    //매입가
    private int buyPrice;

    //매입처
    private String supply;

    //매입처 정보
    private String supplyInfo;

    //비고, 특이사항
    private String memo;

    public ItemForm(String name, String manage, String division, int count, String countSub, int buyPrice, String supply, String supplyInfo, String memo) {
        this.name = name;
        this.manage = manage;
        this.division = division;
        this.count = count;
        this.countSub = countSub;
        this.buyPrice = buyPrice;
        this.supply = supply;
        this.supplyInfo = supplyInfo;
        this.memo = memo;
    }
}
