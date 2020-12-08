package erp.cleanarea.service;

import erp.cleanarea.domain.item.Item;
import erp.cleanarea.repository.ItemRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Page<Item> getItemList(Pageable pageable){
        int page = (pageable.getPageNumber() == 0) ? 0 : pageable.getPageNumber()-1;
        pageable = PageRequest.of(page, 15, Sort.by(Sort.Direction.DESC, "id"));
        return itemRepository.findAll(pageable);
    }

    public Page<Item> itemSearch(Pageable pageable, String name){
        int page = (pageable.getPageNumber() == 0) ? 0 : pageable.getPageNumber()-1;
        pageable = PageRequest.of(page, 15, Sort.by(Sort.Direction.DESC, "id"));
        Page<Item> items = itemRepository.findByNameContaining(name, pageable);
        return items;
    }



}
