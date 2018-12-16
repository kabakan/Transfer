package controller;

import com.revolut.api.AccountsService;
import com.revolut.model.Accounts;
import com.revolut.utils.BodyResponse;
import com.revolut.utils.CreateDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Unit test of AccountsController
 * @author Kanat K.B.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestAccountsController {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BodyResponse bodyResponse;

    @MockBean
    private AccountsService accountsService;

    @Test
    public void add() throws Exception {
        Accounts acc = new Accounts();
        acc.setClientName("Alex Li");
        acc.setAccCode("ACC7788454545");
        acc.setCurrCode("USD");
        acc.setSumm(10.1);
        when(accountsService.add(acc)).thenReturn(bodyResponse);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/accounts/add"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.status", is("SUCCESS")))
                .andExpect(jsonPath("$.message", is("Add account")))
                .andDo(print());

        verifyNoMoreInteractions(accountsService);
    }

    @Test
    public void update() throws Exception {
        Accounts acc = new Accounts();
        acc.setId(1);
        acc.setClientName("Alex Li");
        acc.setAccCode("ACC7788454545");
        acc.setCurrCode("USD");
        acc.setSumm(10.1);
        when(accountsService.update(acc)).thenReturn(bodyResponse);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/accounts/update"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.status", is("SUCCESS")))
                .andExpect(jsonPath("$.message", is("Update account")))
                .andDo(print());

        verifyNoMoreInteractions(accountsService);
    }

    @Test
    public void delete() throws Exception {
        Accounts acc = new Accounts();
        acc.setId(1);
        acc.setClientName("Alex Li");
        acc.setAccCode("ACC7788454545");
        acc.setCurrCode("USD");
        acc.setSumm(10.1);
        when(accountsService.delete(acc)).thenReturn(bodyResponse);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/accounts/delete"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.status", is("SUCCESS")))
                .andExpect(jsonPath("$.message", is("Delete account")))
                .andDo(print());

        verifyNoMoreInteractions(accountsService);
    }

    @Test
    public void get() throws Exception {
        List<Accounts> accounts = new ArrayList<>();
        Accounts acc = new Accounts();
        acc.setClientName("Alex Li");
        acc.setAccCode("ACC7788454545");
        acc.setCurrCode("USD");
        acc.setSumm(10.1);
        accountsService.add(acc);
        when(accountsService.get("Alex","CA12345")).thenReturn(accounts);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/accounts/get"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].clientName", is("Alex Li")))
                .andExpect(jsonPath("$[1].accCode", is("ACC7788454545")))
                .andExpect(jsonPath("$[1].currCode", is("USD")))
                .andExpect(jsonPath("$[1].summ", is(100.1)))
                .andExpect(jsonPath("$[1].createDate", is(CreateDate.getDate())))
                .andDo(print());

        verifyNoMoreInteractions(accountsService);
    }

    @Test
    public void getAll() throws Exception {
        List<Accounts> accounts = new ArrayList<>();
        Accounts acc = new Accounts();
        acc.setClientName("Alex Li");
        acc.setAccCode("ACC7788454545");
        acc.setCurrCode("USD");
        acc.setSumm(10.1);
        accountsService.add(acc);
        when(accountsService.getAll()).thenReturn(accounts);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/accounts/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].clientName", is("Alex Li")))
                .andExpect(jsonPath("$[1].accCode", is("ACC7788454545")))
                .andExpect(jsonPath("$[1].currCode", is("USD")))
                .andExpect(jsonPath("$[1].summ", is(10.1)))
                .andExpect(jsonPath("$[1].createDate", is(CreateDate.getDate())))
                .andDo(print());

        verifyNoMoreInteractions(accountsService);
    }


    @Test
    public void generate() throws Exception {
        Map<String,String> acc = new Hashtable<>();
        when(accountsService.generate()).thenReturn(acc);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/accounts/generate"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.account", is(accountsService.generate().get("account"))))
                .andDo(print());

        verifyNoMoreInteractions(accountsService);
    }

}
