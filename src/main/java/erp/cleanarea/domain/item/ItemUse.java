package erp.cleanarea.domain.item;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter @Setter
@NoArgsConstructor
public class ItemUse {

    @Id
    @Column(name = "itemUse_id")
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    //사용 일
    private LocalDate useDay;

    //입-출고 사용처
    private String place;

    //사용량
    private int useCount;

    //가격
    private int price;

    //입,출고
    @Enumerated(EnumType.STRING)
    private InOut io;

    private String memo;

    //재고
    private int stack;
    @Builder
    public ItemUse(Item item, LocalDate useDay, String place, int useCount, int price, InOut io, String memo, int stack) {
        this.item = item;
        this.useDay = useDay;
        this.place = place;
        this.useCount = useCount;
        this.price = price;
        this.io = io;
        this.memo = memo;
        this.stack = stack;
    }
}




