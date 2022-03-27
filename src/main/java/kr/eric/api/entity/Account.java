package kr.eric.api.entity;

import kr.eric.api.enums.AccountRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "account")
public class Account {
    @Id
    @Column(name = "user_id", columnDefinition = "VARCHAR(45)")
    private String userId;

    @Column(name = "password", columnDefinition = "VARCHAR(255)")
    private String password;

    @Column(name = "name", columnDefinition = "VARCHAR(45)")
    private String name;

    @Column(name = "regNo", columnDefinition = "VARCHAR(45)")
    private String regNo;

    @Column(name = "tel", columnDefinition = "VARCHAR(45)")
    private String tel;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<AddressBook> addressBookList = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private List<AccountRole> roles = new ArrayList<>();

}
