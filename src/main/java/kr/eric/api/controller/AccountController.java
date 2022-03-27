package kr.eric.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.eric.api.dto.AccountDto;
import kr.eric.api.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/address-book")
@Api(tags = {"계정 API"})
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping(value = "/signup")
    @ApiOperation(value = "회원가입", httpMethod = "POST")
    public void signUp(
            @RequestBody AccountDto.SignUpRequest request
    ) {
        accountService.signUp(request);
    }

    @PostMapping(value = "/login")
    @ApiOperation(value = "로그인", httpMethod = "POST")
    public String login(
            @RequestBody AccountDto.LoginRequest request
    ) {
        return accountService.login(request);
    }
}
