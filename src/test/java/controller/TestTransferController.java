package controller;

import com.revolut.api.TransferService;
import com.revolut.model.Transfer;
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
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Unit test of TransferController
 * @author Kanat K.B.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestTransferController {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BodyResponse bodyResponse;

    @MockBean
    private TransferService transferService;

    @Test
    public void add() throws Exception {
        Transfer transfer = new Transfer();
        transfer.setFromAccCode("ACC7788454545");
        transfer.setToAccCode("ACC7788757575");
        transfer.setCurrCode("USD");
        transfer.setSumm(100.0);

        when(transferService.add(transfer)).thenReturn(bodyResponse);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/transfer/add"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.status", is("SUCCESS")))
                .andExpect(jsonPath("$.message", is("Add transfer")))
                .andDo(print());

        verifyNoMoreInteractions(transferService);
    }

    @Test
    public void send() throws Exception {
        Transfer transfer = new Transfer();
        transfer.setFromAccCode("ACC7788454545");
        transfer.setToAccCode("ACC7788757575");
        transfer.setCurrCode("USD");
        transfer.setSumm(100.0);
        transferService.add(transfer);

        when(transferService.sendTransfer()).thenReturn(bodyResponse);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/transfer/send"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.status", is("FINISH")))
                .andExpect(jsonPath("$.message", is("Send transfer completed")))
                .andDo(print());

        verifyNoMoreInteractions(transferService);
    }


    @Test
    public void get() throws Exception {
        List<Transfer> transfers = new ArrayList<>();
        Transfer transfer = new Transfer();
        transfer.setFromAccCode("ACC7788454545");
        transfer.setToAccCode("ACC7788757575");
        transfer.setCurrCode("USD");
        transfer.setSumm(10.1);
        transferService.add(transfer);

        when(transferService.get("ACC7788454545","ACC7788757575")).thenReturn(transfers);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/transfer/get"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[1].id", is(1)))
                .andExpect(jsonPath("$[1].fromAccCode", is("ACC7788454545")))
                .andExpect(jsonPath("$[1].toAccCode", is("ACC7788757575")))
                .andExpect(jsonPath("$[1].currCode", is("USD")))
                .andExpect(jsonPath("$[1].title", is("")))
                .andExpect(jsonPath("$[1].status", is("CREATE")))
                .andExpect(jsonPath("$[1].summ", is(10.1)))
                .andExpect(jsonPath("$[1].sendDate", is("")))
                .andExpect(jsonPath("$[1].createDate", is(CreateDate.getDate())))
                .andDo(print());

        verifyNoMoreInteractions(transferService);
    }

    @Test
    public void getAll() throws Exception {
        List<Transfer> transfers = new ArrayList<>();
        Transfer transfer = new Transfer();
        transfer.setFromAccCode("ACC7788454545");
        transfer.setToAccCode("ACC7788757575");
        transfer.setCurrCode("USD");
        transfer.setSumm(10.1);
        transferService.add(transfer);

        when(transferService.getAll()).thenReturn(transfers);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/transfer/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[1].id", is(1)))
                .andExpect(jsonPath("$[1].fromAccCode", is("ACC7788454545")))
                .andExpect(jsonPath("$[1].toAccCode", is("ACC7788757575")))
                .andExpect(jsonPath("$[1].currCode", is("USD")))
                .andExpect(jsonPath("$[1].title", is("")))
                .andExpect(jsonPath("$[1].status", is("CREATE")))
                .andExpect(jsonPath("$[1].summ", is(10.1)))
                .andExpect(jsonPath("$[1].sendDate", is("")))
                .andExpect(jsonPath("$[1].createDate", is(CreateDate.getDate())))
                .andDo(print());

        verifyNoMoreInteractions(transferService);
    }


}
