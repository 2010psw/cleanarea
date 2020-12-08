package erp.cleanarea.domain.item;

import erp.cleanarea.repository.ItemRepository;
import erp.cleanarea.service.ItemService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Converter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemTest {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemService itemService;

    @PersistenceContext
    private EntityManager em;

    @Test
    @DisplayName("Item Dummy Data")
    public void itemDummy(){
        for(int i=1; i<=1500;i++){
            String name = "" + i;
            String manage = "" + i;
            String division = "" + i;
            int count = i;
            String countSub = "" + i;
            int buyPrice = i;
            String supply = "" + i;
            String supplyInfo = "" + i;
            String memo = "" + i;
            ItemForm itemForm = new ItemForm(name, manage, division, count, countSub, buyPrice, supply, supplyInfo, memo);
            Item item = new Item(itemForm);
            itemRepository.save(item);
        }
    }
}