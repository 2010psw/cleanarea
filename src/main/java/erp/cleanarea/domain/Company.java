package erp.cleanarea.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Company {



    @Id
    private String companyId; //사업자번호

    private String name; //사업장 이름

    private String president; //대표

    private String address; //주소

    private String phone; //연락처

    private String fax; //팩스번호

    private String work; //종목

    private String service; //업태

}

