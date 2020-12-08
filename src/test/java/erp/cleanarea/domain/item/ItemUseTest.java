package erp.cleanarea.domain.item;

import erp.cleanarea.repository.ItemRepository;
import erp.cleanarea.repository.ItemUseRepository;
import erp.cleanarea.service.ItemService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class ItemUseTest {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemUseRepository itemUseRepository;

    @Autowired
    private ItemService itemService;

    @PersistenceContext
    private EntityManager em;


    @Test
    @DisplayName("날짜 검색 test")
    public void searchBetweenTest(){
        LocalDate startDay = LocalDate.of(1990, 1, 3);
        LocalDate endDay = LocalDate.of(1990, 1, 5);
        List<ItemUse> itemUses = itemUseRepository.findAllByUseDayBetween(startDay, endDay);

        for (ItemUse itemUse : itemUses) {
            System.out.println(itemUse.toString());
        }

    }
}