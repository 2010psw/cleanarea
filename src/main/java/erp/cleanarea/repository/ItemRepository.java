package erp.cleanarea.repository;

import erp.cleanarea.domain.item.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Page<Item> findByNameContaining(String name, Pageable pageable);

    Page<Item> findAll(Pageable pageable);

    Item findOneById(Long id);

}
