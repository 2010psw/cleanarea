package erp.cleanarea.domain.item;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Item {
    @Id
    @Column(name = "item_id")
    @GeneratedValue
    private Long id;

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

    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
    private List<ItemUse> itemUse = new ArrayList<>();

    public Item(ItemForm itemForm) {
        this.name = itemForm.getName();
        this.manage = itemForm.getManage();
        this.division = itemForm.getDivision();
        this.count = 0;
        this.countSub = itemForm.getCountSub();
        this.buyPrice = itemForm.getBuyPrice();
        this.supply = itemForm.getSupply();
        this.supplyInfo = itemForm.getSupplyInfo();
        this.memo = itemForm.getMemo();
    }
}
