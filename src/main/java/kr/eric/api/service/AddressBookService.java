package kr.eric.api.service;

import kr.eric.api.dto.AddressBookDto;
import kr.eric.api.entity.Account;
import kr.eric.api.entity.AddressBook;
import kr.eric.api.exception.DataNotFoundException;
import kr.eric.api.exception.UserNotFoundException;
import kr.eric.api.repository.AccountRepository;
import kr.eric.api.repository.AddressBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static kr.eric.api.util.StringUtil.*;


@Service
@Transactional
@RequiredArgsConstructor
public class AddressBookService {

    private final AuthorizationService authorizationService;

    private final AddressBookRepository addressBookRepository;

    private final AccountRepository accountRepository;

    /**
     * 주소록 정보를 조회 한다.
     *
     * @param httpRequest
     * @return List<AddressBook>
     */
    public List<AddressBook> getAddressBookList(HttpServletRequest httpRequest) {
        String userId = authorizationService.getAccountIdFromToken(httpRequest);
        Account account = accountRepository.findFirstByUserId(userId).orElseThrow(DataNotFoundException::new);

        return account.getAddressBookList();
    }

    /**
     * 주소록 정보를 등록 한다.
     *
     * @param httpRequest
     * @param requestDto
     * @return AddressBook
     */
    public AddressBook registerAddressBook(HttpServletRequest httpRequest, AddressBookDto.AddressBookRequest requestDto) {
        String userId = authorizationService.getAccountIdFromToken(httpRequest);
        // 이름, 나이, 전화번호, 주민등록번호 체크
        nameValidationCheck(requestDto.getName());
        ageValidationCheck(requestDto.getAge());
        telValidationCheck(requestDto.getTel());

        AddressBook addressBook = AddressBook.builder()
                .userId(userId)
                .age(requestDto.getAge())
                .name(requestDto.getName())
                .tel(requestDto.getTel())
                .build();

        return addressBookRepository.save(addressBook);
    }

    /**
     * 주소록 정보를 변경 한다.
     *
     * @param httpRequest
     * @param requestDto
     * @return AddressBook
     */
    public AddressBook modifyAddressBook(HttpServletRequest httpRequest, AddressBookDto.AddressBookRequest requestDto, Long addressBookId) {
        String userId = authorizationService.getAccountIdFromToken(httpRequest);
        AddressBook addressBook = addressBookRepository.findFirstByIdAndUserId(addressBookId, userId).orElseThrow(UserNotFoundException::new);
        addressBook.setAge(requestDto.getAge());
        addressBook.setName(requestDto.getName());
        addressBook.setTel(requestDto.getTel());

        return addressBookRepository.save(addressBook);
    }

    /**
     * 주소록 정보를 삭제 한다.
     *
     * @param httpRequest
     * @param addressBookId
     */
    public void deleteAddressBook(HttpServletRequest httpRequest, Long addressBookId) {
        String userId = authorizationService.getAccountIdFromToken(httpRequest);
        AddressBook addressBook = addressBookRepository.findFirstByIdAndUserId(addressBookId, userId).orElseThrow(DataNotFoundException::new);

        // 전화번호 체크
        seoulTemNumCheck(addressBook.getTel());
        addressBookRepository.delete(addressBook);
    }


}
