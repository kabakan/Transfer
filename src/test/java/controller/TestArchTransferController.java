package controller;

import com.revolut.api.ArchTransferService;
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
 * Unit test of ArchTransferController
 * @author Kanat K.B.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestArchTransferController {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BodyResponse bodyResponse;

    @MockBean
    private ArchTransferService archTransferService;

    @Test
    public void get() throws Exception {
        List<Transfer> transfers = new ArrayList<>();
        Transfer transfer = new Transfer();
        transfer.setFromAccCode("ACC7788454545");
        transfer.setToAccCode("ACC7788757575");
        transfer.setTitle("Test transfer");
        transfer.setStatus("SUCCESS");
        transfer.setCurrCode("USD");
        transfer.setSumm(10.1);
        transfer.setCreateDate(CreateDate.getDate());
        archTransferService.add(transfer);

        when(archTransferService.get("ACC7788454545","ACC7788757575")).thenReturn(transfers);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/archTransfer/get"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[1].id", is(1)))
                .andExpect(jsonPath("$[1].fromAccCode", is("ACC7788454545")))
                .andExpect(jsonPath("$[1].toAccCode", is("ACC7788757575")))
                .andExpect(jsonPath("$[1].currCode", is("USD")))
                .andExpect(jsonPath("$[1].title", is("Test transfer")))
                .andExpect(jsonPath("$[1].status", is("SUCCESS")))
                .andExpect(jsonPath("$[1].summ", is(10.1)))
                .andExpect(jsonPath("$[1].sendDate", is(CreateDate.getDate())))
                .andExpect(jsonPath("$[1].createDate", is(CreateDate.getDate())))
                .andDo(print());

        verifyNoMoreInteractions(archTransferService);
    }

    @Test
    public void getAll() throws Exception {
        List<Transfer> transfers = new ArrayList<>();
        Transfer transfer = new Transfer();
        transfer.setFromAccCode("ACC7788454545");
        transfer.setToAccCode("ACC7788757575");
        transfer.setTitle("Test transfer");
        transfer.setStatus("SUCCESS");
        transfer.setCurrCode("USD");
        transfer.setSumm(10.1);
        transfer.setCreateDate(CreateDate.getDate());
        archTransferService.add(transfer);

        when(archTransferService.getAll()).thenReturn(transfers);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/archTransfer/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[1].id", is(1)))
                .andExpect(jsonPath("$[1].fromAccCode", is("ACC7788454545")))
                .andExpect(jsonPath("$[1].toAccCode", is("ACC7788757575")))
                .andExpect(jsonPath("$[1].currCode", is("USD")))
                .andExpect(jsonPath("$[1].title", is("Test transfer")))
                .andExpect(jsonPath("$[1].status", is("SUCCESS")))
                .andExpect(jsonPath("$[1].summ", is(10.1)))
                .andExpect(jsonPath("$[1].sendDate", is(CreateDate.getDate())))
                .andExpect(jsonPath("$[1].createDate", is(CreateDate.getDate())))
                .andDo(print());

        verifyNoMoreInteractions(archTransferService);
    }


}
