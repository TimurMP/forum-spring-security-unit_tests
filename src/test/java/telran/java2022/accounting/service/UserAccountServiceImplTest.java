package telran.java2022.accounting.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import telran.java2022.accounting.dao.UserAccountRepository;
import telran.java2022.accounting.dto.UserAccountResponseDto;
import telran.java2022.accounting.dto.UserRegisterDto;
import telran.java2022.accounting.model.UserAccount;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
//@WebMvcTest(UserAccountService.class)
@ExtendWith(SpringExtension.class)

//@RunWith(MockitoJUnitRunner.class)
class UserAccountServiceImplTest {

//    final ModelMapper modelMapper;
    @Mock
    private UserAccountRepository accountRepository;

    @Spy
    ModelMapper modelMapper;

    @InjectMocks
    private UserAccountServiceImpl accountService;

//    @InjectMocks
//    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @InjectMocks
    UserRegisterDto userRegisterDto;




//    @Autowired
//    private MockMvc mvc;

    @Test
    void addUser() {
        ModelMapper modelMapper = getModelMapper();
        Set<String> roles = new HashSet<>();
        String pass = "1111";
        roles.add("admin");
        UserAccount userAccount = new UserAccount("test", "1111", "aaa", "bbb");
        userAccount.setPasswordExpDate(LocalDate.now());
        userAccount.setPassword("1111");
        UserAccountResponseDto responseDto = modelMapper.map(userAccount, UserAccountResponseDto.class);
        UserRegisterDto userRegisterDto = modelMapper.map(userAccount, UserRegisterDto.class);

        System.out.println(userRegisterDto.getPassword());


//        UserAccountResponseDto userAccountResponseDto = new UserAccountResponseDto();
//        userAccountResponseDto.setLogin("test");
//        userAccountResponseDto.setFirstName("aaa");
//        userAccountResponseDto.setLastName("bbb");
//        userAccountResponseDto.setRoles(roles);
//        UserAccountResponseDto createdUser = accountService.addUser(userRegisterDto);

        when(accountRepository.existsById("test")).thenReturn(true);
//        when(accountService.addUser(Mockito.any(UserRegisterDto.class))).thenReturn(responseDto);
//        when(passwordEncoder.encode(userRegisterDto.getPassword())).thenReturn("1111");
//        when(userRegisterDto.getPassword()).thenReturn(pass);

        UserAccountResponseDto createdUser = accountService.addUser(userRegisterDto);

        Assertions.assertThat(createdUser).isEqualTo(responseDto);

    }


    @Test
    void FindUserByLogin() {
        ModelMapper modelMapper = getModelMapper();

        String login = "test";
        Set<String> roles = new HashSet<>();
        roles.add("admin");
        UserAccount userAccount = new UserAccount(login, "1111", "aaa", "bbb");//        UserAccount user = UserAccount.builder()
//                .login("test")
//                        .password("111")
//                                .firstName("aaa")
//                                        .roles(roles).build();

//        UserRegisterDto userRegisterDto = modelMapper.map(userAccount, UserRegisterDto.class);
//        UserAccountResponseDto responseDto = modelMapper.map(userRegisterDto, UserAccountResponseDto.class);

//        Mockito.when(accountService.addUser(Mockito.any(UserRegisterDto.class))).thenReturn(responseDto);
        UserAccountResponseDto responseDto = modelMapper.map(userAccount, UserAccountResponseDto.class);
        when(accountRepository.findById(login)).thenReturn(Optional.of(userAccount));

        UserAccountResponseDto createdUser = accountService.getUser(login);

        Assertions.assertThat(createdUser).isEqualTo(responseDto);






    }

    public ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STRICT);

        return modelMapper;
    }
}