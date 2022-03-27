package kr.eric.api.controller;

import kr.eric.api.dto.AccountDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.eric.api.dto.AddressBookDto;
import kr.eric.api.entity.AddressBook;
import kr.eric.api.service.AddressBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/address-book")
@Api(tags = {"주소록 API"})
@RequiredArgsConstructor
public class AddressBookController {

    private final AddressBookService addressBookService;

    @GetMapping(value = "")
    @ApiOperation(value = "주소록 조회", httpMethod = "GET")
    public List<AddressBook> getAddressBookList(
            HttpServletRequest httpRequest
    ) {
        return addressBookService.getAddressBookList(httpRequest);
    }

    @PostMapping(value = "")
    @ApiOperation(value = "주소록 추가", httpMethod = "POST")
    public AddressBook registerAddressBook(
            HttpServletRequest httpRequest,
            AddressBookDto.AddressBookRequest request
    ) {
        return addressBookService.registerAddressBook(httpRequest, request);
    }

    @PutMapping(value = "/{addressBookId}")
    @ApiOperation(value = "주소록 정보 변경", httpMethod = "PUT")
    public AddressBook modifyAddressBook(
            HttpServletRequest httpRequest,
            AddressBookDto.AddressBookRequest request,
            @PathVariable(name = "addressBookId") Long addressBookId
    ) {
        return addressBookService.modifyAddressBook(httpRequest, request, addressBookId);
    }

    @DeleteMapping(value = "/{addressBookId}")
    @ApiOperation(value = "주소록 정보 삭제", httpMethod = "DELETE")
    public String deleteAddressBook(
            HttpServletRequest httpRequest,
            @PathVariable(name = "addressBookId") Long addressBookId
    ) {
        addressBookService.deleteAddressBook(httpRequest, addressBookId);
        return "success";
    }
}
