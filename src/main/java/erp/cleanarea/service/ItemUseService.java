package erp.cleanarea.service;

import erp.cleanarea.domain.item.ItemUse;
import erp.cleanarea.repository.ItemUseRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class ItemUseService {

    @Autowired
    private ItemUseRepository itemUseRepository;

    public Page<ItemUse> getItemUseList(Pageable pageable){
        int page = (pageable.getPageNumber() == 0) ? 0 : pageable.getPageNumber()-1;
        pageable = PageRequest.of(page, 15, Sort.by(Sort.Direction.DESC, "id"));
        return itemUseRepository.findAll(pageable);
    }

}
